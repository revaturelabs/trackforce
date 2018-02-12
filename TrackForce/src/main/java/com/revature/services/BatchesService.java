package com.revature.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.BatchDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dao.BatchDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

/**
 * Class that provides RESTful services for the batch listing and batch details
 * page.
 */
@Path("batches")
public class BatchesService implements Service {

    private BatchDao batchDao;
    private SessionFactory sessionFactory;

    public BatchesService() {
        this.batchDao = new BatchDaoHibernate();
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * injectable dao for easier testing
     *
     * @param batchDao
     */
    public BatchesService(BatchDao batchDao, SessionFactory sessionFactory) {
        this.batchDao = batchDao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get all batches
     *
     * @return - A list of the batch info. Batch info contains batch name, client
     * name, batch start date, and batch end date.
     * @throws IOException
     */
    private synchronized Set<BatchInfo> getAllBatches() throws IOException {
        Set<BatchInfo> batches = PersistentStorage.getStorage().getBatches();
        if (batches == null || batches.isEmpty()) {
            execute();
            return PersistentStorage.getStorage().getBatches();
        }
        return batches;
    }

    private List<BatchInfo> getAllBatchesSortedByDate() throws IOException {
        List<BatchInfo> batches = PersistentStorage.getStorage().getBatchesByDate();
        if (batches == null || batches.isEmpty()) {
            execute();
            return PersistentStorage.getStorage().getBatchesByDate();
        }

        return batches;
    }

    private Map<Integer, BatchInfo> getBatches() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
        	
            Map<Integer, BatchInfo> map = batchDao.getBatchDetails(session);
            session.flush();
            tx.commit();

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.logger.error(e);
            session.flush();
            tx.rollback();
            throw new IOException("could not get batches", e);
        } finally {
        	
            session.close();
        }
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
    @GET
    @Path("{fromdate}/{todate}/type")
    @Produces({MediaType.APPLICATION_JSON})
    public List<BatchInfo> getBatchChartInfo(@PathParam("fromdate") Long fromDate, @PathParam("todate") Long todate)
            throws IOException {
        List<BatchInfo> batches = PersistentStorage.getStorage().getBatchesByDate();
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

    /**
     * Gets all batches that are running within a given date range
     *
     * @param fromdate - the starting date of the date range
     * @param todate   - the ending date of the date range
     * @return - A list of the batch info. Batch info contains batch name, client
     * name, batch start date, and batch end date.
     * @throws IOException
     */
    @GET
    @Path("{fromdate}/{todate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<BatchInfo> getBatches(@PathParam("fromdate") Long fromdate, @PathParam("todate") Long todate)
            throws IOException {
        List<BatchInfo> batches = PersistentStorage.getStorage().getBatchesByDate();
        List<BatchInfo> sublist = new LinkedList<BatchInfo>();
        if (batches == null)
            execute();
        for (BatchInfo bi : batches) {
            if (bi.getStartLong() != null && bi.getEndLong() != null)
                if (fromdate <= bi.getStartLong() && bi.getStartLong() <= todate) {
                    sublist.add(bi);
                } else if (bi.getStartLong() <= fromdate && fromdate <= bi.getEndLong()) {
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
    @GET
    @Path("{batch}/associates")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<AssociateInfo> getAssociates(@PathParam("batch") String batchIdStr) throws IOException {
        Set<AssociateInfo> associatesList = PersistentStorage.getStorage()
                .getBatchAsMap()
                .get(new Integer(Integer.parseInt(batchIdStr)))
                .getAssociates();
        if (associatesList == null) {
            execute();
            return PersistentStorage.getStorage()
                    .getBatchAsMap()
                    .get(new Integer(Integer.parseInt(batchIdStr)))
                    .getAssociates();
        }
        return associatesList;

    }

    @Override
    public synchronized void execute() throws IOException {
        Set<BatchInfo> bi = PersistentStorage.getStorage().getBatches();
        if (bi == null || bi.isEmpty())
            ;
        PersistentStorage.getStorage().setBatches(getBatches());
    }

    @Override
    public synchronized <T> Collection<T> read(String... args) throws IOException {
        if (args == null || args.length == 0) {
            return (Set<T>) getAllBatches();
        }
        return (List<T>) getAllBatchesSortedByDate();

    }
}