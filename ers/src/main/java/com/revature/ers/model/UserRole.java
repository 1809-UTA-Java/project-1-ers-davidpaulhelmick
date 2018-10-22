package com.revature.ers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERROLES")
public class UserRole {
	@Id
	@Column(name = "UR_ID")
	private int urID;

	@Column(name = "UR_ROLE")
	private String role;

	public UserRole(int urID, String role) {
		super();
		this.urID = urID;
		this.role = role;
	}

	public UserRole() {
		super();
	}

	public int getUrID() {
		return urID;
	}

	public void setUrID(int urID) {
		this.urID = urID;
	}

	public String getRoles() {
		return role;
	}

	public void setRoles(String roles) {
		this.role = roles;
	}
}
