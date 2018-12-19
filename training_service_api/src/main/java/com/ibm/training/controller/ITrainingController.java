package com.ibm.training.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.trainer.model.TrainerModel;

/**
 *  All the apis for Trainer info
 * @author saket
 *
 */
@RequestMapping("/training")
public interface ITrainingController {
	
	/**
	 * 
	 * @returns all trainers info. This also contains the trainer's company info
	 */
	@RequestMapping(value="/getinfo",method = RequestMethod.GET)
	public ResponseEntity<List<TrainerModel>> getAllTrainersInfo();

	/**
	 * 
	 * @param id
	 * @return one trainer info for the matched id. This also contains the trainer's company info
	 */
	@RequestMapping(value="/getinfo/{id}",method=RequestMethod.GET)
	public ResponseEntity<TrainerModel> getTrainerModelById(@PathVariable("id") String id);
}
