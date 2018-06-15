package com.revature.services;

import java.util.List;

import com.revature.dao.TrainerDao;
import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;

/**
 * @author Adam L. 
 * <p> </p>
 * @version.date v06.2018.06.13
 *
 */
public class TrainerService {
	
	private static TrainerDao dao = new TrainerDaoImpl();
	
	// public so it can be used for testing 
	public TrainerService() {};

	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param id
	 * @return
	 */
	public TfTrainer getTrainer(int id) {
		return dao.getTrainer(id);
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @return
	 */
	public List<TfTrainer> getAllTrainers(){
		return dao.getAllTrainers();
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
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
	 * @version.date v06.2018.06.13
	 * 
	 * @param trainer
	 * @return
	 */
	public boolean updateTrainer(TfTrainer trainer) {
		return dao.updateTrainer(trainer);
	}
	
}
