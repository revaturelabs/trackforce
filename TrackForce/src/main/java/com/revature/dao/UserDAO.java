package com.revature.dao;


import java.io.IOException;

import org.hibernate.Session;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;

public interface UserDAO {
	
	TfUser getUser(String username, Session session) throws IOException;
	SuccessOrFailMessage createUser(CreateUserModel newUser, Session session);

}
