package com.revature.daoimpl;

import java.util.List;

import org.hibernate.Session;

import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

    
	@Override
	public TfUser getUser(String username) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfUser u where u.username like :username", TfUser.class).setParameter("username", username).getSingleResult());
	}
    
    @Override
	public List<TfUser> getAllUsers() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfUser ", TfUser.class).getResultList());
	}
    
	@Override
	public boolean insertUser(TfUser newUser) {
		return HibernateUtil.saveToDB(newUser);
	}
    
}
