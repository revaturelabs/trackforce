/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.services;
import com.revature.dao.UserDaoImpl;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


@Path("/create")
public class CreateUserService {
    
    /**
     * Endpoint used to create a new user in the database with a specified role, username and 
     * password
     * @param newUser
     * @return SuccessOrFailMessage
     */
    @POST
    @Path("/user")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public SuccessOrFailMessage createUser(CreateUserModel newUser) throws IOException {
        
        UserDaoImpl userDao = new UserDaoImpl(); 
        return userDao.createUser(newUser);
    }
    
}
