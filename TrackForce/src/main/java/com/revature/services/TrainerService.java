package com.revature.services;

import java.util.List;

import com.revature.dao.TrainerDao;
import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;

/**
 * @author Adam L. 
 * <p> </p>
 * @version.date v6.18.06.13
 *
 */
public class TrainerService {
	
	private static TrainerDao dao = new TrainerDaoImpl();
	
	// public so it can be used for testing 
	public TrainerService() {};
	
	public TrainerService(TrainerDao dao) {
		this.dao = dao;
	}

	/**
	 * @author Adam L. 
	 * Given a trainer id, returns a trainer.
	 * @version.date v6.18.06.13
	 * 
	 * @param id
	 * @return
	 */
	public TfTrainer getTrainer(int id) {
		return dao.getTrainer(id);
	}

	/**
	 * @author Curtis H.
	 * Given a user id, returns a trainer.
	 * @version.date v6.18.06.16
	 *
	 */
	public TfTrainer getTrainerByUserId(int id) {
		return dao.getTrainerByUserId(id);
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v6.18.06.13
	 * 
	 * @return
	 */
	public List<TfTrainer> getAllTrainers(){
		return dao.getAllTrainers();
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v6.18.06.13
	 * 
	 * @param trainer
	 * @return
	 */
	public boolean createTrainer(TfTrainer trainer) {
		return dao.createTrainer(trainer);
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v6.18.06.13
	 * 
	 * @param trainer
	 * @return
	 */
	public boolean updateTrainer(TfTrainer trainer) {
		return dao.updateTrainer(trainer);
	}
}
