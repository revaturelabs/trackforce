package com.revature.services;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.revature.dao.AssociateDao;
import com.revature.dao.TechDao;
import com.revature.dao.TechDaoHibernate;
import com.revature.model.TechInfo;
import com.revature.utils.PersistentStorage;

public class TechService implements Service {

    private TechDao TechDao;
    private AssociateDao associateDao;

    public TechService() {
        this.TechDao = new TechDaoHibernate();
        //this.associateDao = new AssociateDaoHibernate();
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

	public Map<Integer, TechInfo> getTechs() throws HibernateException, IOException{
		return TechDao.getAllTechs();
	}

	@Override
	public synchronized void execute() throws IOException {
//		Set<TechInfo> ti = PersistentStorage.getStorage().getTechs();
		Set<TechInfo> ti = TechDao.getTechFromCache();
		if(ti == null || ti.isEmpty())
			PersistentStorage.getStorage().setTechs(new TechDaoHibernate().getAllTechs());	
			//TechDaoHibernate.cacheAllTechs();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllTechs();
	}
}
