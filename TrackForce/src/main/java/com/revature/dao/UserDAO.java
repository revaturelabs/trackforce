package com.revature.dao;

import java.io.IOException;

import org.hibernate.Session;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;

public interface UserDAO {
	
	TfUser getUser(String username) throws IOException;
	boolean createUser(CreateUserModel newUser);

}
