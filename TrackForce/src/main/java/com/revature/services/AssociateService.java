package com.revature.services;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.model.AssociateInfo;

@Path("associates")
public class AssociateService {

	@GET
	@Path("{associateid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AssociateInfo getAssociate(@PathParam("associateid")BigDecimal associateid)
	{
		AssociateDaoHibernate associatedao=new AssociateDaoHibernate();
		TfAssociate associate=associatedao.getAssociate(associateid);
		AssociateInfo associateinfo=new AssociateInfo();
		associateinfo.setId(associate.getTfAssociateId());
		associateinfo.setFirstName(associate.getTfAssociateFirstName());
		associateinfo.setLastName(associate.getTfAssociateLastName());
		associateinfo.setMarketingStatus(associate.getTfMarketingStatus().getTfMarketingStatusName());
		associateinfo.setClient(associate.getTfClient().getTfClientName());
		return associateinfo;
		
	}
	
}
