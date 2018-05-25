package com.revature.model;

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
	private Integer tfRoleId;
	//token is the JWT token generated
	private String token;
	//user id
	private int userId;
	
	public UserJSON() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTfRoleId() {
		return tfRoleId;
	}

	public void setTfRoleId(Integer tfRoleId) {
		this.tfRoleId = tfRoleId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserJSON [username=" + username + ", tfRoleId=" + tfRoleId + ", token=" + token + ", userId=" + userId
				+ "]";
	}


}
