package com.revature.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.TechDao;
import com.revature.dao.TechDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.TechInfo;

public class TechService implements Service {

    private TechDao TechDao;
    private AssociateDao associateDao;

    public TechService() {
        this.TechDao = new TechDaoHibernate();
        this.associateDao = new AssociateDaoHibernate();
    }

    /**
     * injectable dao for easier testing
     *
     * @param TechDao
     */
    public TechService(TechDao TechDao) {
        this.TechDao = TechDao;
    }

    private Set<TechInfo> getAllTechs() throws HibernateException, IOException{
//		Set<TechInfo> currs = PersistentStorage.getStorage().getTechs();
    	Set<TechInfo> currs = TechDao.getTechFromCache();
		if(currs == null || currs.isEmpty()) {
			execute();
//			return PersistentStorage.getStorage().getTechs();
			return TechDao.getTechFromCache();
		}
		return currs;
	}

    /**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of Tech) <br>
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
//		Set<AssociateInfo> associates = PersistentStorage.getStorage().getAssociates();
		Set<AssociateInfo> associates = associateDao.getAllAssociates();
		if (associates == null) {
			execute();
//			associates = PersistentStorage.getStorage().getAssociates();
			associates = associateDao.getAllAssociates();
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


	public Set<TechInfo> getTechs() throws HibernateException, IOException{
		return TechDao.getTechFromCache();
	}

	@Override
	public synchronized void execute() throws IOException {
//		Set<TechInfo> ti = PersistentStorage.getStorage().getTechs();
		Set<TechInfo> ti = TechDao.getTechFromCache();
		if(ti == null || ti.isEmpty())
//			PersistentStorage.getStorage().setTechs(TechDao.getAllTechs());
			new TechDaoHibernate().cacheAllTechs();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllTechs();
	}
}
