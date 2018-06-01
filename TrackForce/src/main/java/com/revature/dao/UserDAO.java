package com.revature.dao;

import java.util.List;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;

public interface UserDAO {
	
	TfUser getUser(String username);
	List<TfUser> getAllUsers();
	boolean createUser(CreateUserModel newUser);

}
