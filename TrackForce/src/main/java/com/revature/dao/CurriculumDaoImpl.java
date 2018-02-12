package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class CurriculumDaoImpl implements CurriculumDao {

	@Override
	public Map<Integer, CurriculumInfo> getAllCurriculums() {
		Map<Integer, CurriculumInfo> curriculums = new HashMap<Integer, CurriculumInfo>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<TfCurriculum> curriculumsEnt;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TfCurriculum> cq = cb.createQuery(TfCurriculum.class);
			Root<TfCurriculum> from = cq.from(TfCurriculum.class);
			CriteriaQuery<TfCurriculum> all = cq.select(from);
			Query<TfCurriculum> tq = session.createQuery(all);

			curriculumsEnt = tq.getResultList();
			if (curriculumsEnt != null) {
				for (TfCurriculum tfa : curriculumsEnt) {
					curriculums.put(tfa.getTfCurriculumId(), Dao2DoMapper.map(tfa));
				}
			}
			return curriculums;
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
		}
		return curriculums;
	}
}
