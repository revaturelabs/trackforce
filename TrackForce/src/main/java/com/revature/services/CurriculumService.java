package com.revature.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.revature.dao.CurriculumDao;
import com.revature.dao.CurriculumDaoImpl;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumInfo;
import com.revature.model.CurriculumJSON;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class CurriculumService implements Service {

    private CurriculumDao curriculumDao;
    private SessionFactory sessionFactory;

    public CurriculumService() {
        this.curriculumDao = new CurriculumDaoImpl();
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * injectable dao for easier testing
     *
     * @param curriculumDao
     */
    public CurriculumService(CurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Set<CurriculumInfo> getAllCurriculums() throws HibernateException, IOException{
		Set<CurriculumInfo> currs = PersistentStorage.getStorage().getCurriculums();
		if(currs == null || currs.isEmpty()) {
			execute();
			return PersistentStorage.getStorage().getCurriculums();
		}
		return currs;
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
	public Response getClients(int statusid) throws HibernateException, IOException {
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
					map.get(ai.getClid()).setId(ai.getClid().intValue());
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
	public Response getCurriculumsByStatus(int statusid) throws HibernateException, IOException {
		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		if (associates == null) {
			execute();
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
					map.get(ai.getCurid()).setId(ai.getCurid().intValue());
					map.get(ai.getCurid()).setName(ai.getCurriculumName());
				}
			}
		}
		return Response.ok(map.values()).build();
	}


	public Map<Integer, CurriculumInfo> getCurriculums() throws HibernateException, IOException{
		return curriculumDao.getAllCurriculums();
	}

	@Override
	public synchronized void execute() throws IOException {
		Set<CurriculumInfo> ci = PersistentStorage.getStorage().getCurriculums();
		if(ci == null || ci.isEmpty())
			PersistentStorage.getStorage().setCurriculums(getCurriculums());
	}

	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllCurriculums();
	}
}
