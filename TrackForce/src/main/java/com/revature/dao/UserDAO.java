package com.revature.dao;

import java.math.BigDecimal;

import com.revature.entity.TfUser;

public interface UserDAO {
	
	TfUser getUser(String username);
	
	public String getUserHash(TfUser user);

}
