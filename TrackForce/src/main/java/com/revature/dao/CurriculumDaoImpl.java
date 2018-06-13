package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.revature.utils.LogUtil.logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfCurriculum;
import com.revature.model.CurriculumInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class CurriculumDaoImpl implements CurriculumDao {
	

	@Override
	public List<TfCurriculum> getAllCurriculums() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfCurriculum", TfCurriculum.class).setCacheable(true).getResultList());
	}

	public Set<CurriculumInfo> getCurriculaFromCache(){
		return PersistentStorage.getStorage().getCurriculums();
	}

	public CurriculumInfo getCurriculaFromCacheByID(int id) {
		return PersistentStorage.getStorage().getCurriculumAsMap().get(id);
	}

	public Map<Integer, CurriculumInfo> createCurriculaMap(List<TfCurriculum> curricula){
		Map<Integer, CurriculumInfo> map = new HashMap<>();
		if (curricula != null) {
			for (TfCurriculum tfa : curricula) {
				map.put(tfa.getTfCurriculumId(), Dao2DoMapper.map(tfa));
			}
		}
		return map;
	}


	public void cacheAllCurriculms(){
		PersistentStorage.getStorage().setCurriculums(getAllCurriculums());
	}
}
