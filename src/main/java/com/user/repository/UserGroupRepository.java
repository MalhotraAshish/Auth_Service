package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer>{

}
