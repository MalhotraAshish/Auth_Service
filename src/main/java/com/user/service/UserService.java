package com.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.entity.UserGroup;
import com.user.repository.UserGroupRepository;
import com.user.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}

	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	
}
