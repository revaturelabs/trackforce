package com.revature.daoimpl;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.*;
import com.revature.utils.Sessional;
import org.hibernate.Session;

import com.revature.dao.AssociateDao;
import com.revature.utils.HibernateUtil;

import javax.persistence.criteria.*;

import static com.revature.utils.HibernateUtil.multiTransaction;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;

public class AssociateDaoImpl implements AssociateDao {

	
	@Override
	public TfAssociate getAssociate(Integer associateid) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfAssociate a where a.id like :associateid", TfAssociate.class).setParameter("associateid", associateid).getSingleResult());
	}

	@Override
	public TfAssociate getAssociateByUserId(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfAssociate a where a.user.id like :id", TfAssociate.class).setParameter("id", id).getSingleResult());
	}


	@Override
	public List<TfAssociate> getAllAssociates() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfAssociate", TfAssociate.class).getResultList());
	}

	private Sessional<Boolean> updateAssociatePartial = (Session session, Object ... args)-> {
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

	private Sessional<Boolean> approveAssociate = (Session session, Object ... args)-> {
		TfAssociate associate = (TfAssociate) args[0];
		TfAssociate temp = session.get(TfAssociate.class, associate.getId());

		temp.getUser().setIsApproved(TfUser.APPROVED);

		session.update(temp);
		return true;
	};

	@Override
	public boolean approveAssociate(int associateId) {
		return HibernateUtil.runHibernateTransaction(approveAssociate);
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
		return HibernateUtil.runHibernate((Session session, Object ... args) -> {
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
				}, id
		);
	}

	private Sessional<Boolean> updateAssociate = (Session session, Object ... args)-> {
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
		return true;
	};

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		return runHibernateTransaction(updateAssociate, associate);
	}

	@Override
	public boolean updateAssociates(List<TfAssociate> associates) {
		return multiTransaction(updateAssociate, associates);
	}


}
