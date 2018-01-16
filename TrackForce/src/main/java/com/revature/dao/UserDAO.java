package com.revature.dao;


import java.io.IOException;

import org.hibernate.Session;

import com.revature.entity.TfUser;

public interface UserDAO {
	
	TfUser getUser(String username, Session session) throws IOException;

}
