package com.revature.request.model;

public class AssociateUserModel {

	private String username;
	private String password;
	private String fname;
	private String lname;
	
	/**
	 * @param username
	 * @param password
	 * @param fname
	 * @param lname
	 */
	public AssociateUserModel(String username, String password, String fname, String lname) {
		super();
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fname
	 */
	public String getfname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setfname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getlname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setlname(String lname) {
		this.lname = lname;
	}

	public AssociateUserModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AssociateUserModel [username=" + username + ", password=" + password + ", fname=" + fname + ", lname="
				+ lname + "]";
	}

}
