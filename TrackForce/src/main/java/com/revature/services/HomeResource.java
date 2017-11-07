package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.model.StatusInfo;

@Path("/")
public class HomeResource {
	
	private HomeDao homeDaoImpl = new HomeDaoImpl();

	/**
	 * 
	 * @return a StatusInfo object
	 */
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getMappedAndUnmappedInfo() {
		List<TfAssociate> associates = homeDaoImpl.getAllTfAssociates();
		StatusInfo statusInfo = countAssociatesBasedOnStatus(associates);
		statusInfo.setName("All associates' mapped/unmapped info");
		return statusInfo;
	}

	private StatusInfo countAssociatesBasedOnStatus(List<TfAssociate> associates) {
		StatusInfo statusInfo = new StatusInfo();
		for (TfAssociate associate : associates) {
			switch (associate.getTfMarketingStatus().getTfMarketingStatusId().intValue()) {
			case 1:
				statusInfo.setTrainingMapped(statusInfo.getTrainingMapped() + 1);
				break;
			case 2:
				statusInfo.setReservedMapped(statusInfo.getReservedMapped() + 1);
				break;
			case 3:
				statusInfo.setSelectedMapped(statusInfo.getSelectedMapped() + 1);
				break;
			case 4:
				statusInfo.setConfirmedMapped(statusInfo.getConfirmedMapped() + 1);
				break;
			case 5:
				statusInfo.setDeployedMapped(statusInfo.getDeployedMapped() + 1);
				break;
			case 6:
				statusInfo.setTrainingUnmapped(statusInfo.getTrainingUnmapped() + 1);
				break;
			case 7:
				statusInfo.setOpenUnmapped(statusInfo.getOpenUnmapped() + 1);
				break;
			case 8:
				statusInfo.setSelectedUnmapped(statusInfo.getSelectedUnmapped() + 1);
				break;
			case 9:
				statusInfo.setConfirmedUnmapped(statusInfo.getConfirmedUnmapped() + 1);
				break;
			case 10:
				statusInfo.setDeployedUnmapped(statusInfo.getDeployedUnmapped() + 1);
				break;
			}
		}
		return statusInfo;
	}
	
	private List<TfAssociate> associatesById(int id){
		return null;
	}
}
