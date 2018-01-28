/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/create")
public class CreateUserService {

    private JWTService jwtService;
    private UserDAO userDao;
    private SessionFactory sessionFactory;

    /**
     * injectable dependencies for easier testing
     *
     * @param jwtService
     * @param userDao
     * @param sessionFactory
     */
    public CreateUserService(JWTService jwtService, UserDAO userDao, SessionFactory sessionFactory) {
        this.jwtService = jwtService;
        this.userDao = userDao;
        this.sessionFactory = sessionFactory;
    }

    public CreateUserService() {
        userDao = new UserDaoImpl();
        sessionFactory = HibernateUtil.getSessionFactory();
        jwtService = new JWTService();
    }

    /**


    /**
     * Endpoint used to create a new user in the database with a specified role, username and
     * password
     *
     * @param newUser
     * @return SuccessOrFailMessage
     */
    @POST
    @Path("/user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public SuccessOrFailMessage createUser(CreateUserModel newUser) {
        Session session = null;
        Transaction tx = null;
        SuccessOrFailMessage msg = new SuccessOrFailMessage();
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            msg = userDao.createUser(newUser, session);

            tx.commit();
            session.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            msg.setFailure();

            if (tx != null)
                tx.rollback();
        }
        finally {
            if (session != null)
                session.close();
        }
        return msg;
    }

}
