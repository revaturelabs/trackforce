 package com.revature.services;

import java.util.List;

import javax.persistence.NoResultException;

import com.revature.dao.UserDao;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

/**
 * @author Adam L. 
 * <p> </p>
 * @version.date v6.18.06.13
 *
 */
public class UserService {

	private UserDao dao = new UserDaoImpl();
	
	// public so it can be used for testing 
	public UserService() {};
	
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
	// used in the submitCredentials 
	private JWTService jwtService;

	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v6.18.06.13
	 * 
	 * @return
	 */
    public List<TfUser> getAllUsers(){
    	return dao.getAllUsers();
    }
    
   /**
    * @author Adam L. 
    * <p> </p>
    * @version.date v6.18.06.13
    * 
    * @param username
    * @return
    */
    public TfUser getUser(String username) {
    	try {
        	return dao.getUser(username);
    	} catch (NoResultException nre) {
    		return null;
    	}
    }
    
   /**
    * @author Adam L. 
    * <p> </p>
    * @version.date v6.18.06.13
    * 
    * @param newUser
    * @return
    */
	public boolean insertUser(TfUser newUser) {
		return dao.insertUser(newUser);
	}

	public TfRole getRole(int roleId) {return dao.getRole(roleId);}
	
	/**
	 * @author Adam L. 
	 * <p>Allows verification that a given user exists, and has the correct password.</p>
	 * 
	 * <p>Given the TfUser with only the username and password, find that user by the username.
	 * If it exists, retrieve it from the database.
	 * Given their hashed passwords match, return the user from the database.</p>
	 * @version.date v6.18.06.13
	 * 
	 * @param loginUser
	 * @return foundUser 
	 */
	public TfUser submitCredentials(TfUser loginUser){
		LogUtil.logger.info("creating query in hibernate..");
		TfUser foundUser = getUser(loginUser.getUsername());
		LogUtil.logger.info("The found user was " + foundUser.toString());
		if(foundUser != null) {
			try {
				if(PasswordStorage.verifyPassword(loginUser.getPassword(), foundUser.getPassword())) {
					int role = foundUser.getTfRole().getTfRoleId();
					foundUser.setRole(role);
					foundUser.setToken(jwtService.createToken(foundUser.getUsername(), foundUser.getRole()));
					LogUtil.logger.info("Password verification successful! Returning " + foundUser.toString());
					return foundUser;
				}
			} catch (CannotPerformOperationException | InvalidHashException e) {
				LogUtil.logger.warn(e.getMessage());
			}
		}
		return null;
	}
}
