package com.revature.services;

import java.util.List;

import com.revature.dao.TrainerDao;
import com.revature.dao.TrainerDaoImpl;
import com.revature.entity.TfTrainer;

public class TrainerService {
	
	private static TrainerDao dao = new TrainerDaoImpl();
	private TrainerService() {};

	public static TfTrainer getTrainer(int id) {
		return dao.getTrainer(id);
	}
	public static List<TfTrainer> getAllTrainers(){
		return dao.getAllTrainers();
	}
	public static boolean createTrainer(TfTrainer trainer) {
		return dao.createTrainer(trainer);
	}
	public static boolean updateTrainer(TfTrainer trainer) {
		return dao.updateTrainer(trainer);
	}
	
}
