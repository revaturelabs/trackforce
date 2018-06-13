package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

public class AssociateDaoHibernate implements AssociateDao {

	

	@Override
	public TfAssociate getAssociate(Integer associateid) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Associate a where a.tf_associate_id like :associateid", TfAssociate.class).setParameter("associateid", associateid).getSingleResult());
	}

	@Override
	public List<TfAssociate> getAllAssociates() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Associate", TfAssociate.class).getResultList());
	}

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			TfAssociate temp = session.get(TfAssociate.class, associate.getTfAssociateId());
			
			temp.setTfAssociateFirstName(associate.getTfAssociateFirstName());
			temp.setTfAssociateLastName(associate.getTfAssociateLastName());
			
			session.update(temp);
			t.commit();
			System.out.println(associate.getTfAssociateFirstName() + " successfully updated");
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
		for(TfAssociate a : associates) {
			if(!updateAssociate(a)) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		return HibernateUtil.saveToDB(newassociate);
	}



}
