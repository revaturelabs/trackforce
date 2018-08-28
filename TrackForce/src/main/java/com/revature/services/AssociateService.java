package com.revature.services;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import java.util.List;

/** @author Adam L.
 * @version v6.18.06.13 */
public class AssociateService
{
	private AssociateDao dao;

	public AssociateService() { dao = new AssociateDaoImpl(); }// public so it can be used for testing

	public AssociateService(AssociateDao dao) { this.dao = dao; }
	
	//----------------------------------
	
	public List<TfAssociate> getNAssociates(){ return dao.getNAssociates(); }
	
	public Object getCountUndeployedMapped() { return dao.getCountUndeployedMapped(); }
	
	public Object getCountUndeployedUnmapped() { return dao.getCountUndeployedUnmapped(); }
	
	public Object getCountDeployedMapped() { return dao.getCountDeployedMapped(); }
	
	public Object getCountDeployedUnmapped() { return dao.getCountDeployedUnmapped(); }

	public Object getCountUnmappedTraining() { return dao.getCountUnmappedTraining(); }
	
	public Object getCountUnmappedOpen() { return dao.getCountUnmappedOpen(); }
	
	public Object getCountUnmappedSelected() { return dao.getCountUnmappedSelected(); }
	
	public Object getCountUnmappedConfirmed() { return dao.getCountUnmappedConfirmed(); }
	
	public Object getCountMappedTraining() { return dao.getCountMappedTraining(); }
	
	public Object getCountMappedReserved() { return dao.getCountMappedReserved(); }
	
	public Object getCountMappedSelected() { return dao.getCountMappedSelected(); }
	
	public Object getCountMappedConfirmed() { return dao.getCountMappedConfirmed(); }

	public List<GraphedCriteriaResult> getUndeployed(String which) { return dao.getUndeployed(which); }

	public boolean approveAssociate(int associateId) { return dao.approveAssociate(associateId); }

	public boolean updateAssociate(TfAssociate associate) { return dao.updateAssociate(associate); }

	public Long getMappedAssociateCountByClientId(Long client_id, Integer mappedStatus) 
	{ return dao.countMappedAssociatesByValue("TF_CLIENT_ID",client_id,mappedStatus); }

	 /** @author Adam L.
	 * <p>Gets the associate given by their associate Id.</p>
	 * @version v6.18.06.13 */
	public TfAssociate getAssociate(int associateid) { return dao.getAssociate(associateid); }

	/** @author Curtis H.
	 * <p>Gets the associate given by their associate Id.</p>
     * @version v6.18.06.13*/
	public TfAssociate getAssociateByUserId(int id) { return dao.getAssociateByUserId(id); }

	/** @author Adam L.
	 * <p>Gets all associates in the database.</p>
	 * @version v6.18.06.13 */
	public List<TfAssociate> getAllAssociates(){ return dao.getAllAssociates(); }
	
	/** @author Adam L.
	 * <p>Updates an associate based on their Id, which should not change.
	 * Any field you wish to update should be included in the TfAsscociate.</p>
	 * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
	 * 	it will be saved as such!</p>
	 * @version v6.18.06.13
	 * @return true if successful, false otherwise */
	public boolean updateAssociatePartial(TfAssociate associate)
    { return dao.updateAssociatePartial(associate); }

	/** @author Adam L. 
	 * <p>Updates all associates based on their individual Id, which should not change.
	 * Any field you wish to update should be included in the TfAsscociate.</p>
	 * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
	 * 	it will be saved as such!</p>
	 * @version v6.18.06.13 */
	public boolean updateAssociates(List<TfAssociate> associates) { return dao.updateAssociates(associates); }
	
	/** @author Adam L. 
	 * <p>Creates an associate in the database.</p>
	 * @since v6.18.06.13
	 * @return true if successful, false otherwise */
	public boolean createAssociate(TfAssociate newassociate) {
		try {
			TfUser associateuser = newassociate.getUser();
			associateuser.setPassword(PasswordStorage.createHash(newassociate.getUser().getPassword()));
			newassociate.setUser(associateuser);
			List<TfAssociate> associates = getAllAssociates();
			int maxid = associates.size();
			newassociate.setId(4951 + maxid); // Late game fix for non-functional Seq gen
			LogUtil.logger.info("The associate with hashed password is " + newassociate);
		} catch (CannotPerformOperationException e) { LogUtil.logger.warn(e.getMessage()); }		
		return dao.createAssociate(newassociate);
	}

	/** @author Curtis H.
	 * @since v6.18.06.16 */
	public List<GraphedCriteriaResult> getMappedInfo(int status) { return dao.getMapped(status); }
}