package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

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

    /**
     * Retrieve information about a specific associate.
     * 
     * @param associateid
     *            - The ID of the associate to get information about
     * @return - An AssociateInfo object that contains the associate's information.
     * @throws IOException 
     */
    @GET
    @Path("{associateid}")
    @Produces(MediaType.APPLICATION_JSON)
    public AssociateInfo getAssociate(@PathParam("associateid") BigDecimal associateid) throws IOException {
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

        if (associate.getTfBatch() != null) {
            associateinfo.setBatchName(associate.getTfBatch().getTfBatchName());
        } else {
            associateinfo.setBatchName("None");
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
     * @throws IOException 
     */
    @GET
    @Path("{associateId}/update/{marketingStatus}/{client}")
    @Produces({ MediaType.TEXT_HTML })
    public Response updateAssociate(@PathParam("associateId") String id, @PathParam("marketingStatus") String marketingStatus,
            @PathParam("client") String client) throws IOException {
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

    /**
     * Gets a list of all the associates. If an associate has no marketing status or
     * curriculum, replaces them with blanks. If associate has no client, replaces
     * it with "None".
     * 
     * @return - A Response object with a list of TfAssociate objects.
     * @throws IOException 
     * @throws HibernateException 
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssociates() throws HibernateException, IOException {
        List<TfAssociate> tfAssociates = homeDaoImpl.getAllTfAssociates();
        List<AssociateInfo> associateInfos = new ArrayList<>();
        for (TfAssociate tfAssociate : tfAssociates) {
            if (tfAssociate.getTfMarketingStatus().getTfMarketingStatusName().equals("TERMINATED")
                    || tfAssociate.getTfMarketingStatus().getTfMarketingStatusName().equals("DIRECTLY PLACED")) {
                continue;
            }
            BigDecimal tfAssociateId = tfAssociate.getTfAssociateId();
            String tfAssociateFirstName = tfAssociate.getTfAssociateFirstName();
            String tfAssociateLastName = tfAssociate.getTfAssociateLastName();
            String tfMarketingStatusName = tfAssociate.getTfMarketingStatus() != null ? tfAssociate.getTfMarketingStatus().getTfMarketingStatusName() : "";
            String tfClientName = tfAssociate.getTfClient() != null ? tfAssociate.getTfClient().getTfClientName() : "None";
            String tfBatchName = tfAssociate.getTfBatch() != null ? tfAssociate.getTfBatch().getTfBatchName() : "";

            String tfCurriculum;
            if (tfAssociate.getTfBatch() != null && tfAssociate.getTfBatch().getTfCurriculum() != null) {
                tfCurriculum = tfAssociate.getTfBatch().getTfCurriculum().getTfCurriculumName();
            } else {
                tfCurriculum = "";
            }

            associateInfos.add(new AssociateInfo(tfAssociateId, tfAssociateFirstName, tfAssociateLastName, tfMarketingStatusName, tfClientName, tfBatchName,
                    tfCurriculum));
        }
        return Response.ok(associateInfos).build();
    }
    /**
	 * Update the marketing status or client of multiple associates
	 * @param ids to be updated
	 * @param marketingStatus to be updated to
	 * @param client to be updated to
	 * @return 
	 * @throws IOException 
	 */
	@PUT
	@Path("/update/{marketingStatus}/{client}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociates(int[] ids, @PathParam("marketingStatus") String marketingStatus,
			@PathParam("client") String client) throws IOException {
		MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
		TfMarketingStatus status = marketingStatusDao.getMarketingStatus(marketingStatus);

		if (status == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
		}

		ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
		TfClient tfclient = clientDaoImpl.getClient(client);

		for (int id : ids) {
			BigDecimal associateID = new BigDecimal(id);

			AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
			associateDaoHibernate.updateInfo(associateID, status, tfclient);
		}
		
		return Response.status(Response.Status.OK).entity("Updated the associate's information.").build();
	}
}