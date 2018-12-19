package com.ibm.training.config.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.trainer.model.TrainerCompanyModel;

@FeignClient("trainerservice")
public interface TrainerServiceFeignClient {
	
	@RequestMapping(value="/trainer/get/info",method=RequestMethod.GET)
	public List<TrainerCompanyModel> getAllTrainerCompanyModel();
	
	@RequestMapping(value="/trainer/get/info/{id}",method=RequestMethod.GET)
	public TrainerCompanyModel getTrainerCompanyModelByTrainerId(@PathVariable("id") String id);

}
