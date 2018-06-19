package com.revature.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * <p>Used by the Hibernate utility class</p>
 * @version v6.18.06.13
 * 
 * @param <T>
 */
public interface Sessional<T> {
	T operate(Session session, Object ... args) throws HibernateException, ThrownInHibernate;
}
