package com.revature.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;

public class UserDaoImpl implements UserDAO {

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
    
    public boolean createUser(CreateUserModel newUser) {
        String password;
        Transaction t1 = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	t1 = session.beginTransaction();
            password = PasswordStorage.createHash(newUser.getPassword());
            TfUser user = new TfUser(newUser.getRole(), newUser.getUsername(), password);
            session.save(user);
            return true;
        } catch (Exception e) {
        	t1.rollback();
        	LogUtil.logger.error(e);
            e.printStackTrace();
        }
        return false;
    }
}
