/**
 * 
 */
package com.ibm.training.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.trainer.model.TrainerCompanyModel;
import com.ibm.trainer.model.TrainerModel;
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
	
	List<TrainerModel> trainerModel = new ArrayList<TrainerModel>();
	TrainingControllerImpl()
	{
		for (int i=0;i<3;i++)
		{
			TrainerModel model = new TrainerModel();
			
		    TrainerCompanyModel companyModel = new TrainerCompanyModel();
			companyModel.setTrainerCompanyId("00608V_"+i);
			companyModel.setTrainerDesignation("Rest services Lead_"+i);
			companyModel.setTrainerLocation("Manyatha_"+i);
			model.setTrainerCompanyModel(companyModel);
			model.setTrainerId(i);
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
		return ResponseEntity.status(HttpStatus.OK).body(trainerModel);
	}
	
	/**
	 * 
	 */
	
	public ResponseEntity<TrainerModel> getTrainerModelById(@PathVariable int id) {
		String methodName = "getTrainerModelById";
		log.info(className+"->"+methodName);
		TrainerModel singleTrainerModel = new TrainerModel();
		log.info(trainerName);
		trainerModel.forEach(model ->{
			if(model.getTrainerId() == id)
			{
				singleTrainerModel.setTrainerCompanyModel(model.getTrainerCompanyModel());
				singleTrainerModel.setTrainerId(id);
				singleTrainerModel.setTrainerName(model.getTrainerName());
			}
		});
		return ResponseEntity.status(HttpStatus.OK).body(singleTrainerModel);
	}

}
