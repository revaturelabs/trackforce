package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

/**
 * Class that provides RESTful services for the batch listing and batch details
 * page.
 */
@Path("batches")
public class BatchesService {

	private static final String OTHER_VALUE = "Other";
	private static final String UNKNOWN_VALUE = "null";

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
	public List<Map<String, Object>> getBatchChartInfo(@PathParam("fromdate") long fromdate,
			@PathParam("todate") long todate) throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			BatchDaoHibernate batchDao = new BatchDaoHibernate();
			List<TfBatch> batches = batchDao.getBatchDetails(new Timestamp(fromdate), new Timestamp(todate), session);
			Map<String, Integer> curriculumData = new HashMap<>();
			List<String> curriculums = new ArrayList<>();
			List<Map<String, Object>> chartData = new ArrayList<>();
			for (TfBatch batch : batches) {
				TfCurriculum curriculum = batch.getTfCurriculum();
				String curriculumName = (curriculum != null) ? batch.getTfBatchName() : OTHER_VALUE;
				if (curriculumData.containsKey(curriculumName)) {
					int moreAssociates = batch.getTfAssociates().size();
					int totalAssociates = curriculumData.get(curriculumName) + moreAssociates;
					curriculumData.put(curriculumName, totalAssociates);
				} else {
					int totalAssociates = batch.getTfAssociates().size();
					curriculumData.put(curriculumName, totalAssociates);
					curriculums.add(curriculumName);
				}
			}
			for (String curriculum : curriculums) {
				Map<String, Object> curriculumMap = new HashMap<>();
				curriculumMap.put("curriculum", curriculum);
				curriculumMap.put("value", curriculumData.get(curriculum));
				chartData.add(curriculumMap);
			}

			session.flush();
			tx.commit();

			return chartData;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("oculd not get batch info", e);
		}
		 finally {
			 session.close();
		 }
	}

	/**
	 * When given a batch name returns an object that contains all information about
	 * that batch
	 *
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A list with batch name, client name, curriculum name, batch
	 *         location, batch start date, and batch end date.
	 * @throws IOException
	 */
	@GET
	@Path("{batch}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public BatchInfo getBatchInfo(@PathParam("batch") String batchName) throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			BatchDaoHibernate batchDao = new BatchDaoHibernate();
			TfBatch batch = batchDao.getBatch(batchName, session);
			
			session.flush();
			tx.commit();
			
			return batchToInfo(batch);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("could not get batches", e);
		}
	}

	/**
	 * Gets the number of associates that are mapped and unmapped within a
	 * particular batch
	 *
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A map with the key being either Mapped or Unmapped and the value
	 *         being the number of associates in those statuses.
	 * @throws IOException
	 */
	@GET
	@Path("{batch}/batchChart")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Integer> getMappedData(@PathParam("batch") String batchName) throws IOException {
		Map<String, Integer> mappedChartData = new HashMap<>();
		
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch selectedBatch = batchDao.getBatch(batchName, session);
		int unmappedCount = 0;
		int mappedCount = 0;
		for (TfAssociate associate : selectedBatch.getTfAssociates()) {
			if (associate.getTfMarketingStatus().getTfMarketingStatusName().contains("UNMAPPED")) {
				unmappedCount++;
			} else if (associate.getTfMarketingStatus().getTfMarketingStatusName().contains("TERMINATED")
					|| associate.getTfMarketingStatus().getTfMarketingStatusName().contains("DIRECTLY")) {
				continue;
			} else {
				mappedCount++;
			}
		}
		mappedChartData.put("Unmapped", unmappedCount);
		mappedChartData.put("Mapped", mappedCount);
		
		session.flush();
		tx.commit();
		return mappedChartData;
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("can not get mapped data", e);
		}

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
	@Produces(MediaType.APPLICATION_JSON)
	public List<BatchInfo> getBatches(@PathParam("fromdate") long fromdate, @PathParam("todate") long todate)
			throws IOException {
		ArrayList<BatchInfo> batchesList = new ArrayList<>();
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		List<TfBatch> list = batchDao.getBatchDetails(new Timestamp(fromdate), new Timestamp(todate), session);

		for (TfBatch batch : list) {
			BatchInfo info = batchToInfo(batch);
			batchesList.add(info);
		}
		
		session.flush();
		tx.commit();
		
		return batchesList;
		} catch(Exception e) {
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
	public List<AssociateInfo> getAssociates(@PathParam("batch") String batchName) throws IOException {
		ArrayList<AssociateInfo> associatesList = new ArrayList<>();

		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch(batchName, session);

		for (TfAssociate associate : batch.getTfAssociates()) {

			if (associate.getTfMarketingStatus().getTfMarketingStatusName().equals("TERMINATED")
					|| associate.getTfMarketingStatus().getTfMarketingStatusName().equals("DIRECTLY PLACED")) {
				continue;
			}
			AssociateInfo associateInfo = new AssociateInfo();
			associateInfo.setId(associate.getTfAssociateId());
			associateInfo.setFirstName(associate.getTfAssociateFirstName());
			associateInfo.setLastName(associate.getTfAssociateLastName());
			associateInfo.setMarketingStatus(associate.getTfMarketingStatus().getTfMarketingStatusName());
			try {
				associateInfo.setClient(associate.getTfClient().getTfClientName());
			} catch (NullPointerException e) {
				associateInfo.setClient("None");
				LogUtil.logger.error(e);
			}

			session.flush();
			tx.commit();
			
			associatesList.add(associateInfo);
		}
		return associatesList;
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("coudl not get associates", e);
		}
		finally {
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
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("could not update associates", e);
		}
		finally {
			session.close();
		}
	}

    /**
     * map TfBatch object to format consumed by front end, properly
     * checking for null values
     *
     * @param batch
     * @return
     */
	private BatchInfo batchToInfo(TfBatch batch) {
		String batchName = batch.getTfBatchName();
		Timestamp start = batch.getTfBatchStartDate();
		Timestamp end = batch.getTfBatchEndDate();
		TfCurriculum curriculum = batch.getTfCurriculum();
		TfBatchLocation location = batch.getTfBatchLocation();

		String startDate = (start != null) ? start.toString() : UNKNOWN_VALUE;
		String endDate = (end != null) ? end.toString() : UNKNOWN_VALUE;
		String curriculumName = (curriculum != null) ? curriculum.getTfCurriculumName() : OTHER_VALUE;
		String locationName = (location != null) ? location.getTfBatchLocationName() : OTHER_VALUE;

		return new BatchInfo(batchName, curriculumName, locationName, startDate, endDate);
	}
}
