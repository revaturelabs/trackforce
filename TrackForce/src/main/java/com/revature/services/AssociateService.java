package com.revature.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumJSON;
import com.revature.utils.PersistentStorage;

public class AssociateService implements Service {

    private AssociateDao associateDao;

    public AssociateService() {
        this.associateDao = new AssociateDaoHibernate();
    }

    /**
     * injectable dao for testing
     *
     * @param associateDao
     */
    public AssociateService(AssociateDao associateDao, SessionFactory sessionFactory) {
        this.associateDao = associateDao;
    }

	/**
	 * Retrieve information about a specific associate.
	 *
	 * @param associateid - The ID of the associate to get information about
	 * @return - An AssociateInfo object that contains the associate's information.
	 * @throws IOException
	 */
	public AssociateInfo getAssociate(BigDecimal associateid) {
		AssociateInfo associateinfo = associateDao.getAssociate(associateid);
		return associateinfo;
	}

	/**
	 * Update the marketing status or client of an associate from form data.
	 *
	 * @param id - The ID of the associate to change
	 * @param marketingStatusId - What to change the associate's marketing status to
	 * @param clientId - What client to change the associate to
	 * @return boolean
	 */
	public boolean updateAssociate(int id, int marketingStatusId, int clientId) {
		return associateDao.updateAssociate(new BigDecimal(id), marketingStatusId, clientId);
	}

	/**
	 * Gets a list of all the associates. If an associate has no marketing status or
	 * curriculum, replaces them with blanks. If associate has no client, replaces
	 * it with "None".
	 *
	 * @return - A Response object with a list of TfAssociate objects.
	 * @throws IOException
	 */

//	private Set<AssociateInfo> getAllAssociates() throws IOException {
//		System.out.println("getAllAssociates called");
//		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
//		if (associates == null || associates.isEmpty()) {
//			execute();
//			return PersistentStorage.getStorage().getAssociates();
//		}
//		return associates;
//	}

	//The method used to populate all of the data onto TrackForce
    //Doesn't work correctly at the moment
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("action/{marketingStatus}/{clientid}/")
    public Response updateAssociate(@QueryParam("id") BigDecimal[] associateids,
    		@PathParam("marketingStatus") BigDecimal marketingStatus,
    		@PathParam("clientid") BigDecimal clientid,
    		AssociateInfo associateinfo) {
    	System.out.println("Got something with UpdateAssociate:" + associateinfo);
    	associateDaoHib.updateAssociates(associateids, marketingStatus, clientid);
    	return Response.status(200).build();
    }
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<AssociateInfo> getAllAssociates(){

//		try {
			//for now, must use read method in respective service class to read
			//data from the cache and be able to send it to Angular
			//return read();
			return AssociateDaoHibernate.getAllAssociates();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//return null;
	}
    /**
     * fetch associates from database
     *
     * @return
     * @throws HibernateException
     * @throws IOException
     */
	public Map<BigDecimal, AssociateInfo> getAssociates() {
		Map<BigDecimal, AssociateInfo> map = associateDao.getAssociates();
		return map;
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
	public boolean updateAssociates(int[] ids, String marketingStatusIdStr, String clientIdStr) {
		int statusId = Integer.parseInt(marketingStatusIdStr);
		int clientId = Integer.parseInt(clientIdStr);
		BigDecimal[] arr = new BigDecimal[ids.length];
		for (int i=0;i<arr.length;i++) {
			arr[i] = new BigDecimal(ids[i]);
		}
		associateDao.updateAssociates(arr, statusId, clientId);
		return true;
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
