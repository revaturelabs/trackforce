package com.revature.model;

import java.math.BigDecimal;

/**
 * 
 * @author Michael Tseng
 * 
 * This class is the Java representation of a JSON bean to be set to Angular
 * Jackson will automatically convert the Java Bean to JSON
 * This beans contains user roleId, username, JWT token
 * Produced by the REST service
 *
 */
public class UserJSON {
	
	private String username;
	//Role Id represents the user's role (i.e. associate, manager, and etc.)
	private BigDecimal tfRoleId;
	//token is the JWT token generated
	private String token;
	//user id
	private BigDecimal userId;
	
	public UserJSON() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getTfRoleId() {
		return tfRoleId;
	}

	public void setTfRoleId(BigDecimal tfRoleId) {
		this.tfRoleId = tfRoleId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserJSON [username=" + username + ", tfRoleId=" + tfRoleId + ", token=" + token + ", userId=" + userId
				+ "]";
	}


}
