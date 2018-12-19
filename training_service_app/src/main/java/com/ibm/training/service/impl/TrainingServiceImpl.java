package com.ibm.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.trainer.model.TrainerCompanyModel;
import com.ibm.trainer.model.TrainerModel;
import com.ibm.training.config.feign.TrainerServiceFeignClient;
import com.ibm.training.service.ITrainingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainingServiceImpl implements ITrainingService {

	
	
	@Autowired
	TrainerServiceFeignClient trainerFeignClient;
	
	private static final String className = TrainingServiceImpl.class.toString();
	List<TrainerModel> trainerModel = new ArrayList<TrainerModel>();
	
	TrainingServiceImpl()
	{
		for (int i=0;i<3;i++)
		{
			TrainerModel model = new TrainerModel();
			model.setTrainerId("00608V"+i);
			model.setTrainerName("trainer"+"_"+i);
			trainerModel.add(model);
			}
	}
	
	
	@HystrixCommand(fallbackMethod = "findAllTrainersInfo_fallback",
			commandProperties = {
				       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
				       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60"),
				       @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
				    })
	public List<TrainerModel> findAllTrainersInfo() {
		String methodName = "findAllTrainersInfo";
		log.info(className+"->"+methodName);
		
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
		
		return trainerModel;
	}

	
	
	public List<TrainerModel> findAllTrainersInfo_fallback() {
		String methodName = "findAllTrainersInfo_fallback";
		log.info(className+"->"+methodName);
		
		log.warn("Trainer Service is down!! hystix fall back");
		return trainerModel;
	}
	
	@HystrixCommand(commandKey="find-trainer-by-id", fallbackMethod = "findTrainerModelById_fallback",
			commandProperties = {
				       @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
				    })
	public TrainerModel findTrainerModelById(String id) {
		String methodName = "findTrainerModelById";
		log.info(className+"->"+methodName);
		TrainerModel singleTrainerModel = new TrainerModel();
		trainerModel.forEach(model ->{
			if(model.getTrainerId().equalsIgnoreCase(id))
			{
				singleTrainerModel.setTrainerCompanyModel(trainerFeignClient.getTrainerCompanyModelByTrainerId(id));
				singleTrainerModel.setTrainerId(id);
				singleTrainerModel.setTrainerName(model.getTrainerName());
			}
		});
		return singleTrainerModel;
	}

	
	public TrainerModel findTrainerModelById_fallback(String id) {
		String methodName = "findTrainerModelById";
		log.info(className+"->"+methodName);
		log.warn("Trainer Service is down!! hystix fall back to findTrainerModelById_fallback");
		TrainerModel singleTrainerModel = new TrainerModel();
		trainerModel.forEach(model ->{
			if(model.getTrainerId().equalsIgnoreCase(id))
			{
				singleTrainerModel.setTrainerId(id);
				singleTrainerModel.setTrainerName(model.getTrainerName());
			}
		});
		return singleTrainerModel;
	}
	
}
