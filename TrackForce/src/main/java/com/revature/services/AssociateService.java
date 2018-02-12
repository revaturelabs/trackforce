package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumJSON;
import com.revature.utils.PersistentStorage;

public class AssociateService implements Service {

    private AssociateDao associateDao;
    private AssociateDaoHibernate associateDaoHib = new AssociateDaoHibernate();

    public AssociateService() {
<<<<<<< HEAD
        this.associateDao = new AssociateDaoHibernate();   //this is the point at which the associateDao object inherits its methods n junkz - through conversion to associateDaoHibernate
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * injectable dao for testing
     *
     * @param associateDao
     */
    public AssociateService(AssociateDao associateDao, SessionFactory sessionFactory) {
        this.associateDao = associateDao;
        this.sessionFactory = sessionFactory;
=======
        this.associateDao = new AssociateDaoHibernate();
>>>>>>> cf1f9ffd1d7edcd0946e584885e2661a7868ef7f
    }

	/**
	 * Retrieve information about a specific associate.
	 *
	 * @param associateid - The ID of the associate to get information about
	 * @return - An AssociateInfo object that contains the associate's information.
	 * @throws IOException
	 */
<<<<<<< HEAD
	@GET
	@Path("{associateid}")
	@Produces(MediaType.APPLICATION_JSON)
	public AssociateInfo getAssociate(@PathParam("associateid") BigDecimal associateid) throws IOException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			AssociateInfo associateinfo = associateDao.getAssociate(associateid, session);  //Dao is fine. Move along.
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
	 * @throws NumberFormatException 
	 * @throws IOException
	 */
	@PUT
	@Path("{associateId}/update/{marketingStatusId}/{clientId}")
	@Produces({ MediaType.TEXT_HTML })
	public Response updateAssociate(@PathParam("associateId") String id,
                                    @PathParam("marketingStatusId") String marketingStatusId,
                                    @PathParam("clientId") String clientId) throws NumberFormatException, IOException {
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
=======
	public AssociateInfo getAssociate(Integer associateid) {
		AssociateInfo associateinfo = associateDao.getAssociate(associateid);
		return associateinfo;
>>>>>>> cf1f9ffd1d7edcd0946e584885e2661a7868ef7f
	}

	//The method used to populate all of the data onto TrackForce
    //Doesn't work correctly at the moment
    public Response updateAssociates(
    		Integer[] associateids,
    		Integer marketingStatus,
    		Integer clientid) {
    	//System.out.println("Got something with UpdateAssociate:" + associateinfo);
    	associateDaoHib.updateAssociates(associateids, marketingStatus, clientid);
    	return Response.status(200).build();
    }
    /**
     * 
     * @return
     */
<<<<<<< HEAD
	public Map<BigDecimal, AssociateInfo> getAssociates() throws HibernateException, IOException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Map<BigDecimal, AssociateInfo> tfAssociates = associateDao.getAssociates(session);
			PersistentStorage.getStorage().setTotals(AssociateInfo.getTotals());  //this is pulling from the cache instead of the database

			session.flush();
			tx.commit();
			return tfAssociates;
		} catch (Exception e) {
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
	@PUT           // UNDER SCRUTINY  -_-
	@Path("/update/{marketingStatusId}/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAssociates(int[] ids, @PathParam("marketingStatusId") String marketingStatusIdStr,  
			@PathParam("clientId") String clientIdStr) throws IOException { //takes a series of ids from the method on the Angular side
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			int statusId = Integer.parseInt(marketingStatusIdStr);
			int clientId = Integer.parseInt(clientIdStr);

			ClientInfo tfclient = PersistentStorage.getStorage().getClientAsMap().get(new BigDecimal(clientId));  // CACHE found
			MarketingStatusInfo msi = PersistentStorage.getStorage().getMarketingAsMap().get(new BigDecimal(statusId));

			if (msi == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Invalid marketing status sent.").build();
			}

			Map<BigDecimal, AssociateInfo> map = new HashMap<>(); // 'map' is what is fed into the .getstorage.updateAssociates() method to update the cahche; learn where it gets its info from.
			for (int id : ids) {   //enter all ids into maps of various types
				AssociateInfo ai = PersistentStorage.getStorage().getAssociateAsMap().get(new BigDecimal(id)); // this returns an empty Hashmap of the specified object type, then fitted with id
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
				BigDecimal oldms = ai.getMsid(); //remember 'ai' variable is of type AssociateInfo, which at this point is empty safe for an id
				tfclient.getStats().appendToMap(msi.getId());
				tfclient.getTfAssociates().add(ai);
				ai.setMarketingStatusId(msi.getId());
				ai.setMarketingStatus(msi.getName());
				ai.setClid(tfclient.getId());
				ai.setClient(tfclient.getTfClientName());

				// write to DB
				associateDao.updateInfo(session, ai.getId(), msi, tfclient);

				map.put(ai.getId(), ai);  //id key and associated object of AssociateInfo type placed into 'map'
				PersistentStorage.getStorage().getTotals().appendToMap(msi.getId());
				if(oldms != null && !oldms.equals(new BigDecimal(-1)))
					PersistentStorage.getStorage().getTotals().subtractFromMap(oldms);
			}
			session.flush();
			tx.commit();

			// update Persistent storage
			PersistentStorage.getStorage().updateAssociates(map);  //associateinfo in 'map' then parsed to PersistentStorage for entry into the cache 

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
=======
	public Set<AssociateInfo> getAllAssociates(){
			//for now, must use read method in respective service class to read
			//data from the cache and be able to send it to Angular
			//return read();
			return AssociateDaoHibernate.getAllAssociates();
>>>>>>> cf1f9ffd1d7edcd0946e584885e2661a7868ef7f
	}

	/**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of curriculum) <br>
	 * count: (count of desired status)
	 *
	 * @param statusid - Status id of the status/stage of associates that the requester wants information for.
	 * @return a Response object with a List of Map objects as an entity.
	 * @throws IOException
	 */
	public Collection<ClientMappedJSON> getAssociatesByStatus(int statusid) throws IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if (associates == null) {
			execute();
			associates = PersistentStorage.getStorage().getAssociates();
		}

		Map<Integer, ClientMappedJSON> map = new HashMap<>();
		for (AssociateInfo ai : associates) {
			if (ai.getMsid().equals(new Integer(statusid))) {
				if (!map.containsKey(ai.getClid())) {
					map.put(ai.getClid(), new ClientMappedJSON());
				}
				if (ai.getClient() != null && !ai.getClid().equals(new Integer(-1))) {
					map.get(ai.getClid()).setCount(map.get(ai.getClid()).getCount() + 1);
					map.get(ai.getClid()).setId(ai.getClid());
					map.get(ai.getClid()).setName(ai.getClient());
				}
			}
		}
		return map.values();
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
	public Collection<CurriculumJSON> getCurriculumsByStatus(int statusid) throws HibernateException, IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if (associates == null) {
			//execute();
			associates = PersistentStorage.getStorage().getAssociates();
		}

		Map<Integer, CurriculumJSON> map = new HashMap<>();
		for (AssociateInfo ai : associates) {
			if (ai.getMsid().equals(new Integer(statusid))) {
				if (!map.containsKey(ai.getCurid())) {
					map.put(ai.getCurid(), new CurriculumJSON());
				}
				if (ai.getCurriculumName() != null && !ai.getCurid().equals(new Integer(-1))) {
					map.get(ai.getCurid()).setCount(map.get(ai.getCurid()).getCount() + 1);
					map.get(ai.getCurid()).setId(ai.getCurid());
					map.get(ai.getCurid()).setName(ai.getCurriculumName());
				}
			}
		}
		return map.values();
	}

    /**
     * execute delegated task: fetch data from DB and cache it to storage
     *
     * @throws IOException
     */
	@Override
	public synchronized void execute() throws IOException {
//		Set<AssociateInfo> ai = PersistentStorage.getStorage().getAssociates();
//		if (ai == null || ai.isEmpty())
//			PersistentStorage.getStorage().setAssociates(getAssociates());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> read(String... args) throws IOException {
		return (Set<T>) getAllAssociates();
	}
}
