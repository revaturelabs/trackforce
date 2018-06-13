package com.revature.dao;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.request.model.CreateAssociateModel;
import com.revature.request.model.CreateUserModel;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

import static com.revature.utils.LogUtil.logger;

public class UserDaoImpl implements UserDAO {

    
	@Override
	public TfUser getUser(String username) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfUser", TfUser.class).getSingleResult());
	}
    
    @Override
	public List<TfUser> getAllUsers() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfUser ", TfUser.class).setCacheable(true).getResultList());
	}
    
	@Override
	public boolean insertUser(TfUser newUser) {
		return HibernateUtil.saveToDB(newUser);
	}
    
}
