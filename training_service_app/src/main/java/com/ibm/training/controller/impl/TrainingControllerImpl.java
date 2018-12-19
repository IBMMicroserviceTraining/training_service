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

import com.ibm.trainer.model.TrainerCompanyModel;
import com.ibm.trainer.model.TrainerModel;
import com.ibm.training.config.feign.TrainerServiceFeignClient;
import com.ibm.training.config.props.TrainerProperties;
import com.ibm.training.controller.ITrainingController;


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
	
	List<TrainerModel> trainerModel = new ArrayList<TrainerModel>();
	TrainingControllerImpl()
	{
		for (int i=0;i<3;i++)
		{
			TrainerModel model = new TrainerModel();
			model.setTrainerId("00608V"+i);
			model.setTrainerName(trainerName+"_"+i);
			trainerModel.add(model);
			}
		}
	
	
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
		List<TrainerCompanyModel> compModel = new ArrayList<TrainerCompanyModel>();
		compModel= trainerFeignClient.getAllTrainerCompanyModel();
		compModel.forEach(company->{
			trainerModel.forEach(trainerObj ->{
				if(trainerObj.getTrainerId().equalsIgnoreCase(company.getTrainerCompanyId())
						) {
					trainerObj.setTrainerCompanyModel(company);
				}
			});
		});
		return ResponseEntity.status(HttpStatus.OK).body(trainerModel);
	}
	
	/**
	 * 
	 */
	
	public ResponseEntity<TrainerModel> getTrainerModelById(@PathVariable String id) {
		String methodName = "getTrainerModelById";
		log.info(className+"->"+methodName);
		TrainerModel singleTrainerModel = new TrainerModel();
		log.info(trainerName);
		trainerModel.forEach(model ->{
			if(model.getTrainerId().equalsIgnoreCase(id))
			{
				singleTrainerModel.setTrainerCompanyModel(trainerFeignClient.getTrainerCompanyModelByTrainerId(id));
				singleTrainerModel.setTrainerId(id);
				singleTrainerModel.setTrainerName(model.getTrainerName());
			}
		});
		return ResponseEntity.status(HttpStatus.OK).body(singleTrainerModel);
	}

}
