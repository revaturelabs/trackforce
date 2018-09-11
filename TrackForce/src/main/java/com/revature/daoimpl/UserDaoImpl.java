package com.revature.daoimpl;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import java.util.List;
import com.revature.entity.TfRole;
import org.hibernate.Session;
import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public TfUser getUser(String username) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfUser u where u.username like :username", TfUser.class)
		.setParameter("username", username).setCacheable(true).getSingleResult());
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

	@Override
	public TfRole getRole(int roleId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfRole u where u.id = :id", TfRole.class)
				.setParameter("id", roleId).setCacheable(true).getSingleResult());
	}

	@Override
	public boolean updateUser(TfUser user) {
		return runHibernateTransaction((Session session, Object ... args)->{
			session.update(user);
			return true;
		});
	}
}