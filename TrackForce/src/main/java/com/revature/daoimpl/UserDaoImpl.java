package com.revature.daoimpl;

import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import java.util.List;

import com.revature.entity.TfRole;
import org.hibernate.Session;
import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public TfUser getUser(Integer id) {
		LogUtil.logger.trace("Hibernate Call to get User by Id: " + id);
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfUser u where u.id = :id", TfUser.class)
		.setParameter("id", id).setCacheable(true).getSingleResult());
		
	}

	@Override
	public TfUser getUser(String username) {
		LogUtil.logger.trace("Hibernate Call to get User by Username: " + username);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfUser u where u.username like :username", TfUser.class)
				.setParameter("username", username).setCacheable(true).getSingleResult());
	}

	@Override
	public List<TfUser> getAllUsers() {
		LogUtil.logger.trace("Hibernate Call to get ALL Users.");
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfUser ", TfUser.class).setCacheable(true).getResultList());
	}

	@Override
	public boolean insertUser(TfUser newUser) {
		LogUtil.logger.trace("Hibernate Call to save created User["+newUser.getId()+"] to the Database.");
		return HibernateUtil.saveToDB(newUser);
	}

	@Override
	public TfRole getRole(int roleId) {
		LogUtil.logger.trace("Hibernate Call to get Role of User by RoleId: " + roleId);
		return HibernateUtil.runHibernate(
				(Session session, Object... args) -> session.createQuery("from TfRole u where u.id = :id", TfRole.class)
						.setParameter("id", roleId).setCacheable(true).getSingleResult());
	}

	@Override
	public boolean updateUser(TfUser user) {
		LogUtil.logger.trace("Hibernate Call to update User: " + user.getId());
		return runHibernateTransaction((Session session, Object... args) -> {
			session.update(user);
			return true;
		});
	}

	/**
	 * Allows deletion of the user in the database. So far its only used for a
	 * RestAssured test to allow rerunability. Could be used in the future in the
	 * case that say an administrator can delete a user.
	 * 
	 * @author Seth L.
	 * @param newUser
	 */
	@Override
	public void deleteUser(TfUser user) {
		LogUtil.logger.trace("Hibernate Call to delete User: " + user.getId() +
							". Currently only used as part of RestAssured tests.");
		runHibernateTransaction((Session session, Object... args) -> {
			session.delete(user);
			return true;
		});
	}

}