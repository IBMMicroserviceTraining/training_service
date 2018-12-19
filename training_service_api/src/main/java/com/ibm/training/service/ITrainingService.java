package com.ibm.training.service;

import java.util.List;

import com.ibm.trainer.model.TrainerModel;

/**
 * 
 * @author saket
 *
 */
public interface ITrainingService {

	public List<TrainerModel> findAllTrainersInfo();
	
	public TrainerModel findTrainerModelById(String id);
	
}
