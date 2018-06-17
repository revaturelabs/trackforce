package com.revature.daoimpl;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDao;
import com.revature.utils.HibernateUtil;

import javax.persistence.criteria.*;

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
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate", TfAssociate.class).getResultList());
	}

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();

			TfAssociate temp = session.get(TfAssociate.class, associate.getId());

			temp.setFirstName(associate.getFirstName());
			temp.setLastName(associate.getLastName());

			session.update(temp);
			t.commit();
			System.out.println(associate.getFirstName() + " successfully updated");
			return true;
		} catch (HibernateException hbe) {
			if (t != null) {
				t.rollback();
				System.out.println("Transaction successfully rolled back");
			}
			hbe.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.out.println("Session successfully closed: " + !session.isOpen());
			}
		}
		return false;

	}

	@Override
	public boolean updateAssociates(List<TfAssociate> associates) {
		for (TfAssociate a : associates) {
			if (!updateAssociate(a)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		return HibernateUtil.saveToDB(newassociate);
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


}
