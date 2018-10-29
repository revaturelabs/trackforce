package com.revature.dao;
import java.util.List;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;

public interface UserDao {
	TfUser getUser(String username);
	
	List<TfUser> getAllUsers();
	
	boolean insertUser(TfUser newUser);
	
	TfRole getRole(int roleId);
	
	boolean updateUser(TfUser user);
	
	public void deleteUser(TfUser user);
}