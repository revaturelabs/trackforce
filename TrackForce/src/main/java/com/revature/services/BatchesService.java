package com.revature.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
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

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.BatchDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

/**
 * Class that provides RESTful services for the batch listing and batch details
 * page.
 */
@Path("batches")
public class BatchesService {

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
	 */
	@GET
	@Path("{fromdate}/{todate}/type")
	@Produces({ MediaType.APPLICATION_JSON })
	public Map<String, Integer> getBatchChartInfo(@PathParam("fromdate") long fromdate,
			@PathParam("todate") long todate) {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();

		List<TfBatch> batches = batchDao.getBatchDetails(new Timestamp(fromdate), new Timestamp(todate));
		Map<String, Integer> chartData = new Hashtable<String, Integer>();

		for (TfBatch batch : batches) {
			String curriculumName = batch.getTfCurriculum().getTfCurriculumName();

			if (chartData.containsKey(curriculumName)) {
				int moreAssociates = batch.getTfAssociates().size();
				int totalAssociates = chartData.get(curriculumName) + moreAssociates;
				chartData.put(curriculumName, totalAssociates);
			} else {
				int totalAssociates = batch.getTfAssociates().size();
				chartData.put(curriculumName, totalAssociates);
			}
		}
		return chartData;
	}

	/**
	 * When given a batch name returns an object that contains all information about
	 * that batch
	 * 
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A list with batch name, client name, curriculum name, batch
	 *         location, batch start date, and batch end date.
	 */
	@GET
	@Path("{batch}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public BatchInfo getBatchInfo(@PathParam("batch") String batchName) {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch(batchName);

		String name = batch.getTfBatchName();
		String curriculumName = batch.getTfCurriculum().getTfCurriculumName();
		String batchLocation = batch.getTfBatchLocation().getTfBatchLocationName();
		String startDate = batch.getTfBatchStartDate().toString();
		String endDate = batch.getTfBatchEndDate().toString();

		BatchInfo batchInfo = new BatchInfo(name, curriculumName, batchLocation, startDate, endDate);

		return batchInfo;
	}

	/**
	 * Gets the number of associates that are mapped and unmapped within a
	 * particular batch
	 * 
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A map with the key being either Mapped or Unmapped and the value
	 *         being the number of associates in those statuses.
	 */
	@GET
	@Path("{batch}/batchChart")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Integer> getMappedData(@PathParam("batch") String batchName) {
		Map<String, Integer> mappedChartData = new Hashtable<String, Integer>();
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch selectedBatch = batchDao.getBatch(batchName);
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
		return mappedChartData;

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
	 */
	@GET
	@Path("{fromdate}/{todate}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BatchInfo> getBatches(@PathParam("fromdate") long fromdate, @PathParam("todate") long todate) {
		ArrayList<BatchInfo> batchesList = new ArrayList<BatchInfo>();

		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		List<TfBatch> list = batchDao.getBatchDetails(new Timestamp(fromdate), new Timestamp(todate));

		for (TfBatch batch : list) {
			
			String batchName = batch.getTfBatchName();
			String startDate = batch.getTfBatchStartDate().toString();
			String endDate = batch.getTfBatchEndDate().toString();

			BatchInfo batchDetails = new BatchInfo(batchName, startDate, endDate);

			batchesList.add(batchDetails);
		}

		System.out.println(batchesList);
		return batchesList;
	}

	/**
	 * Gets the information of the associates in a particular batch
	 * 
	 * @param batchName
	 *            - the name of a batch that is in the database
	 * @return - A list of the lists of associate info. Associate info contains id,
	 *         first name, last name, and marketing status.
	 */
	@GET
	@Path("{batch}/associates")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AssociateInfo> getAssociates(@PathParam("batch") String batchName) {
		ArrayList<AssociateInfo> associatesList = new ArrayList<AssociateInfo>();

		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch(batchName);

		for (TfAssociate associate : batch.getTfAssociates()) {
			if (associate.getTfMarketingStatus().getTfMarketingStatusName().equals("TERMINATED")
					|| associate.getTfMarketingStatus().getTfMarketingStatusName().equals("DIRECTLY PLACED")) {
				continue;
			}
			BigDecimal id = associate.getTfAssociateId();
			String firstName = associate.getTfAssociateFirstName();
			String lastName = associate.getTfAssociateLastName();
			String marketingStatus = associate.getTfMarketingStatus().getTfMarketingStatusName();
			AssociateInfo associateDetails = new AssociateInfo(id, firstName, lastName, marketingStatus, "");

			associatesList.add(associateDetails);
		}
		return associatesList;
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
	 */
	@PUT
	@Path("{associate}/update")
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociate(@FormParam("id") String id, @FormParam("marketingStatus") String marketingStatus,
			@FormParam("client") String client) {
		MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
		TfMarketingStatus status = marketingStatusDao.getMarketingStatus(marketingStatus);

		ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
		TfClient tfclient = clientDaoImpl.getClient(client);

		BigDecimal associateID = new BigDecimal(Integer.parseInt(id));

		AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
		associateDaoHibernate.updateInfo(associateID, status, tfclient);

		return Response.status(Response.Status.OK).entity("Updated the associate's information").build();
	}
}
