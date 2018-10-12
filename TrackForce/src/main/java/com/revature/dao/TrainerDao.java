package com.revature.dao;
import java.util.List;
import com.revature.entity.TfTrainer;

public interface TrainerDao {
	TfTrainer getTrainer(int id);
	
	List<TfTrainer> getAllTrainers();
	
	boolean createTrainer(TfTrainer trainer);
	
	boolean updateTrainer(TfTrainer trainer);
	
	TfTrainer getTrainerByUserId(int id);
}