package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
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
import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

@Path("associates")
public class AssociateService implements Delegate {

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

			AssociateInfo associateinfo = associatedao.getAssociate(associateid, session);

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
	private Set<AssociateInfo> getAllAssociates() throws IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if(associates == null || associates.isEmpty()) {
			execute();
			return PersistentStorage.getStorage().getAssociates();
		}
		return associates;
	}
	private Map<BigDecimal, AssociateInfo> getAssociates() throws HibernateException, IOException{
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Map<BigDecimal, AssociateInfo> tfAssociates = new AssociateDaoHibernate().getAssociates(session);
			PersistentStorage.getStorage().setTotals(AssociateInfo.getTotals());
			
			session.flush();
			tx.commit();
			return tfAssociates;
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
	
	// execute delegated task: fetch data from DB and cache it to storage
	@Override
	public synchronized void execute() throws IOException {
		Set<AssociateInfo> ai = PersistentStorage.getStorage().getAssociates();
		if(ai == null || ai.isEmpty())
			PersistentStorage.getStorage().setAssociates(getAssociates());
	}

	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllAssociates();
	}
}
