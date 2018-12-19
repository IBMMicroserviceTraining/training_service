/**
 * 
 */
package com.ibm.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author saket
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.ibm.training.config.feign")
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class TrainingServiceApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrainingServiceApp.class, args);

	}

}
