package com.revature.services;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.revature.criteria.UnmappedCriteriaResult;
import com.revature.dao.CurriculumDao;
import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfCurriculum;

/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version.date v6.18.06.13
 *
 */
public class CurriculumService{

	private static CurriculumDao dao = new CurriculumDaoImpl();
	
	// public so it can be used for testing 
	public CurriculumService() {};
	
	/**
	 * @author Adam L. 
	 * <p>Gets all the curricula</p>
	 * @version.date v6.18.06.13
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
	public List<UnmappedCriteriaResult> getUnmappedInfo(int statusId) {
		return dao.getUnmapped(statusId);
	}
}
