package com.revature.dao;

import java.util.List;

import com.revature.entity.TfUser;

public interface UserDAO {
	
	public TfUser getUser(String username);
	public List<TfUser> getAllUsers();
	public boolean insertUser(TfUser newUser);

}
