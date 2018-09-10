package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.CurriculumDao;
import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;

/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
 *
 */
public class CurriculumService{

	private static CurriculumDao dao = new CurriculumDaoImpl();
	
	// public so it can be used for testing 
	public CurriculumService() {};
	
	public CurriculumService(CurriculumDao dao) {
		this.dao = dao;
	}
	
	/**
	 * @author Adam L. 
	 * <p>Gets all the curricula</p>
	 * @version v6.18.06.13
	 * 
	 * @return
	 */
	public List<TfCurriculum> getAllCurriculums(){
		return dao.getAllCurriculums();
	}


	/**
	 * Generates statistics for the expanded view of the home page unmapped chart
	 *
	 * @param statusId
	 * @return Collection<CurriculumJSON>
	 */
	public List<GraphedCriteriaResult> getUnmappedInfo(int statusId) {
		return dao.getUnmapped(statusId);
	}

//	public List<GraphedCriteriaResult> getAssociateCountByCurriculum(Timestamp timestamp, Timestamp timestamp2) {
//
//		return dao.getAssociateCountByCurriculum(timestamp, timestamp2);
//	}
}