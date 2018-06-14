package com.revature.services;

import java.io.IOException;
import java.util.Set;


import org.hibernate.HibernateException;

import com.revature.dao.CurriculumDao;
import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;

/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version.date v06.2018.06.13
 *
 */
public class CurriculumService{

	private static CurriculumDao dao = new CurriculumDaoImpl();
	
	// public so it can be used for testing 
	public CurriculumService() {};
	
	/**
	 * @author Adam L. 
	 * <p>Gets all the curricula</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @return
	 */
	public List<TfCurriculum> getAllCurriculums(){
		return dao.getAllCurriculums();
	}
}
