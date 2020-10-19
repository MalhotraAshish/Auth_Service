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
import com.user.entity.UserGroup;
import com.user.service.UserGroupService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/group")
public class UserGroupController {
	
	@Autowired
	private UserGroupService userGroupService;
	
	@PostConstruct
	public void init() {
		createInitialUsers();
	}
	
	@GetMapping(value = "/getAllUserGroup", headers="Accept=application/json")
	public List<UserGroup> getAllUserGroup() {
		System.out.println("In User Group List");
		return userGroupService.getAllUsersGroup();
	}
	
	@GetMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserGroup> getAllUser(@PathVariable Integer id) {
		UserGroup userGroup = userGroupService.getUserGroup(id);
        if (userGroup == null) {
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserGroup>(userGroup, HttpStatus.OK);
		
	}

	@PostMapping(value = "/createGroup", headers = "Accept=application/json")
	public ResponseEntity<Void> createGroup(@RequestBody UserGroup userGroup) {
		System.out.println("Creating User " + userGroup.getGroupCode());
		userGroupService.saveUserGroup(userGroup);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value="/updateGroup", headers="Accept=application/json")
    public ResponseEntity<String> updateUserGroup(@RequestBody UserGroup currentUserGrp)
    {
        UserGroup userGrp = userGroupService.getUserGroup(currentUserGrp.getId());
        if (userGrp == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
       
        userGroupService.saveUserGroup(userGrp);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

	@DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<User> deleteUserGroup(@PathVariable Integer id){
        UserGroup userGrp = userGroupService.getUserGroup(id);
        if (userGrp == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userGroupService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
	private void createInitialUsers() {
		userGroupService.saveUserGroup(new UserGroup(1, "Admin", "Administrator", true));
	}
}
