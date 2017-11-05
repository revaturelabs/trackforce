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
	
	@GET
	@Path("{fromdate}/{todate}/type")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, Integer> getBatchChartInfo(@PathParam("fromDate") String fromdate, 
			@PathParam("toDate") String todate) {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		Timestamp fromDate = new Timestamp(Long.valueOf(fromdate));
		Timestamp toDate = new Timestamp(Long.valueOf(todate));
		
		List<TfBatch> batches = batchDao.getBatchDetails(fromDate, toDate);
		Map<String, Integer> chartData = new Hashtable<String, Integer>();

		for (TfBatch batch : batches) {
			String curriculumName = batch.getTfCurriculum().getTfCurriculumName();

			if (chartData.containsKey(curriculumName)) {
				int moreAssociates = batch.getTfAssociates().size();
				int totalAssociates = chartData.get(curriculumName) + moreAssociates;
				chartData.put(curriculumName, totalAssociates);
			}
			else {
				int totalAssociates = batch.getTfAssociates().size();
				chartData.put(curriculumName, totalAssociates);
			}
		}
		return chartData;
	}
	

	
	@GET 
	@Path("{batch}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public TfBatch getBatchInfo(@PathParam("batch")String batchName) {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		return batchDao.getBatch(batchName);
	}
	
	@GET
	@Path("{batch}/batchChart")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Integer> getMappedData(@PathParam("batch") String batchName){
		Map<String, Integer> mappedChartData = new Hashtable<String, Integer>();
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		TfBatch selectedBatch = batchDao.getBatch(batchName);
		int unmappedCount = 0;
		int mappedCount = 0;
		for (TfAssociate associate : selectedBatch.getTfAssociates()) {
			if (associate.getTfMarketingStatus().getTfMarketingStatusName().contains("UNMAPPED")) {
				unmappedCount++;
			}
			else {
				mappedCount++;
			}
		}
		mappedChartData.put("UNMAPPED", unmappedCount);
		mappedChartData.put("MAPPED", mappedCount);
		return mappedChartData;
		
	}
	
	

	@Path("{fromdate}/{todate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TfBatch> getBatches(@PathParam("fromdate") String fromdate, @PathParam("todate") String todate ){
		System.out.println(fromdate);
        System.out.println(todate);
        return new BatchDaoHibernate().getBatchDetails(fromdate.substring(4, 15), todate.substring(4, 15));
    }

}
