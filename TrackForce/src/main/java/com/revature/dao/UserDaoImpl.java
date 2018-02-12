package com.revature.dao;

import java.util.List;

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
        TfUser user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
            Root<TfUser> root = criteriaQuery.from(TfUser.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
            Query<TfUser> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();
        } catch(Exception e) {
        	LogUtil.logger.error(e);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TfUser> getAllUsers() {
		List<TfUser> user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	List<TfUser> list = session.createQuery("from com.revature.entity.TfUser").list();
        	return list;
        } catch(Exception e) {
        	LogUtil.logger.error(e);
        }
        return user;
	}
}
