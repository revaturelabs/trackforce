package com.revature.dao;

import java.util.List;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;

public interface UserDao {
	
	public TfUser getUser(String username);
	public List<TfUser> getAllUsers();
	public boolean insertUser(TfUser newUser);
	TfRole getRole(int roleId);
	public boolean updateUser(TfUser user);

}
