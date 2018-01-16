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
	 * Update the marketing status or client of an associate from form data.
	 *
	 * @param id
	 *            - The ID of the associate to change
	 * @param marketingStatus
	 *            - What to change the associate's marketing status to
	 * @param client
	 *            - What client to change the associate to
	 * @return
	 * @throws IOException
	 */
	@PUT
	@Path("{associate}/update")
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociate(@FormParam("id") String id, @FormParam("marketingStatus") String marketingStatus,
			@FormParam("client") String client) throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
			TfMarketingStatus status = marketingStatusDao.getMarketingStatus(session, marketingStatus);

			ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
			TfClient tfclient = clientDaoImpl.getClient(session, client);

			BigDecimal associateID = new BigDecimal(Integer.parseInt(id));

			AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
			associateDaoHibernate.updateInfo(session, associateID, status, tfclient);

			session.flush();
			tx.commit();
			return Response.status(Response.Status.OK).entity("Updated the associate's information").build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("could not update associates", e);
		} finally {
			session.close();
		}
	}

	@Override
	public synchronized void execute() throws IOException {
		Set<BatchInfo> bi = PersistentStorage.getStorage().getBatches();
		if(bi == null || bi.isEmpty());
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
