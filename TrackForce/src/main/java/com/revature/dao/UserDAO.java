package com.revature.dao;


import java.io.IOException;

import com.revature.entity.TfUser;

public interface UserDAO {
	
	TfUser getUser(String username) throws IOException;

}
