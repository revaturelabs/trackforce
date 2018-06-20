package com.revature.services;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	UserService userService = new UserService();
	public boolean createAssociate(TfAssociate newassociate) {
		try {
			TfUser associateuser = newassociate.getUser();
			associateuser.setPassword(PasswordStorage.createHash(newassociate.getUser().getPassword()));
			newassociate.setUser(associateuser);
			List<TfAssociate> associates = getAllAssociates();
			int maxid = associates.size();
			newassociate.setId(4951 + maxid); // Late game fix for non-functional Seq gen
			LogUtil.logger.info("The associate with hashed password is " + newassociate);
		} catch (CannotPerformOperationException e) {
			LogUtil.logger.warn(e.getMessage());
		}		
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
