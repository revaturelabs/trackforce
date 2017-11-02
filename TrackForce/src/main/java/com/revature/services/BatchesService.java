package com.revature.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.BatchDaoHibernate;
import com.revature.dao.ClientNameDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;

@Path("batches")
public class BatchesService {
	
	@GET
	@Path("type")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, Integer> getBatchChartInfo(@FormParam("fromDate") Timestamp fromDate, 
			@FormParam("toDate") Timestamp toDate) {
		BatchDaoHibernate batchDao = new BatchDaoHibernate();
		AssociateDaoHibernate associateDao = new AssociateDaoHibernate();
		List<TfBatch> batches = batchDao.getBatchDetails(fromDate, toDate);
		Map<String, Integer> chartData = new Hashtable<String, Integer>();

		for (TfBatch batch : batches) {
			
			//TODO: update if/else with new method calls
			String curriculumName = batch.getTfCurriculum().getTfCurriculumName();
			BigDecimal curriculumId = batch.getTfCurriculum().getTfCurriculumId();

			if (chartData.containsKey(curriculumName)) {
				int moreAssociates = associateDao.getNoOfAssociates(curriculumId, fromDate, toDate);
				int totalAssociates = chartData.get(curriculumName) + moreAssociates;
				chartData.put(curriculumName, totalAssociates);
			}
			else {
				int totalAssociates = associateDao.getNoOfAssociates(curriculumId, fromDate, toDate);
				chartData.put(curriculumName, totalAssociates);
			}
		}
		return chartData;
	}
	
	@GET
	@Path("{batch}/associates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TfAssociate> getAssociateInfo(@PathParam("batch") String batchName) {
		AssociateDaoHibernate associateDao = new AssociateDaoHibernate();
		return associateDao.getAssociatesByBatch(batchName);
	}
	
	
	@GET
	@Path("{batch}/client")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClientInfo(@PathParam("batch") String batchName){
		ClientNameDaoHibernate clientNameDao = new ClientNameDaoHibernate();
		return clientNameDao.getClientName(batchName);
	}
	
	@GET 
	@Path("{batch}/info")
	@Produces(MediaType.APPLICATION_JSON)
	public TfBatch getBatchInfo(@PathParam("batch")String batchName) {
		//TODO: need to make this hibernate method call
		return hibernate.getBatch(batchName);
	}
	
	/** PENDING */
	@GET
	@Path("{batch}/batchChart")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Integer> getMappedData(@PathParam("batch") String batchName){
		Map<String, Integer> mappedChartData = new Hashtable<>();
		//TODO: need hibernate call that returns mapped numbers
		return mappedChartData;
	}
	
	@GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<TfBatch> getBatches(@MatrixParam("fromdate") String fromdate, @MatrixParam("todate") String todate ){
        
        return new BatchDaoHibernate().getBatchDetails(fromdate, todate);
    }

}
