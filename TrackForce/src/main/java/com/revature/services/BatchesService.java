package com.revature.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchesService{// implements Service {

    private BatchDao batchDao;

    public BatchesService() {
        this.batchDao = new BatchDaoHibernate();
    }

    public BatchesService(BatchDao batchDao) {
    	this.batchDao = batchDao;
    }

    /**
     * Get all batches
     *
     * @return - A list of the batch info. Batch info contains batch name, client
     * name, batch start date, and batch end date.
     * @throws IOException
     */
    public Set<BatchInfo> getAllBatches() {
    	return batchDao.getAllBatches();
    }

    public List<BatchInfo> getAllBatchesSortedByDate() {
        List<BatchInfo> batches = batchDao.getBatchesSortedByDate();
        return batches;
    }

    public BatchInfo getBatchById(Integer id) {
    	return batchDao.getBatchById(id);
    }

    /**
     * Gets all batches that are running within a given date range
     *
     * @param fromdate - the starting date of the date range
     * @param todate   - the ending date of the date range
     * @return - A list of the batch info. Batch info contains batch name, client
     * name, batch start date, and batch end date.
     * @throws IOException
     */
    public List<BatchInfo> getBatches(Long fromdate, Long todate) {
        List<BatchInfo> batches = getAllBatchesSortedByDate();
		List<BatchInfo> sublist = new LinkedList<BatchInfo>();
        for (BatchInfo bi : batches) {
            if (bi.getStartLong() != null && bi.getEndLong() != null) {
            	if (bi.getEndLong() >= fromdate && bi.getEndLong() <= todate)
            		sublist.add(bi);
            }
        }
        return sublist;
    }


    /**
     * Gets the information of the associates in a particular batch
     *
     * @param batchIdStr - the name of a batch that is in the database
     * @return - A list of the lists of associate info. Associate info contains id,
     * first name, last name, and marketing status.
     * @throws IOException
     */
    public Set<AssociateInfo> getAssociatesForBranch(Integer id) {
    	return batchDao.getBatchAssociates(id);

    }

}
