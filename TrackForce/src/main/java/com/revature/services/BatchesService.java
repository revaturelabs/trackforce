package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.BatchDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.*;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

/**
 * Class that provides RESTful services for the batch listing and batch details
 * page.
 */
@Path("batches")
public class BatchesService implements Delegate {

	/**
	 * Gets all batches that are running within a given date range
	 *
	 * @param fromdate
	 *            - the starting date of the date range
	 * @param todate
	 *            - the ending date of the date range
	 * @return - A list of the batch info. Batch info contains batch name, client
	 *         name, batch start date, and batch end date.
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

	private Map<BigDecimal, BatchInfo> getBatches() throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			BatchDaoHibernate batchDao = new BatchDaoHibernate();
			Map<BigDecimal, BatchInfo> map = batchDao.getBatchDetails(session);

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
	 * @param fromdate
	 *            - the starting date of the date range
	 * @param todate
	 *            - the ending date of the date range
	 * @return - A map of associates in each curriculum with the curriculum name as
	 *         the key and number of associates as value.
	 *
	 *         The returned chart data is laid out as follows: [ { "curriculum" ->
	 *         "1109 Sept 11 Java JTA", "value" -> 14 },
	 *
	 *         { "curriculum" -> "1109 Sept 11 Java Full Stack", "value" -> 16 }, *
	 *
	 *         ... ]
	 * @throws IOException
	 */
	@GET
	@Path("{fromdate}/{todate}/type")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<BatchInfo> getBatchChartInfo(@PathParam("fromdate") Long fromdate, @PathParam("todate") Long todate)
			throws IOException {
		List<BatchInfo> batches = PersistentStorage.getStorage().getBatchesByDate();
		List<BatchInfo> sublist = new LinkedList<BatchInfo>();
		if (batches == null)
			execute();
		for (BatchInfo bi : batches) {
			if (bi.getStartLong() != null && bi.getEndLong() != null)
				if (todate.compareTo(bi.getStartLong()) >= 1 && todate.compareTo(bi.getEndLong()) <= 1) {
					LogUtil.logger.info(new Timestamp(fromdate) + " <= " + new Timestamp(bi.getStartLong()) + " <= "
							+ new Timestamp(todate));
					LogUtil.logger.info("fromdate: " + new Timestamp(fromdate) + " todate: " + new Timestamp(todate)
							+ " startlong: " + new Timestamp(bi.getStartLong()) + " endLong: "
							+ new Timestamp(bi.getEndLong()));
					sublist.add(bi);
				} else if (fromdate.compareTo(bi.getStartLong()) <= 1 && fromdate.compareTo(bi.getEndLong()) >= 1) {
					LogUtil.logger.info(new Timestamp(bi.getStartLong()) + " <= " + new Timestamp(fromdate) + "<="
							+ new Timestamp(bi.getEndLong()));
					LogUtil.logger.info("fromdate: " + new Timestamp(fromdate) + " todate: " + new Timestamp(todate)
							+ " startlong: " + new Timestamp(bi.getStartLong()) + " endLong: "
							+ new Timestamp(bi.getEndLong()));
					sublist.add(bi);
				}
		}

		return sublist;
	}

	/**
	 * Gets all batches that are running within a given date range
	 *
	 * @param fromdate
	 *            - the starting date of the date range
	 * @param todate
	 *            - the ending date of the date range
	 * @return - A list of the batch info. Batch info contains batch name, client
	 *         name, batch start date, and batch end date.
	 * @throws IOException
	 */
	@GET
	@Path("{fromdate}/{todate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<BatchInfo> getBatches(@PathParam("fromdate") Long fromdate, @PathParam("todate") Long todate)
			throws IOException {
		List<BatchInfo> batches = PersistentStorage.getStorage().getBatchesByDate();
		List<BatchInfo> sublist = new LinkedList<BatchInfo>();
		if (batches == null)
			execute();
		for (BatchInfo bi : batches) {
			if (bi.getStartLong() != null && bi.getEndLong() != null)
				if (fromdate <= bi.getStartLong() && bi.getStartLong() <= todate) {
					LogUtil.logger.info(new Timestamp(fromdate) + " <= " + new Timestamp(bi.getStartLong()) + " <= "
							+ new Timestamp(todate));
					LogUtil.logger.info("fromdate: " + new Timestamp(fromdate) + " todate: " + new Timestamp(todate)
							+ " startlong: " + new Timestamp(bi.getStartLong()) + " endLong: "
							+ new Timestamp(bi.getEndLong()));
					sublist.add(bi);
				} else if (bi.getStartLong() <= fromdate && fromdate <= bi.getEndLong()) {
					LogUtil.logger.info(new Timestamp(bi.getStartLong()) + " <= " + new Timestamp(fromdate) + "<="
							+ new Timestamp(bi.getEndLong()));
					LogUtil.logger.info("fromdate: " + new Timestamp(fromdate) + " todate: " + new Timestamp(todate)
							+ " startlong: " + new Timestamp(bi.getStartLong()) + " endLong: "
							+ new Timestamp(bi.getEndLong()));
					sublist.add(bi);
				}
		}

		return sublist;
	}
	
	/**
	 * Gets the information of the associates in a particular batch
	 *
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A list of the lists of associate info. Associate info contains id,
	 *         first name, last name, and marketing status.
	 * @throws IOException
	 */
	@GET
	@Path("{batch}/associates")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<AssociateInfo> getAssociates(@PathParam("batch") String batchName) throws IOException {
		Set<AssociateInfo> associatesList = PersistentStorage.getStorage().getBatchAsMap().get(new BigDecimal(Integer.parseInt(batchName))).getAssociates();
		if(associatesList == null) {
			execute();
			return PersistentStorage.getStorage().getBatchAsMap().get(new BigDecimal(Integer.parseInt(batchName))).getAssociates();
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
