package com.revature.services;

import java.util.List;

import com.revature.dao.TrainerDao;
import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

/**
 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
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
	 * @version v6.18.06.13
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
	 * @version v6.18.06.16
	 *
	 */
	public TfTrainer getTrainerByUserId(int id) {
		return dao.getTrainerByUserId(id);
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version v6.18.06.13
	 * 
	 * @return
	 */
	public List<TfTrainer> getAllTrainers(){
		return dao.getAllTrainers();
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version v6.18.06.13
	 * 
	 * @param trainer
	 * @return
	 */
	UserService userService = new UserService();
	public boolean createTrainer(TfTrainer trainer) {
		try {
			TfUser traineruser = trainer.getTfUser();
			traineruser.setPassword(PasswordStorage.createHash(trainer.getTfUser().getPassword()));
			trainer.setTfUser(traineruser);
			LogUtil.logger.info("The trainer with hashed password is " + trainer);
		} catch (CannotPerformOperationException e) {
			LogUtil.logger.warn(e.getMessage());
		}
		return dao.createTrainer(trainer);
	}
	
	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version v6.18.06.13
	 * 
	 * @param trainer
	 * @return
	 */
	public boolean updateTrainer(TfTrainer trainer) {
		return dao.updateTrainer(trainer);
	}
}
