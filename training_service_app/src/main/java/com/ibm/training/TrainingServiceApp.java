/**
 * 
 */
package com.ibm.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author saket
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TrainingServiceApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrainingServiceApp.class, args);

	}

}
