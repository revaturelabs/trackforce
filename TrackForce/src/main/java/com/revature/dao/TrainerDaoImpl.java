package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entity.TfTrainer;
import com.revature.utils.HibernateUtil;

public class TrainerDaoImpl implements TrainerDao{

	@Override
	public TfTrainer getTrainer(int trainerId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Trainer t where t.tf_trainer_id like :trainerId", TfTrainer.class).setParameter("trainerId", trainerId).getSingleResult());
	}

	@Override
	public List<TfTrainer> getAllTrainers() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Trainer", TfTrainer.class).setCacheable(true).getResultList());
	}

	@Override
	public boolean createTrainer(TfTrainer trainer) {
		return HibernateUtil.saveToDB(trainer);
	}

	@Override
	public boolean updateTrainer(TfTrainer trainer) {
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			TfTrainer temp = session.get(TfTrainer.class, trainer.getId());
			
			temp.setCoTrainer(trainer.getCoTrainer());
			temp.setFirstName(trainer.getFirstName());
			temp.setLastName(trainer.getLastName());
			temp.setIsApproved(trainer.getIsApproved());
			temp.setPrimary(trainer.getPrimary());
			
			session.update(temp);
			t.commit();
			System.out.println(trainer.getFirstName() + " successfully updated");
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

}
