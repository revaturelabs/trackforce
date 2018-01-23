package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.AssociateInfo;
import com.revature.model.ClientInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumJSON;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

@Path("associates")
public class AssociateService implements Delegate {

    private AssociateDao associateDao;

    public AssociateService() {
        this.associateDao = new AssociateDaoHibernate();
    }

    /**
     * injectable dao for testing
     *
     * @param associateDao
     */
    public AssociateService(AssociateDao associateDao) {
        this.associateDao = associateDao;
    }

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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {

			AssociateInfo associateinfo = associateDao.getAssociate(associateid, session);

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
	 * @param marketingStatusId
	 *            - What to change the associate's marketing status to
	 * @param clientId
	 *            - What client to change the associate to
	 * @return
	 * @throws IOException
	 */
	@PUT
	@Path("{associateId}/update/{marketingStatusId}/{clientId}")
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociate(@PathParam("associateId") String id,
                                    @PathParam("marketingStatusId") String marketingStatusId,
                                    @PathParam("clientId") String clientId) {
		return updateAssociates(new int[] { Integer.parseInt(id) }, marketingStatusId, clientId);
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
		if (associates == null || associates.isEmpty()) {
			execute();
			return PersistentStorage.getStorage().getAssociates();
		}
		return associates;
	}

    /**
     * fetch associates from database
     *
     * @return
     * @throws HibernateException
     * @throws IOException
     */
	private Map<BigDecimal, AssociateInfo> getAssociates() throws HibernateException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Map<BigDecimal, AssociateInfo> tfAssociates = associateDao.getAssociates(session);
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
	 * 
	 * @param ids
	 *            to be updated
	 * @param marketingStatusIdStr
	 *            updating to
	 * @param clientIdStr
	 *            updating to
	 * @return
	 * @throws IOException
	 */
	@PUT
	@Path("/update/{marketingStatusId}/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAssociates(int[] ids, @PathParam("marketingStatusId") String marketingStatusIdStr,
			@PathParam("clientId") String clientIdStr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			int statusId = Integer.parseInt(marketingStatusIdStr);
			int clientId = Integer.parseInt(clientIdStr);

			ClientInfo tfclient = PersistentStorage.getStorage().getClientAsMap().get(new BigDecimal(clientId));
			MarketingStatusInfo msi = PersistentStorage.getStorage().getMarketingAsMap().get(new BigDecimal(statusId));

			if (msi == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
			}

			Map<BigDecimal, AssociateInfo> map = new HashMap<>();
			for (int id : ids) {
				AssociateInfo ai = PersistentStorage.getStorage().getAssociateAsMap().get(new BigDecimal(id));
				ClientInfo old = PersistentStorage.getStorage().getClientAsMap().get(ai.getClid());

				// subtract old values
				if (old != null) {
					if (ai.getMsid() != null)
						old.getStats().subtractFromMap(ai.getMsid());
					old.getTfAssociates().remove(ai);
				}

				// add new values
				// since all the resources are available to us, we can update storage here
				// without having to hit the DB
				BigDecimal oldms = ai.getMsid();
				tfclient.getStats().appendToMap(msi.getId());
				tfclient.getTfAssociates().add(ai);
				ai.setMarketingStatusId(msi.getId());
				ai.setMarketingStatus(msi.getName());
				ai.setClid(tfclient.getId());
				ai.setClient(tfclient.getTfClientName());

				// write to DB
				associateDao.updateInfo(session, ai.getId(), msi, tfclient);

				map.put(ai.getId(), ai);
				PersistentStorage.getStorage().getTotals().appendToMap(msi.getId());
				if(oldms != null && !oldms.equals(new BigDecimal(-1)))
					PersistentStorage.getStorage().getTotals().subtractFromMap(oldms);
			}
			session.flush();
			tx.commit();

			// update Persistent storage
			PersistentStorage.getStorage().updateAssociates(map);

			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Updated the associate's information.")
					.build();
		} finally {
			session.close();
		}
	}

	/**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of curriculum) <br>
	 * count: (count of desired status)
	 * 
	 * @param statusid
	 *            Status id of the status/stage of associates that the requester
	 *            wants information for.
	 * @return a Response object with a List of Map objects as an entity.
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Path("client/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClients(@PathParam("statusid") int statusid) throws HibernateException, IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if (associates == null) {
			execute();
			associates = PersistentStorage.getStorage().getAssociates();
		}

		Map<BigDecimal, ClientMappedJSON> map = new HashMap<>();
		for (AssociateInfo ai : associates) {
			if (ai.getMsid().equals(new BigDecimal(statusid))) {
				if (!map.containsKey(ai.getClid())) {
					map.put(ai.getClid(), new ClientMappedJSON());
				}
				if (ai.getClient() != null && !ai.getClid().equals(new BigDecimal(-1))) {
					map.get(ai.getClid()).setCount(map.get(ai.getClid()).getCount() + 1);
					map.get(ai.getClid()).setId(ai.getClid().intValueExact());
					map.get(ai.getClid()).setName(ai.getClient());
				}
			}
		}
		return Response.ok(map.values()).build();
	}

	/**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of curriculum) <br>
	 * count: (count of desired status)
	 * 
	 * @param statusid
	 *            Status id of the status/stage of associates that the requester
	 *            wants information for.
	 * @return a Response object with a List of Map objects as an entity.
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Path("skillset/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCurriculumsByStatus(@PathParam("statusid") int statusid) throws HibernateException, IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if (associates == null) {
			execute();
			associates = PersistentStorage.getStorage().getAssociates();
		}

		Map<BigDecimal, CurriculumJSON> map = new HashMap<>();
		Set<AssociateInfo> assocsByStatus = new TreeSet<AssociateInfo>();
		for (AssociateInfo ai : associates) {
			if (ai.getMsid().equals(new BigDecimal(statusid))) {
				if (!map.containsKey(ai.getCurid())) {
					map.put(ai.getCurid(), new CurriculumJSON());
				}
				if (ai.getCurriculumName() != null && !ai.getCurid().equals(new BigDecimal(-1))) {
					map.get(ai.getCurid()).setCount(map.get(ai.getCurid()).getCount() + 1);
					map.get(ai.getCurid()).setId(ai.getCurid().intValueExact());
					map.get(ai.getCurid()).setName(ai.getCurriculumName());
				}
			}
		}
		return Response.ok(map.values()).build();
	}

    /**
     * execute delegated task: fetch data from DB and cache it to storage
     *
     * @throws IOException
     */
	@Override
	public synchronized void execute() throws IOException {
		Set<AssociateInfo> ai = PersistentStorage.getStorage().getAssociates();
		if (ai == null || ai.isEmpty())
			PersistentStorage.getStorage().setAssociates(getAssociates());
	}

	@Override
	public <T> Set<T> read(String... args) throws IOException {
		return (Set<T>) getAllAssociates();
	}
}