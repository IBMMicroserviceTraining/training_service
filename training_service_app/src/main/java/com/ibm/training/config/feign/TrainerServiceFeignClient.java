package com.ibm.training.config.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.ibm.trainer.controller.ITrainerController;

@FeignClient("trainerservice")
public interface TrainerServiceFeignClient extends ITrainerController {
	
	

}
