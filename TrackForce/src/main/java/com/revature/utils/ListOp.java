package com.revature.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface ListOp<T> {
	List<T> operate (Session session, Object ... args) throws HibernateException, ThrownInHibernate;
}