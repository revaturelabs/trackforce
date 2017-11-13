package com.revature.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDaoImpl;
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;

@Path("associates")
public class AssociateService {

	private HomeDaoImpl homeDaoImpl = new HomeDaoImpl();

	@GET
	@Path("{associateid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AssociateInfo getAssociate(@PathParam("associateid") BigDecimal associateid) {
		AssociateDaoHibernate associatedao = new AssociateDaoHibernate();
		TfAssociate associate = associatedao.getAssociate(associateid);
		AssociateInfo associateinfo = new AssociateInfo();
		associateinfo.setId(associate.getTfAssociateId());
		associateinfo.setFirstName(associate.getTfAssociateFirstName());
		associateinfo.setLastName(associate.getTfAssociateLastName());

		if (associate.getTfMarketingStatus() != null) {
			associateinfo.setMarketingStatus(associate.getTfMarketingStatus().getTfMarketingStatusName());
		} else {
			associateinfo.setMarketingStatus("None");
		}

		if (associate.getTfClient() != null) {
			associateinfo.setClient(associate.getTfClient().getTfClientName());
		} else {
			associateinfo.setClient("None");
		}

		if (associate.getTfEndClient() != null) {
			associateinfo.setEndClient(associate.getTfEndClient().getTfEndClientName());
		} else {
			associateinfo.setEndClient("None");
		}
		return associateinfo;
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
	@GET
	@Path("{associateId}/update/{marketingStatus}/{client}")
	@Produces(MediaType.TEXT_HTML)
	public Response updateAssociate(@PathParam("associateId") String id,
			@PathParam("marketingStatus") String marketingStatus, @PathParam("client") String client) {
		MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
		TfMarketingStatus status = marketingStatusDao.getMarketingStatus(marketingStatus);

		if (status == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
		}

		ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
		TfClient tfclient = clientDaoImpl.getClient(client);

		BigDecimal associateID = new BigDecimal(Integer.parseInt(id));

		AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
		associateDaoHibernate.updateInfo(associateID, status, tfclient);

		return Response.status(Response.Status.OK).entity("Updated the associate's information.").build();
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAssociates() {
		List<TfAssociate> tfAssociates = homeDaoImpl.getAllTfAssociates();
		List<AssociateInfo> associateInfos = new ArrayList<>();
		for (TfAssociate tfAssociate : tfAssociates) {
			associateInfos.add(new AssociateInfo(tfAssociate.getTfAssociateId(), tfAssociate.getTfAssociateFirstName(),
					tfAssociate.getTfAssociateLastName(),
					tfAssociate.getTfMarketingStatus() != null
							? tfAssociate.getTfMarketingStatus().getTfMarketingStatusName()
							: "",
					tfAssociate.getTfClient() != null ? tfAssociate.getTfClient().getTfClientName() : "None",
					tfAssociate.getTfBatch() != null ? tfAssociate.getTfBatch().getTfBatchName() : ""));
		}
		return Response.ok(associateInfos).build();
	}
}
