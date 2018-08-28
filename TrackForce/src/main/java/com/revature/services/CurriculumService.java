package com.revature.services;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.CurriculumDao;
import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;
import java.util.List;

/** @author Adam L.
 * @version v6.18.06.13 */
public class CurriculumService{

	private static CurriculumDao dao = new CurriculumDaoImpl();

	public CurriculumService() {} // public so it can be used for testing

    public CurriculumService(CurriculumDao dao) { CurriculumService.dao = dao; }
	
	/** @author Adam L.
	 * <p>Gets all the curricula</p>
	 * @version v6.18.06.13 */
	public List<TfCurriculum> getAllCurriculums(){ return dao.getAllCurriculums(); }

	/** Generates statistics for the expanded view of the home page unmapped chart */
	public List<GraphedCriteriaResult> getUnmappedInfo(int statusId) { return dao.getUnmapped(statusId); }
}