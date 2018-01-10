package com.revature.model;

import java.math.BigDecimal;

public class UserJSON {
	
	private String username;
	private BigDecimal tfRoleId;
	private String token;
	
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

	@Override
	public String toString() {
		return "UserJSON [username=" + username + ", tfRoleId=" + tfRoleId + ", token=" + token + "]";
	}


}
