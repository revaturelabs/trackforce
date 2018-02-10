package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;

@Path("users")
public class UserResource {

    private JWTService jwtService;
    private UserDAO userDao;

    public UserResource() {
        userDao = new UserDaoImpl();
        jwtService = new JWTService();
    }

    /**
     * Gets every user for TrackForce
     * @return Returns a json of all the users
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers(){
    	//This will produce application/json
    	//Not sure if this will actually be needed
//    	return Response.status(501)
//    			.entity("This has not yet been implemented. There maybe future implementations")
//    			.build();
    	return "This has not yet been implemented. There maybe future implementations";
    }
    
    /**
     * Creates a user
     * @return Returns whether the response was successful
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createNewUser(){
    	//This will produce application/json
    	//Not sure if this will actually be needed
    	return Response.status(501)
    			.entity("This has not yet been implemented. There maybe future implementations")
    			.build();
    }
    
    /**
     * Gets the user by the user's username
     * @param username Username used to get the user
     * @return Returns a TfUser json
     */
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public TfUser getUser(@PathParam("username") String username) {
    	return new UserDaoImpl().getUser(username);
    }
    /**
     * injectable dependencies constructor for easier testing
     *
     * @param userDao
     * @param jwtService
     */
    public UserResource(UserDAO userDao, JWTService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    
    /**
     * Method takes login information from front-end and verifies the information.
     * If info is valid, a status code of 200 is returned, otherwise 400 for a bad
     * request
     *
     * @param login - contains login information
     * @return a Response object with authentication data, such as username, JWT
     * token, and roleId
     * @throws IOException
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitCredentials(LoginJSON login) throws IOException {
        String username = login.getUsername();
        String password = login.getPassword();
        UserJSON userjson = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            // Attempts to get the user from the database based on username
            TfUser tfUser = userDao.getUser(username, session);
            if (tfUser != null) {
                String hashedPassword = tfUser.getTfUserHashpassword();
                // If the user object is empty, the user is invalid
                if (tfUser.equals(new TfUser())) {
                    return Response.status(400).build();
                } else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
                    TfRole tfRole = tfUser.getTfRole();
                    userjson = new UserJSON();

                    if (tfRole != null) {
                        BigDecimal tfRoleId = tfRole.getTfRoleId();
                        String tfUserName = tfUser.getTfUserUsername();
                        // If the user have a valid role and username, a 200 can be returned
                        if (tfRoleId != null && tfUserName != null) {
                            // Sets the role id and username to the userjson object, which is set back to
                            // angular
                            userjson.setTfRoleId(tfRoleId);
                            userjson.setUsername(tfUserName);

                            userjson.setUserId(tfUser.getTfUserId());

                            // Uses JWT service to create token
                            userjson.setToken(this.jwtService.createToken(tfUserName));
                            session.flush();
                            tx.commit();
                            return Response.status(200).entity(userjson).build();
                        }
                    }
                }
            }
        } catch (Exception e) {
            session.flush();
            tx.rollback();
            e.printStackTrace();
            LogUtil.logger.error(e);
            throw new IOException("Could not get associate", e);
        } finally {
            session.close();
        }
        // Default return is 400 for a bad request
        return Response.status(400).build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testM() {
        return "This is a test";
    }
}
