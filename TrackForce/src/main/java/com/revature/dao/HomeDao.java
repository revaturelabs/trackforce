package com.revature.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.entity.TfAssociate;

public interface HomeDao {
	
	/**
	 * Returns a list of TfAssociate objects.
	 * @return a list of TfAssociate objects.
	 */
	List<TfAssociate> getAllTfAssociates(Session session) throws HibernateException, IOException;

}
