package com.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_GROUP")
public class UserGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "GROUP_CODE")
	private String groupCode;
	
	@Column(name = "GROUP_DESC")
	private String description;
	
	@Column(name = "IS_ACTIVE")
	private boolean isActive;
	
	
	/*
	 * @OneToMany(mappedBy = "userGroups", fetch = FetchType.LAZY) private
	 * List<User> user = new ArrayList<>();
	 */ 
	//private User user;
	
	public UserGroup() {}


	public UserGroup(Integer id, String groupCode, String description, boolean isActive) {
		super();
		this.id = id;
		this.groupCode = groupCode;
		this.description = description;
		this.isActive = isActive;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGroup other = (UserGroup) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive != other.isActive)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", groupCode=" + groupCode + ", description=" + description + ", isActive="
				+ isActive + "]";
	}

		

}
