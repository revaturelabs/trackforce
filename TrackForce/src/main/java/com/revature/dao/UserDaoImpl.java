package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;

public class UserDaoImpl implements UserDAO {
	static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public TfUser getUser(String username) {
        TfUser user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
            Root<TfUser> root = criteriaQuery.from(TfUser.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
            Query<TfUser> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();
        } catch(Exception e) {
        	logger.error(e);
        }
        finally {
            session.close();
        }
        return user;
    }
    
    public boolean createUser(CreateUserModel newUser) {
        String password;
        Transaction t1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	t1 = session.beginTransaction();
            password = PasswordStorage.createHash(newUser.getPassword());
            TfUser user = new TfUser(newUser.getRole(), newUser.getUsername(), password);
            session.save(user);
            return true;
        } catch (NullPointerException e) {
        	if (t1 != null) {
				t1.rollback();
			}
        	logger.error(e);
        } catch (Exception e) {
        	if (t1 != null) {
				t1.rollback();
			}
        	logger.error(e);        	
        }
        finally {
            session.close();
        }
        return false;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TfUser> getAllUsers() {
		List<TfUser> user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	return session.createQuery("from com.revature.entity.TfUser").list();
        } catch(Exception e) {
        	logger.error(e);
        }
        finally {
            session.close();
        }
        return user;
	}
}
