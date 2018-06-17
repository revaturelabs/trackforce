package com.revature.utils;

import static com.revature.utils.LogUtil.logger;

import java.util.List;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfTrainer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Curtis H., Adam L.
 * <p>The abstracted methods for making Hibernate calls to the database</p>
 * @version.date v6.18.06.13
 *
 */
public class HibernateUtil {

	private HibernateUtil() {}
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		Configuration cfg = new Configuration();
			
		cfg.setProperty("hibernate.connection.url", System.getenv("TRACKFORCE_DB_URL"));
		cfg.setProperty("hibernate.connection.username", System.getenv("TRACKFORCE_DB_USERNAME"));
		cfg.setProperty("hibernate.connection.password", System.getenv("HBM_PW_ENV"));

		return cfg.configure().buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

//	public static void shutdown() {
//		System.out.println("Shutting down SessionFactory");
//		getSessionFactory().close();
//		System.out.println("SessionFactory closed");
//	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
			System.out.println("Session is" + (session.isOpen() ? " open" : " closed"));
		}
	}

	public static void rollbackTransaction(Transaction transaction) {
		if (transaction != null) {
			transaction.rollback();
			System.out.println("Transaction rolled back");
		}
	}

	// The code above this line to the top of the package is basically an exact copy of stuff William did in class
	// Now we abstract further.


	public static boolean runHibernateTransaction(Sessional<Boolean> sessional, Object ... args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			boolean b = sessional.operate(session, args);

			if (b) {
				logger.debug("Committing...");
			} else throw new HibernateException("Transaction Operation Failed!");

			transaction.commit();
			logger.info("Transaction committed!");

			return true;
		} catch (HibernateException | ThrownInHibernate e) {
			HibernateUtil.rollbackTransaction(transaction);
			logger.error(e.getMessage(), e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return false;
	}


	public static <T> boolean multiTransaction(Sessional<Boolean> sessional, List<T> items) {
		return HibernateUtil.runHibernateTransaction((Session session, Object ... args) -> {
			for (T a : items) {
				if (!sessional.operate(session, a)) {
					return false;
				}
			}
			return true;
		});
	}

	public static <T> T runHibernate(Sessional<T> ss, Object ... args) {
		Session session = null;
		Throwable t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return ss.operate(session, args);
		} catch (ThrownInHibernate | HibernateException e) {
			logger.error(e.getMessage(), e);
			t = e;
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		throw new HibernateException(t);
	}



	public static <T> List<T> runHibernate(ListOp<T> ss,  Object ... args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return ss.operate(session, args);
		} catch (ThrownInHibernate | HibernateException e) {
			logger.error(e.getMessage(), e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}

	private static Sessional<Boolean> dbSave = (Session session, Object ... args) -> {
		session.save(args[0]);
		return true;
	};

	public static boolean saveToDB(Object o) {
		return runHibernateTransaction(dbSave, o);
	}

	public static <T> boolean saveToDB(List<T> o) {
		return multiTransaction(dbSave, o);
	}

}