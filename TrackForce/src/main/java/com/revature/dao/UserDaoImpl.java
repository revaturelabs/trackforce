package com.revature.dao;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

public class UserDaoImpl implements UserDAO{
	
	@Override
	public TfUser getUser(String username) {
		SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);

		Root<TfUser> root = criteriaQuery.from(TfUser.class);

		criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));

		Query<TfUser> query = session.createQuery(criteriaQuery);

		TfUser user;

		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			user = new TfUser();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public String getUserHash(TfUser user) {
		
		String hash = new String();
		
		try {
			hash = PasswordStorage.createHash(user.getTfUserHashpassword());
		} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hash;
}

}
