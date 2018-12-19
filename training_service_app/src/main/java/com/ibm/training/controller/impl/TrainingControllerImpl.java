/**
 * 
 */
package com.ibm.training.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.trainer.model.TrainerModel;
import com.ibm.training.config.feign.TrainerServiceFeignClient;
import com.ibm.training.config.props.TrainerProperties;
import com.ibm.training.controller.ITrainingController;
import com.ibm.training.service.ITrainingService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author saket
 *
 */
@RestController
@Slf4j
public class TrainingControllerImpl implements ITrainingController {

	private static final String className = TrainingControllerImpl.class.toString();

	@Value("${trainer.name}")
	private String trainerName;
	
	@Autowired
	TrainerProperties props;
	
	@Autowired
	TrainerServiceFeignClient trainerFeignClient;
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	ITrainingService trainingService;
	
	List<TrainerModel> trainerModel = new ArrayList<TrainerModel>();
	
	
	/**
	 * 
	 */
	public ResponseEntity<List<TrainerModel>> getAllTrainersInfo() {
		String methodName = "getAllTrainersInfo";
		log.info(className+"->"+methodName);
		log.info(trainerName);
		log.info(props.getName());
		log.info(props.getCompanies().toString());
		log.info(this.client.getServices().toString());
		
		trainerModel = trainingService.findAllTrainersInfo();
		return ResponseEntity.status(HttpStatus.OK).body(trainerModel);
	}
	
	/**
	 * 
	 */
	
	public ResponseEntity<TrainerModel> getTrainerModelById(@PathVariable String id) {
		String methodName = "getTrainerModelById";
		log.info(className+"->"+methodName);
		TrainerModel singleTrainerModel = new TrainerModel();
		singleTrainerModel = trainingService.findTrainerModelById(id);
		return ResponseEntity.status(HttpStatus.OK).body(singleTrainerModel);
	}

}
