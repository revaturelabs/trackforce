package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Path;

import com.revature.dao.CurriculumDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.CurriculumDaoImpl;
import com.revature.model.CurriculumInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

@Path("skillset")
public class CurriculumService implements Delegate {


    private CurriculumDao curriculumDao;

    public CurriculumService() {
        this.curriculumDao = new CurriculumDaoImpl();
    }

    /**
     * injectable dao for easier testing
     *
     * @param curriculumDao
     */
    public CurriculumService(CurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    private Set<CurriculumInfo> getAllCurriculums() throws HibernateException, IOException{
		Set<CurriculumInfo> currs = PersistentStorage.getStorage().getCurriculums();
		if(currs == null || currs.isEmpty()) {
			execute();
			return PersistentStorage.getStorage().getCurriculums();
		}
		return currs;
	}
	
	public Map<BigDecimal, CurriculumInfo> getCurriculums() throws HibernateException, IOException{
		Map<BigDecimal, CurriculumInfo> curriculums;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			curriculums = curriculumDao.fetchCurriculums(session);
			
			session.flush();
			tx.commit();
			
			return curriculums;
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("Could not get curriculums", e);
		} finally {
			session.close();
		}
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
