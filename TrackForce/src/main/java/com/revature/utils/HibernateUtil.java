package com.revature.utils;

import static com.revature.utils.LogUtil.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Curtis H., Adam L.
 *         <p>
 *         The abstracted methods for making Hibernate calls to the database
 *         </p>
 * @version v6.18.06.13
 *
 */
public class HibernateUtil {

	private static ThreadUtil threadUtil = new ThreadUtil();

	private HibernateUtil() {
		super();
	}

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static void addShutdown() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});
	}

	private static SessionFactory buildSessionFactory() {

		try {
			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.url", System.getenv("TRACKFORCE_DB_URL"));
			cfg.setProperty("hibernate.connection.username", System.getenv("TRACKFORCE_DB_USERNAME"));
			cfg.setProperty("hibernate.connection.password", System.getenv("HBM_PW_ENV"));

			return cfg.configure().buildSessionFactory();

		} finally {
			addShutdown();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		logger.info("Shutting down SessionFactory");
		getSessionFactory().close();
		logger.info("SessionFactory closed");
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
			logger.info("Session is" + (session.isOpen() ? " open" : " closed"));
		}
	}

	public static void rollbackTransaction(Transaction transaction) {
		if (transaction != null) {
			transaction.rollback();
			logger.warn("Transaction rolled back");
		}
	}

	// The code above this line to the top of the package is basically an exact copy
	// of stuff William did in class
	// Now we abstract further...

	public static boolean runHibernateTransaction(Sessional<Boolean> sessional, Object... args) {
		Callable<Boolean> caller = () -> {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			boolean b = sessional.operate(session, args);	
			if (b) {
				logger.debug("Committing...");
			} else {
				throw new HibernateException("Transaction Operation Failed!");
			}
			transaction.commit();
			logger.info("Transaction committed!");
				transaction.commit();
				logger.info("Transaction committed!");

				return true;
			} catch (HibernateException | ThrownInHibernate e) {
				HibernateUtil.rollbackTransaction(transaction);
				logger.error(e.getMessage(), e);
			} finally {
				if (session != null)
					session.close();
			}
			return false;
		};
		
		return threadUtil.submitCallable(caller);
	}

	public static <T> boolean multiTransaction(Sessional<Boolean> sessional, List<T> items) {
		//Be careful using this method as it can create extreme strain by creating multiple threads
		//This should be refactored along with a refactor of runHibernateTransaction to both call
		//on another method that does the work that runs x amount of given times. Or implement a
		//cache the ensures that flush is not called on a hibernate transaction
		return HibernateUtil.runHibernateTransaction((Session session, Object... args) -> {
			for (T a : items) {
				if (!sessional.operate(session, a)) {
					return false;
				}
			}
			return true;
		});
	}

	public static <T> T runHibernate(Sessional<T> ss, Object... args) {
		Callable<T> caller = () -> {
			Session session = null;
			Throwable t = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				return ss.operate(session, args);
			} catch (ThrownInHibernate | HibernateException e) {
				logger.error(e.getMessage(), e);
				t = e;
			} finally {
				if (session != null)
					session.close();
			}
			throw new HibernateException(t);
		};

		return threadUtil.submitCallable(caller);
	}

	public static <T> List<T> runHibernate(ListOp<T> ss, Object... args) {
		Callable<List<T>> caller = () -> {
			Session session = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				return ss.operate(session, args);
			} catch (ThrownInHibernate | HibernateException e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (session != null)
					session.close();
			}
			return new ArrayList<>();
		};

		return threadUtil.submitCallable(caller);
	}

	private static Sessional<Boolean> dbSave = (Session session, Object... args) -> {
		session.save(args[0]);
		return true;
	};

	public static boolean saveToDB(Object o) {
		return runHibernateTransaction(dbSave, o);
	}

	public static <T> boolean saveToDB(List<T> o) {
		return multiTransaction(dbSave, o);
	}

	private static Sessional<Boolean> detachedUpdate = (Session session, Object... args) -> {
		session.update(args[0]);
		return true;
	};

	public static <T> boolean updateDetached(T det) {
		return runHibernateTransaction(detachedUpdate, det);
	}

	public static <T> boolean updateDetached(List<T> det) {
		return multiTransaction(detachedUpdate, det);
	}

}