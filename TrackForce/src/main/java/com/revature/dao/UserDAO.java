package com.revature.dao;


import java.io.IOException;

import org.hibernate.Session;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;

public interface UserDAO {
	
<<<<<<< HEAD
	TfUser getUser(String username, Session session) throws IOException;
=======
	TfUser getUser(String username) throws IOException;
	SuccessOrFailMessage createUser(CreateUserModel newUser); 
>>>>>>> 524587ec5f277885965cfaba8ef9ed3c466ac6ca

}
