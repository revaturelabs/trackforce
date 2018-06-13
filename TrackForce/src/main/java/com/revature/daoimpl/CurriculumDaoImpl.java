package com.revature.daoimpl;

import java.util.List;

import org.hibernate.Session;

import com.revature.dao.CurriculumDao;
import com.revature.entity.TfCurriculum;
import com.revature.utils.HibernateUtil;

public class CurriculumDaoImpl implements CurriculumDao {

	@Override
	public List<TfCurriculum> getAllCurriculums() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfCurriculum", TfCurriculum.class).getResultList());
	}

	public Set<CurriculumInfo> getCurriculaFromCache(){
		return PersistentStorage.getStorage().getCurriculums();
	}

	public CurriculumInfo getCurriculaFromCacheByID(int id) {
		return PersistentStorage.getStorage().getCurriculumAsMap().get(id);
	}
	

	
}
