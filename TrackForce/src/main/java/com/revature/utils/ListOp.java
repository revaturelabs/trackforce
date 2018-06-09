package com.revature.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public interface ListOp<T> {
	List<T> operate (Session session, Object ... args) throws HibernateException;
}