package com.revature.services;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;

/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version.date v6.18.06.13
 *
 */
public class AssociateService {
	
	private AssociateDao dao;
	
	// public so it can be used for testing 
	public AssociateService() {dao = new AssociateDaoImpl();};
	public AssociateService(AssociateDao dao) {this.dao = dao;};
	/**
	 * @author Adam L. 
	 * 
	 * <p>Gets the associate given by their associate Id.</p>
	 * 
	 * @version.date v6.18.06.13
	 * @param associateid
	 * @return TfAssociate
	 */
	public TfAssociate getAssociate(int associateid) {
		return dao.getAssociate(associateid);
	}

	/**
	 * @author Curtis H.
	 *
	 * <p>Gets the associate given by their associate Id.</p>
	 *
	 * @version.date v6.18.06.13
	 * @param id
	 * @return TfAssociate
	 */
	public TfAssociate getAssociateByUserId(int id) {
		return dao.getAssociateByUserId(id);
	}

	
	/**
	 * @author Adam L. 
	 * 
	 * <p>Gets all associates in the database.</p>
	 * 
	 * @version.date v6.18.06.13
	 * @return List<TfAssociate>
	 */
	public List<TfAssociate> getAllAssociates(){
		return dao.getAllAssociates();
	}
	
	
	/**
	 * @author Adam L. 
	 * 
	 * <p>Updates an associate based on their Id, which should not change.
	 * Any field you wish to update should be included in the TfAsscociate.</p>
	 * 
	 * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
	 * 	it will be saved as such!</p>
	 * 
	 * @version.date v6.18.06.13
	 * @param associate
	 * @return true if successful, false otherwise
	 */
	public boolean updateAssociatePartial(TfAssociate associate) {
		return dao.updateAssociatePartial(associate);
	}

	public boolean updateAssociate(TfAssociate associate) {
		return dao.updateAssociate(associate);
	}




	/**
	 * @author Adam L. 
	 * <p>Updates all associates based on their individual Id, which should not change.
	 * Any field you wish to update should be included in the TfAsscociate.</p>
	 * 
	 * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
	 * 	it will be saved as such!</p>
	 * @version.date v6.18.06.13
	 * 
	 * @param associates
	 * @return
	 */
	public boolean updateAssociates(List<TfAssociate> associates) {
		return dao.updateAssociates(associates);
	}
	
	/**
	 * @author Adam L. 
	 * 
	 * <p>Creates an associate in the database.</p>
	 * 
	 * @since v6.18.06.13
	 * @param newassociate
	 * @return true if successful, false otherwise
	 */
	public boolean createAssociate(TfAssociate newassociate) {
		return dao.createAssociate(newassociate);
	}

	/**
	 * @author Curtis H.
	 *
	 * @since v6.18.06.16
	 *
	 */
	public List<GraphedCriteriaResult> getMappedInfo(int status) {return dao.getMapped(status);}

}
