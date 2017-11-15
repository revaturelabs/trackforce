package com.revature.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class UserDaoImpl implements UserDAO {

	@Override
	public TfUser getUser(String username) {
		TfUser user;
		SessionFactory sessionFactory = HibernateUtil.getSession();
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);

			Root<TfUser> root = criteriaQuery.from(TfUser.class);

			criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));

			Query<TfUser> query = session.createQuery(criteriaQuery);

			try {
				user = query.getSingleResult();
				if(user.getTfRole() != null) {
					if (user.getTfRole().getTfRoleName() != null){
						Hibernate.initialize(user.getTfRole().getTfRoleName());
					}
				}
				
			} catch (NoResultException nre) {
				user = new TfUser();
				LogUtil.logger.error(nre);
			}
		}
		return user;
	}
}
