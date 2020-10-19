package com.user.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		createInitialUsers();
	}

	@GetMapping(value = "/getAllUsers", headers="Accept=application/json")
	public List<User> getAllUsers() {
		System.out.println("In List");
		return userService.getAllUsers();
	}

	@GetMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getAllUser(@PathVariable Integer id) {
		User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user) {
		System.out.println("Creating User " + user.getFirstName());
		userService.saveUser(user);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value="/updateUser/{id}", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User currentUser)
    {
        User user = userService.getUser(currentUser.getId());
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        user.setFirstName(currentUser.getFirstName());
        user.setSecondName(currentUser.getSecondName());
        user.setEmail(currentUser.getEmail());
        userService.saveUser(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
	private void createInitialUsers() {
		userService.saveUser(new User(1, "rakesh", "pass", "Rakesh", "Kumar", "abc@gmail.com", true, "ADMIN"));
		//userService.saveUser(new User(1, "parag", "pass", "Parag", "Jain", "parag_jain@gmail.com", true, "ADMIN"));
		//userService.saveUser(new User(1, "ashish", "pass", "Rakesh", "Kumar", "abc@gmail.com", true, "ADMIN"));
	}

}
