package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfCurriculum;
import com.revature.model.CurriculumInfo;
import com.revature.utils.Dao2DoMapper;

public class CurriculumDaoImpl implements CurriculumDao {

	@Override
	public Map<BigDecimal, CurriculumInfo> fetchCurriculums(Session session) throws IOException {
		List<TfCurriculum> curriculumsEnt;
		Map<BigDecimal, CurriculumInfo> curriculumsInfo = new HashMap<BigDecimal, CurriculumInfo>();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TfCurriculum> cq = cb.createQuery(TfCurriculum.class);
		Root<TfCurriculum> from = cq.from(TfCurriculum.class);
		CriteriaQuery<TfCurriculum> all = cq.select(from);
		Query<TfCurriculum> tq = session.createQuery(all);

		curriculumsEnt = tq.getResultList();
		if (curriculumsEnt != null) {
			for (TfCurriculum tfa : curriculumsEnt) {
				curriculumsInfo.put(tfa.getTfCurriculumId(), Dao2DoMapper.map(tfa));
			}
		}

		return curriculumsInfo;
	}
}
