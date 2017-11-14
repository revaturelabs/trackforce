package com.revature.dao;


import com.revature.entity.TfUser;

public interface UserDAO {
	
	TfUser getUser(String username);
	
	String getUserHash(TfUser user);

}
