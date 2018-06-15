package com.revature.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;


/** 
 * <p>Used by the Hibernate utility class</p>
 * @version.date v06.2018.06.13
 * 
 */
public interface ListOp<T> {
	List<T> operate (Session session, Object ... args) throws HibernateException, ThrownInHibernate;
}