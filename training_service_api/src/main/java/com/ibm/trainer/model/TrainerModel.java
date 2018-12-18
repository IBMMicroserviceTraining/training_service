package com.ibm.trainer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerModel {

	private int trainerId;
	private String trainerName;
	private TrainerCompanyModel trainerCompanyModel;
}
