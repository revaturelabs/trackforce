package com.revature.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface Sessional<T> {
	T operate(Session session, Object ... args) throws HibernateException;
}