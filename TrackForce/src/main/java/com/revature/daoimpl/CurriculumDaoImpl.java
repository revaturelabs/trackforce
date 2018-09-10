package com.revature.daoimpl;
import java.util.List;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfMarketingStatus;
import org.hibernate.Session;
import com.revature.dao.CurriculumDao;
import com.revature.entity.TfCurriculum;
import com.revature.utils.HibernateUtil;
import javax.persistence.criteria.*;

public class CurriculumDaoImpl implements CurriculumDao {
	
	@Override
	public List<TfCurriculum> getAllCurriculums() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
			session.createQuery("from TfCurriculum", TfCurriculum.class)
			.setCacheable(true).getResultList());
	}

	@Override
	public List<GraphedCriteriaResult> getUnmapped(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) -> {
					CriteriaBuilder cb = session.getCriteriaBuilder();
					CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

					Root<TfAssociate> root = query.from(TfAssociate.class);

					Join<TfAssociate, TfBatch> batchJoin = root.join("batch");
					Join<TfBatch, TfCurriculum> curriculumJoin = batchJoin.join("curriculumName");
					Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");

					Path<?> curriculumid = curriculumJoin.get("id");
					Path<?> curriculumName = curriculumJoin.get("name");

					query.where(cb.equal(msJoin.get("id"), args[0]));
					query.groupBy(curriculumid, curriculumName);
					query.multiselect(cb.count(root), curriculumid, curriculumName);
					return session.createQuery(query).setCacheable(true).getResultList();
				}, id );
	}//end getUnmapped()
}//end class