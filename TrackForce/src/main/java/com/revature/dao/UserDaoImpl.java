package com.revature.dao;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;

public class UserDaoImpl implements UserDAO {

    @Override
    public TfUser getUser(String username, Session session) throws IOException {
        TfUser user;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);

        Root<TfUser> root = criteriaQuery.from(TfUser.class);

        criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));

        Query<TfUser> query = session.createQuery(criteriaQuery);

        try {
            user = query.getSingleResult();
            if (user.getTfRole() != null && user.getTfRole().getTfRoleName() != null) {
                user.getTfRole().getTfRoleName();
            }

        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
            throw new IOException("Cannot get user", nre);
        }
        return user;
    }

    public TfUser getUser(String username) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        TfUser user = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);

        Root<TfUser> root = criteriaQuery.from(TfUser.class);

        criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));

        Query<TfUser> query = session.createQuery(criteriaQuery);

        try {
            user = query.getSingleResult();
            if (user.getTfRole() != null && user.getTfRole().getTfRoleName() != null) {
                user.getTfRole().getTfRoleName();
            }

        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
        } finally {
        	session.close();
        }
        return user;
    }
    public SuccessOrFailMessage createUser(CreateUserModel newUser, Session session) {
        String password;
        SuccessOrFailMessage message = new SuccessOrFailMessage();

        try {
            password = PasswordStorage.createHash(newUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            message.setFailure();
            return message;
        }

        TfUser user = new TfUser(newUser.getRole(), newUser.getUsername(), password);
        session.save(user);

        message.setSuccess();
        return message;
    }
}
