package com.revature.daoimpl;

import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.openqa.selenium.InvalidArgumentException;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.Sessional;

public class AssociateDaoImpl implements AssociateDao {

	@Override
	public TfAssociate getAssociate(Integer associateid) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate a where a.id like :associateid", TfAssociate.class)
				.setParameter("associateid", associateid).getSingleResult());
	}

	@Override
	public TfAssociate getAssociateByUserId(int id) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate a where a.user.id like :id", TfAssociate.class).setParameter("id", id)
				.getSingleResult());
	}

	@Override
	public List<TfAssociate> getAllAssociates() {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate", TfAssociate.class).getResultList());
	}

	private Sessional<Boolean> updateAssociatePartial = (Session session, Object... args) -> {
		TfAssociate associate = (TfAssociate) args[0];
		TfAssociate temp = session.get(TfAssociate.class, associate.getId());

		temp.setFirstName(associate.getFirstName());
		temp.setLastName(associate.getLastName());

		session.update(temp);
		return true;
	};

	@Override
	public boolean updateAssociatePartial(TfAssociate associate) {
		return HibernateUtil.runHibernateTransaction(updateAssociatePartial);
	}

	private Sessional<Boolean> approveAssociate = (Session session, Object... args) -> {
		TfAssociate temp = session.get(TfAssociate.class, (Integer) args[0]);

		temp.getUser().setIsApproved(TfUser.APPROVED);

		session.update(temp);
		return true;
	};

	@Override
	public boolean approveAssociate(int associateId) {
		return HibernateUtil.runHibernateTransaction(approveAssociate, associateId);
	}

	@Override
	public boolean approveAssociates(List<Integer> associateIds) {
		return HibernateUtil.multiTransaction(approveAssociate, associateIds);
	}

	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		return saveToDB(newassociate);
	}

	@Override
	public List<GraphedCriteriaResult> getMapped(int id) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

			Root<TfAssociate> root = query.from(TfAssociate.class);

			Join<TfAssociate, TfClient> clientJoin = root.join("client");
			Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");

			Path clientId = clientJoin.get("id");
			Path clientName = clientJoin.get("name");

			query.where(cb.equal(msJoin.get("id"), args[0]));
			query.groupBy(clientId, clientName);
			query.multiselect(cb.count(root), clientId, clientName);
			return session.createQuery(query).getResultList();
		}, id);
	}

	@Override
	public List<GraphedCriteriaResult> getUndeployed(String which) {
		if (which.equals("mapped")) {
			return HibernateUtil.runHibernate((Session session, Object... args) -> {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

				Root<TfAssociate> root = query.from(TfAssociate.class);

				Join<TfAssociate, TfClient> clientJoin = root.join("client");
				Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");

				Path clientId = clientJoin.get("id");
				Path clientName = clientJoin.get("name");

				query.where(cb.lessThanOrEqualTo(msJoin.get("id"), 4));
				query.where(cb.greaterThanOrEqualTo(msJoin.get("id"), 1));

				query.groupBy(clientId, clientName);
				query.multiselect(cb.count(root), clientId, clientName);
				return session.createQuery(query).getResultList();
			});
		} else if (which.equals("unmapped")) {
			return HibernateUtil.runHibernate((Session session, Object... args) -> {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

				Root<TfAssociate> root = query.from(TfAssociate.class);

				Join<TfAssociate, TfBatch> batchJoin = root.join("batch");
				Join<TfBatch, TfCurriculum> curriculumJoin = batchJoin.join("curriculumName");
				Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");

				Path curriculumid = curriculumJoin.get("id");
				Path curriculumName = curriculumJoin.get("name");

				query.where(cb.lessThanOrEqualTo(msJoin.get("id"), 9));
				query.where(cb.greaterThanOrEqualTo(msJoin.get("id"), 6));
				query.groupBy(curriculumid, curriculumName);
				query.multiselect(cb.count(root), curriculumid, curriculumName);
				return session.createQuery(query).getResultList();
			});
		}
		
		throw new InvalidArgumentException("NOT MAPPED OR UNMAPPED YOU FOOOL");
	}

	private Sessional<Boolean> updateAssociate = (Session session, Object... args) -> {
		TfAssociate associate = (TfAssociate) args[0];
		TfAssociate temp = session.get(TfAssociate.class, associate.getId());

		temp.setBatch(associate.getBatch());
		temp.setMarketingStatus(associate.getMarketingStatus());
		temp.setClient(associate.getClient());
		temp.setEndClient(associate.getEndClient());
		temp.setFirstName(associate.getFirstName());
		temp.setLastName(associate.getLastName());
		temp.setInterview(associate.getInterview());
		temp.setPlacement(associate.getPlacement());
		temp.setClientStartDate(associate.getClientStartDate());

		session.update(temp);
		System.out.println("<============================Indside Update===============================>");
		return true;
	};

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		return runHibernateTransaction((Session session, Object... args) -> {
			session.update(associate);
			return true;
		});

	}

	@Override
	public boolean updateAssociates(List<TfAssociate> associates) {
		associates.forEach(this::updateAssociate);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T countMappedAssociatesByValue(String column, T value, Integer mappedStatus) {

		Sessional<T> ss = (Session session, Object... args) -> {
			String condition = null;

			if (Integer.valueOf(value.toString()) != -1) {
				condition = column + " = " + args[0] + " AND ";
			} else {
				condition = "";
			}
			
			Query query = session.createQuery("SELECT COUNT(TF_ASSOCIATE_ID) FROM TfAssociate WHERE "
					+ condition + "TF_MARKETING_STATUS_ID = :status");
			
			return (T) query
					.setParameter("client_condition", condition)
					.setParameter("status", args[1])
					.getSingleResult();
		};

		return HibernateUtil.runHibernate(ss, value, mappedStatus);
	}

}
