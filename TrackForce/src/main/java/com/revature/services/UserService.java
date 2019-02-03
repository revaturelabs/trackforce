package com.revature.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

import com.revature.dao.UserDao;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import static com.revature.utils.LogUtil.logger;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

/**

 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
 *
 */
public class UserService {

	private UserDao dao = new UserDaoImpl();

	// public so it can be used for testing
	public UserService() {
	};

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	// used in the submitCredentials
	private JWTService jwtService;

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version.date v6.18.06.13
	 * 
	 * @return
	 */
	public List<TfUser> getAllUsers() {
		return dao.getAllUsers();
	}

	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version v6.18.06.13
	 * 
	 * @param username
	 * @return
	 * 
	 * Added a new catch exception to handle other exception
	 * I am not proud of this but it is what was given to me
	 * -Coder from batch 1806
	 */
	public TfUser getUser(String username) {
		try {
			return dao.getUser(username);
		} catch (NoResultException nre) {
			logger.error("No Such User: " + username + "\n" + nre.getMessage());
			return null;
		} catch (HibernateException e) {
			return new TfUser();
		}
	}
	
	/**
	 * @author Lei Z.
	 * <p> </p>
	 * @version v6.18.11
	 * 
	 * @param userId
	 * @return TfUser
	 * 
	 * Same code-type as above but using the provided getting user by userId
	 */
	public TfUser getUser(Integer userId) {
		try {
			return dao.getUser(userId);
		} catch (NoResultException nre) {
			logger.error("No Such User: " + userId + "\n" + nre.getMessage());
			return null;
		} catch (HibernateException e) {
			return new TfUser();
		}
	}
    
   /**
    * @author Adam L. 
    * <p> </p>
    * @version v6.18.06.13
    * 
    * @param newUser
    * @return
    */
	public boolean insertUser(TfUser newUser) {
		if(newUser==null) {
			return false;
		}
		try {
			newUser.setPassword(PasswordStorage.createHash(newUser.getPassword()));
		} catch (CannotPerformOperationException e) {
			logger.warn(e.getMessage());
		}
		return dao.insertUser(newUser);
	}

	/**
	 * Allows deletion of the user in the database.  So far its only used for a RestAssured test to allow rerunability.
	 * Could be used in the future in the case that say an administrator can delete a user.
	 * @author Seth L.
	 * @param newUser
	 */
	public void deleteUser(TfUser newUser) {

		dao.deleteUser(newUser);
	}
	
	public TfRole getRole(int roleId) {
		return dao.getRole(roleId);
	}

	/**
	 * @author Adam L.
	 *         <p>
	 * 		Allows verification that a given user exists, and has the correct
	 *         password.
	 *         </p>
	 * 

	 * <p>Given the TfUser with only the username and password, find that user by the username.
	 * If it exists, retrieve it from the database.
	 * Given their hashed passwords match, return the user from the database.</p>
	 * @version v6.18.06.13
	 * 
	 * @param loginUser
	 * @return foundUser
	 */
	public TfUser submitCredentials(TfUser loginUser) {
		if(loginUser==null) {
			return null;
		}
		TfUser foundUser = getUser(loginUser.getUsername());
		if (foundUser != null) {
			try {
				if (PasswordStorage.verifyPassword(loginUser.getPassword(), foundUser.getPassword())) {
					int role = foundUser.getTfRole().getTfRoleId();
					foundUser.setRole(role);
					foundUser.setToken(JWTService.createToken(foundUser.getUsername(), foundUser.getRole()));
					return foundUser;
				}
			} catch (CannotPerformOperationException | InvalidHashException e) {
				logger.warn(e.getMessage());
			}
		}
		return null;
	}
	
	/*
	 * @author Lei Z
	 * @param userUpdatePass, updatePass
	 * Updates the user's password based on the passed TfUser object and password String object.
	 * Will return false if could not perform the hashing of the new password.
	 */
	public Boolean updateUserPassword(TfUser userUpdatePass, String updatePass) {
		return dao.updateUserPass(userUpdatePass, updatePass);
	}
	
	/*
	 * @author Lei Z
	 * @param userUpdateName, newUsername
	 * Updates the user's username based on the passed TfUser object and username String object.
	 */
	public Boolean updateUsername(TfUser userUpdateName, String newUsername) {
		return dao.updateUsername(userUpdateName, newUsername);
	}
}