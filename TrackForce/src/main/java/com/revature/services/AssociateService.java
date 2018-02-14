package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

    public AssociateService() {

        this.associateDao = new AssociateDaoHibernate();

    }

	/**
	 * Retrieve information about a specific associate.
	 *
	 * @param associateid - The ID of the associate to get information about
	 * @return - An AssociateInfo object that contains the associate's information.
	 * @throws IOException
	 */

	public AssociateInfo getAssociate(Integer associateid) {
		AssociateInfo associateinfo = associateDao.getAssociate(associateid);
		return associateinfo;

	}
	public Response updateAssociates(List<AssociateInfo> associates) {
		associateDao.updateAssociates(associates);
		return Response.status(200).build();
	}

	//The method used to populate all of the data onto TrackForce
    //Doesn't work correctly at the moment
    public Response updateAssociates(
    		List<Integer> associateids,
    		Integer marketingStatus,
    		Integer clientid) {
    	//System.out.println("Got something with UpdateAssociate:" + associateinfo);
    	System.out.println(associateids.toString());
    	associateDao.updateAssociates(associateids, marketingStatus, clientid);
    	return Response.status(200).build();
    }
    /**
     * 
     * @return
     */

	public Set<AssociateInfo> getAllAssociates(){
		return AssociateDaoHibernate.getAllAssociates();
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
