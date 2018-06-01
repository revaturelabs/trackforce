package com.revature.services;

import java.io.IOException;

import java.util.List;

import static com.revature.utils.LogUtil.logger;

import com.revature.request.model.CreateAssociateModel;
import com.revature.utils.LogUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;

public class UserService {
	

    private JWTService jwtService;
    private UserDAO userDao;

    public UserService() {
        userDao = new UserDaoImpl();
        jwtService = new JWTService();
    }

    /**
     * injectable dependencies constructor for easier testing
     *
     * @param userDao
     * @param jwtService
     */
    public UserService(UserDAO userDao, JWTService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    /**
     * Gets every user for TrackForce
     */
    public List<TfUser> getAllUsers(){
    	return userDao.getAllUsers();
    }

//    /**
//     * Creates a user
//     * @return Returns whether the response was successful
//     */
//    public SuccessOrFailMessage createNewUser(CreateUserModel newUser) {
//        SuccessOrFailMessage msg = new SuccessOrFailMessage();
//        boolean success = userDao.createUser(newUser);
//        if (success) {
//        	msg.setSuccess();
//        }
//        else {
//        	msg.setFailure();
//        }
//        return msg;
//    }

    /**
     * Takes in an object that provides username, password, first name and last name.
     * It first creates a user with the first/last name then uses its ID to generate a new
     * User with associate rights.
     *
     * @param newAssociate
     * @return
     */
    public SuccessOrFailMessage createNewAssociate(CreateAssociateModel newAssociate) {
        LogUtil.logger.info("createNewAssociate in Service hit");
        LogUtil.logger.info(newAssociate);
        SuccessOrFailMessage msg = new SuccessOrFailMessage();
        boolean success = userDao.createAssociate(newAssociate);
        if (success) {
            msg.setSuccess();
        }
        else {
            msg.setFailure();
        }
        return msg;
    }

    /**
     * Gets the user by the user's username
     * @param username Username used to get the user
     * @return Returns a TfUser json
     */
    public TfUser getUser(String username) {
    	return new UserDaoImpl().getUser(username);
    }


    /**
     * Method takes login information from front-end and verifies the information.
     *
     * @param login - contains login information
     * @return a Response object with authentication data, such as username, JWT
     * token, and roleId
     * @throws IOException
     */
    public UserJSON submitCredentials(LoginJSON login) throws IOException {
        String username = login.getUsername();
        String password = login.getPassword();
        UserJSON userjson = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //TODO: input a service that makes an associate if possible, if it does then grab its verified field if not 
        //verified return a null object for the hibernate team
        Transaction tx = session.beginTransaction();
        try {
            // Attempts to get the user from the database based on username
            TfUser tfUser = userDao.getUser(username);
            if (tfUser != null) {
                String hashedPassword = tfUser.getTfUserHashpassword();
                // If the user object is empty, the user is invalid
                if (tfUser.equals(new TfUser())) {
                    //return Response.status(400).build();
                } else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
                    TfRole tfRole = tfUser.getTfRole();
                    userjson = new UserJSON();

                    if (tfRole != null) {
                    	Integer tfRoleId = tfRole.getTfRoleId();
                        String tfUserName = tfUser.getTfUserUsername();
                        //Integer tfRegistered = tfRegisteredFlag();
                        // If the user have a valid role and username, a 200 can be returned
                        if (tfRoleId != null && tfUserName != null) {
                            // Sets the role id and username to the userjson object, which is set back to angular
                        	userjson.setTfRoleId(tfRoleId);
                            userjson.setUsername(tfUserName);
                            userjson.setUserId(tfUser.getTfUserId());

                            // Uses JWT service to create token
                            userjson.setToken(this.jwtService.createToken(tfUserName,tfRoleId));
                            session.flush();
                            tx.commit();
                            //return Response.status(200).entity(userjson).build();
                        }
                    }
                }
            }
        } catch (Exception e) {
            session.flush();
            tx.rollback();
            logger.error(e);
            throw new IOException("Could not get associate", e);
        } finally {
            session.close();
        }
        return userjson;
        //return Response.status(400).build();
    }
}
