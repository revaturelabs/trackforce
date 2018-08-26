package com.revature.services;
import com.revature.dao.BatchDao;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfBatch;
import java.util.List;

/** @author Adam L. 
 * @version v6.18.06.13 */
public class BatchService {
	private BatchDao dao;

	public BatchService() { dao = new BatchDaoImpl(); }// public so it can be used for testing

    public BatchService(BatchDao dao) { this.dao = dao; }

    /** @author Adam L.
	 * <p>Gets the batch given the batch name</p>
	 * @version v6.18.06.13 */
	public TfBatch getBatch(String batchName) {  return dao.getBatch(batchName); }
	
	/** @author Adam L.
	 * <p>Gets the batch given their batch Id</p>
	 * @version v6.18.06.13
	 * @param id - the batch id */
	public TfBatch getBatchById(Integer id) { return dao.getBatchById(id); }
	
	/** @author Adam L.
	 * <p>Gets all batches</p>
	 * @version v6.18.06.13 */
	public List<TfBatch> getAllBatches() { return dao.getAllBatches(); }
}
