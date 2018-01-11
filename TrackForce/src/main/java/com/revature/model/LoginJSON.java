package com.revature.model;

//This class is the Java representation of a JSON bean sent from Angular
//Jackson will automatically convert the JSON consumed to a Java Bean, if the
//attribute names match
//Consumed by the REST service
public class LoginJSON {
	
	private String username;
	private String password;
	
	public LoginJSON() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginJSON [username=" + username + ", password=" + password + "]";
	}
	
	
}
