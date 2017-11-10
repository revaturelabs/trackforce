package com.revature.services;

import java.math.BigDecimal;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;

@Path("associates")
public class AssociateService {

    @GET
    @Path("{associateid}")
    @Produces(MediaType.APPLICATION_JSON)
    public AssociateInfo getAssociate(@PathParam("associateid") BigDecimal associateid) {
        AssociateDaoHibernate associatedao = new AssociateDaoHibernate();
        TfAssociate associate = associatedao.getAssociate(associateid);
        System.out.println(associate.getTfMarketingStatus());
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
     * @param id - The ID of the associate to change
     * @param marketingStatus - What to change the associate's marketing status to
     * @param client - What client to change the associate to
     * @return - A http response with okay status
     */
    @PUT
    @Path("{associate}/update")
    @Produces({ MediaType.TEXT_HTML })
    public Response updateAssociate(@FormParam("id") String id, @FormParam("marketingStatus") String marketingStatus, @FormParam("client") String client) {
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
