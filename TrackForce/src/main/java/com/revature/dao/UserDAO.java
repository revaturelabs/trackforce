package com.revature.dao;


import java.io.IOException;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;

public interface UserDAO {
	
	TfUser getUser(String username) throws IOException;
	SuccessOrFailMessage createUser(CreateUserModel newUser); 

}
