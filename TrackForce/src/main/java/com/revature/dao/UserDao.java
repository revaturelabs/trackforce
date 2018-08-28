package com.revature.dao;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import java.util.List;

public interface UserDao {
	TfUser getUser(String username);
	List<TfUser> getAllUsers();
	boolean insertUser(TfUser newUser);
	TfRole getRole(int roleId);
	boolean updateUser(TfUser user);//UNUSED??
}