package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchesService implements Service {

    private BatchDao batchDao;

    public BatchesService() {
        this.batchDao = new BatchDaoHibernate();
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


    /**
     * Gets the number of associates learning each curriculum during a given date
     * range
     *
     * @param fromDate - the starting date of the date range
     * @param todate   - the ending date of the date range
     * @return - A map of associates in each curriculum with the curriculum name as
     * the key and number of associates as value.
     * <p>
     * The returned chart data is laid out as follows: [ { "curriculum" ->
     * "1109 Sept 11 Java JTA", "value" -> 14 },
     * <p>
     * { "curriculum" -> "1109 Sept 11 Java Full Stack", "value" -> 16 }, *
     * <p>
     * ... ]
     * @throws IOException
     */
    public List<BatchInfo> getBatchChartInfo(Long fromDate, Long todate) throws IOException {
        List<BatchInfo> batches = batchDao.getBatchesSortedByDate();
        List<BatchInfo> subList = new LinkedList<>();
        if (batches == null)
            execute();
        for (BatchInfo bi : batches) {
            if (bi.getStartLong() != null && bi.getEndLong() != null)
                if (todate >= bi.getStartLong() && todate <= bi.getEndLong()) {
                    subList.add(bi);
                } else if (fromDate >= bi.getStartLong() && fromDate <= bi.getEndLong()) {
                    subList.add(bi);
                }
        }
        return subList;
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
            	if (bi.getStartLong() <= fromdate && fromdate <= bi.getEndLong())
            		sublist.add(bi);
//                if (fromdate <= bi.getStartLong() && bi.getStartLong() <= todate) {
//                    sublist.add(bi);
//                } else if (bi.getStartLong() <= fromdate && fromdate <= bi.getEndLong()) {
//                    sublist.add(bi);
//                }
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

    @Override
    public synchronized void execute() throws IOException {
//        Set<BatchInfo> bi = PersistentStorage.getStorage().getBatches();
//        if (bi == null || bi.isEmpty())
//            ;
//        PersistentStorage.getStorage().setBatches(getBatches());
    }

    @SuppressWarnings("unchecked")
	@Override
    public synchronized <T> Collection<T> read(String... args) throws IOException {
        if (args == null || args.length == 0) {
            return (Set<T>) getAllBatches();
        }
        return (List<T>) getAllBatchesSortedByDate();

    }
}
