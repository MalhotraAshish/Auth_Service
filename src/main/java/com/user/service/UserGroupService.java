package com.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.entity.UserGroup;
import com.user.repository.UserGroupRepository;

@Service
@Transactional
public class UserGroupService {
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	public void saveUserGroup(UserGroup userGroup) {
		userGroupRepository.save(userGroup);
	}
	public List<UserGroup> getAllUsersGroup() {
		return userGroupRepository.findAll();
	}
	
	public UserGroup getUserGroup(Integer id) {
		return userGroupRepository.findById(id).get();
	}

	public void deleteUserById(Integer id) {
		userGroupRepository.deleteById(id);
	}
}
