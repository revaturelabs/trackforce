package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfCurriculum;
import com.revature.model.CurriculumInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class CurriculumDaoImpl implements CurriculumDao {

	@Override
	public Map<Integer, CurriculumInfo> getAllCurriculums() {
		Map<Integer, CurriculumInfo> curriculums = new HashMap<Integer, CurriculumInfo>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TfCurriculum> cq = cb.createQuery(TfCurriculum.class);
			Root<TfCurriculum> from = cq.from(TfCurriculum.class);
			CriteriaQuery<TfCurriculum> all = cq.select(from);
			Query<TfCurriculum> tq = session.createQuery(all);

			return createCurriculaMap(tq.getResultList());
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
		}
		return curriculums;
	}
	
	public Set<CurriculumInfo> getCurriculaFromCache(){
		return PersistentStorage.getStorage().getCurriculums();
	}
	
	public CurriculumInfo getCurriculaFromCacheByID(int id) {
		return PersistentStorage.getStorage().getCurriculumAsMap().get(new Integer(id));
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
	
	
	public static void cacheAllCurriculms(){
		PersistentStorage.getStorage().setCurriculums(new CurriculumDaoImpl().getAllCurriculums());			
	}
}
