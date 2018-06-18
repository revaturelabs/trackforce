package com.revature.daoimpl;

import com.revature.dao.TrainerDao;
import com.revature.entity.TfTrainer;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TrainerDaoImpl implements TrainerDao{

	@Override
	public TfTrainer getTrainer(int trainerId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
			session.createQuery("from TfTrainer t where t.id like :trainerId", TfTrainer.class).setParameter("trainerId", trainerId).getSingleResult());
	}

	@Override
	public TfTrainer getTrainerByUserId(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
			session.createQuery("from TfTrainer t where t.user.id like :id", TfTrainer.class).setParameter("id", id).getSingleResult());
	}

	@Override
	public List<TfTrainer> getAllTrainers() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfTrainer", TfTrainer.class).getResultList());
	}

	@Override
	public boolean createTrainer(TfTrainer trainer) {
		return HibernateUtil.saveToDB(trainer);
	}

	@Override
	public boolean updateTrainer(TfTrainer trainer) {
		return HibernateUtil.runHibernateTransaction((Session session, Object ... args)->
		{
			TfTrainer temp = session.get(TfTrainer.class, trainer.getId());

			temp.setCoTrainer(trainer.getCoTrainer());
			temp.setFirstName(trainer.getFirstName());
			temp.setLastName(trainer.getLastName());
			temp.setPrimary(trainer.getPrimary());

			session.update(temp);
			return true;
		});
	}



}
