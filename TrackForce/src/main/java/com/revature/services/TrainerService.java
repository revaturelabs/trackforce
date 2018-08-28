package com.revature.services;
import com.revature.dao.TrainerDao;
import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import java.util.List;

/** @author Adam L.
 * @version v6.18.06.13 */
public class TrainerService {
	
	private static TrainerDao dao = new TrainerDaoImpl();

	public TrainerService() {} // public so it can be used for testing

    public TrainerService(TrainerDao dao) { TrainerService.dao = dao; }

	/** @author Adam L.
	 * Given a trainer id, returns a trainer.
	 * @version v6.18.06.13 */
	public TfTrainer getTrainer(int id) { return dao.getTrainer(id); }

	/** @author Curtis H.
	 * Given a user id, returns a trainer.
	 * @version v6.18.06.16 */
	public TfTrainer getTrainerByUserId(int id) { return dao.getTrainerByUserId(id); }
	
	/** @author Adam L.
	 * @version v6.18.06.13 */
	public List<TfTrainer> getAllTrainers(){ return dao.getAllTrainers(); }
	
	/** @author Adam L.
	 * @version v6.18.06.13 */
	public boolean createTrainer(TfTrainer trainer) {
		try {
			TfUser traineruser = trainer.getTfUser();
			traineruser.setPassword(PasswordStorage.createHash(trainer.getTfUser().getPassword()));
			trainer.setTfUser(traineruser);
			List<TfTrainer> trainers = getAllTrainers();
			int maxid = trainers.size();
			trainer.setId(75 + maxid); // Late game fix for non-functional Seq gen
			LogUtil.logger.info("The trainer with hashed password is " + trainer);
		} catch (CannotPerformOperationException e) { LogUtil.logger.warn(e.getMessage()); }
		return dao.createTrainer(trainer);
	}
	
	/** @author Adam L.
	 * @version v6.18.06.13 */
	public boolean updateTrainer(TfTrainer trainer) { return dao.updateTrainer(trainer); }
}