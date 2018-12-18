package com.ibm.training.config.props;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="trainer")
@Getter
@Setter
public class TrainerProperties {

	private String name;
	private String address;
	private String country;
	private List<String> companies;
}
