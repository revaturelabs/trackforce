package com.revature.dao;

import java.util.List;

import com.revature.entity.TfTrainer;

public interface TrainerDao {

	public TfTrainer getTrainer(int id);
	public List<TfTrainer> getAllTrainers();
	public boolean createTrainer(TfTrainer trainer);
	public boolean updateTrainer(TfTrainer trainer);
}
