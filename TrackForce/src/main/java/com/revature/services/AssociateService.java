package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDaoImpl;
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

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
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
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

			tx.commit();
			return associateinfo;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new IOException("Could not get associate", e);
		} finally {
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
	@Path("{associateId}/update/{marketingStatus}/{client}")
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociate(@PathParam("associateId") String id,
			@PathParam("marketingStatus") String marketingStatus, @PathParam("client") String client)
					throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
			TfMarketingStatus status = marketingStatusDao.getMarketingStatus(session, marketingStatus);

			if (status == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
			}

			ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
			TfClient tfclient = clientDaoImpl.getClient(session, client);

			BigDecimal associateID = new BigDecimal(Integer.parseInt(id));

			AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
			associateDaoHibernate.updateInfo(session, associateID, status, tfclient);

			session.flush();
			tx.commit();

			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("can not update associate", e);
		}
		finally {
			session.close();
		}
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
	public Response getAllAssociates() throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<TfAssociate> tfAssociates = homeDaoImpl.getAllTfAssociates(session);
			Set<AssociateInfo> associateInfos = new TreeSet<>();
			for (TfAssociate tfAssociate : tfAssociates) {
				if (tfAssociate.getTfMarketingStatus() == null || tfAssociate.getTfMarketingStatus().getTfMarketingStatusName().equals("TERMINATED")
						|| tfAssociate.getTfMarketingStatus().getTfMarketingStatusName().equals("DIRECTLY PLACED")) {
					continue;
				}
				BigDecimal tfAssociateId = tfAssociate.getTfAssociateId();
				String tfAssociateFirstName = tfAssociate.getTfAssociateFirstName();
				String tfAssociateLastName = tfAssociate.getTfAssociateLastName();
				String tfMarketingStatusName = tfAssociate.getTfMarketingStatus() != null
						? tfAssociate.getTfMarketingStatus().getTfMarketingStatusName()
								: "";
						String tfClientName = tfAssociate.getTfClient() != null ? tfAssociate.getTfClient().getTfClientName()
								: "None";
						String tfBatchName = tfAssociate.getTfBatch() != null ? tfAssociate.getTfBatch().getTfBatchName() : "";

						String tfCurriculum;
						if (tfAssociate.getTfBatch() != null && tfAssociate.getTfBatch().getTfCurriculum() != null) {
							tfCurriculum = tfAssociate.getTfBatch().getTfCurriculum().getTfCurriculumName();
						} else {
							tfCurriculum = "";
						}

						associateInfos.add(new AssociateInfo(tfAssociateId, tfAssociateFirstName, tfAssociateLastName,
								tfMarketingStatusName, tfClientName, tfBatchName, tfCurriculum));
			}

			session.flush();
			tx.commit();
			return Response.ok(associateInfos).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("cannot get associates", e);
		} finally {
			session.close();
		}
	}

	/**
	 * Update the marketing status or client of associates
	 * @param ids to be updated
	 * @param marketingStatus updating to
	 * @param client updating to
	 * @return
	 * @throws IOException 
	 */
	@PUT
	@Path("/update/{marketingStatus}/{client}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAssociates(int[] ids, @PathParam("marketingStatus") String marketingStatus,
			@PathParam("client") String client) throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			MarketingStatusDao marketingStatusDao = new MarketingStatusDaoHibernate();
			TfMarketingStatus status = marketingStatusDao.getMarketingStatus(session, marketingStatus);

			if (status == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
			}

			ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
			TfClient tfclient = clientDaoImpl.getClient(session, client);

			for (int id : ids) {
				BigDecimal associateID = new BigDecimal(id);

				AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
				associateDaoHibernate.updateInfo(session, associateID, status, tfclient);
			}
			session.flush();
			tx.commit();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Updated the associate's information.").build();
		} finally {
			session.close();
		}
	}
}
