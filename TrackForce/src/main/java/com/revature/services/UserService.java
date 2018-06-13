 package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfUser;

/**
 * Services for getting all users, getting a single user, creating an associate and submitting 
 * credentials
 * reviewed by Jesse
 * @since 6.18.06.08
*/
public class UserService {

	private static UserDAO dao = new UserDaoImpl();
	private UserService() {};

    public static List<TfUser> getAllUsers(){
    	return dao.getAllUsers();
    }
    
    public static TfUser getUser(String username) {
    	return dao.getUser(username);
    }
    
	public static boolean insertUser(TfUser newUser) {
		return dao.insertUser(newUser);
	}

    /**
     * Method takes login information from front-end and verifies the information.
     *
     * @param login - contains login information
     * @return a Response object with authentication data, such as username, JWT
     * token, and roleId
     * @throws IOException
     */
//    public TfUser submitCredentials(LoginJSON login) throws IOException {
//        String username = login.getUsername();
//        String password = login.getPassword();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        //TODO: input a service that makes an associate if possible, if it does then grab its verified field if not 
//        //verified return a null object for the hibernate team
//        Transaction tx = session.beginTransaction();
//        try {
//            // Attempts to get the user from the database based on username
//            TfUser tfUser = userDao.getUser(username);
//            if (tfUser != null) {
//                String hashedPassword = tfUser.getTfUserHashpassword();
//                // If the user object is empty, the user is invalid
//                if (tfUser.equals(new TfUser())) {
//                    //return Response.status(400).build();
//                } else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
//                    TfRole tfRole = tfUser.getTfRole();
//                    userjson = new UserJSON();
//
//                    if (tfRole != null) {
//                    	Integer tfRoleId = tfRole.getTfRoleId();
//                        String tfUserName = tfUser.getTfUserUsername();
//                        //Integer tfRegistered = tfRegisteredFlag();
//                        // If the user have a valid role and username, a 200 can be returned
//                        if (tfRoleId != null && tfUserName != null) {
//                            // Sets the role id and username to the userjson object, which is set back to angular
//                        	userjson.setTfRoleId(tfRoleId);
//                            userjson.setUsername(tfUserName);
//                            userjson.setUserId(tfUser.getTfUserId());
//                            
//                            // If the user is an associate...
//                            if(tfRoleId == 5) {
//                            	TfAssociate assoc=AssociateDaoHibernate.getTfAssociate(tfUser.getTfUserId());
//                            	//If the user has a valid associate id...
//                            	if (!assoc.equals(null)) {
//                            	//if(tfUser.getTfUserAssociate().getTfAssociateId() != null) {
//                            		//Sets the associate id to the userjson object, which is set back to angular
//                            		userjson.setAssociateId(assoc.getTfAssociateId());
//                            		if(assoc.getIsApproved().equals(null)) {
//                            			userjson.setIsApproved(0);
//                            		}
//                            		else {
//                            			userjson.setIsApproved(assoc.getIsApproved());
//                            		}
//                            	}
//                            	
//                            }
//
//                            // Uses JWT service to create token
//                            userjson.setToken(this.jwtService.createToken(tfUserName,tfRoleId));
//                            session.flush();
//                            tx.commit();
//                            //return Response.status(200).entity(userjson).build();
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            session.flush();
//            tx.rollback();
//            logger.error(e);
//            throw new IOException("Could not get associate", e);
//        } finally {
//            session.close();
//        }
//        return userjson;
//        //return Response.status(400).build();
//    }

}
