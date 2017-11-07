package com.revature.services;

import java.sql.Timestamp;
import java.util.Hashtable;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;

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
	 */
	@GET
	@Path("{fromdate}/{todate}/type")
	@Produces({ MediaType.APPLICATION_JSON })
	public Map<String, Integer> getBatchChartInfo(@PathParam("fromDate") long fromdate,
			@PathParam("toDate") long todate) {
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
	 */
	@GET
	@Path("{batch}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public TfBatch getBatchInfo(@PathParam("batch") String batchName) {
		return new BatchDaoHibernate().getBatch(batchName);
	}

	/**
	 * Gets the number of associates that are mapped and unmapped within a
	 * particular batch
	 * 
	 * @param batchName
	 *            - the name of a batch that is in the database
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
	 */
	@Path("{fromdate}/{todate}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TfBatch> getBatches(@PathParam("fromdate") long fromdate, @PathParam("todate") long todate) {
		return new BatchDaoHibernate().getBatchDetails(new Timestamp(fromdate), new Timestamp(todate));
	}

}
