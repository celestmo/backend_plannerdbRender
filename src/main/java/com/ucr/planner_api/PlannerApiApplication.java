package com.ucr.planner_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.ucr.planner_api")
public class PlannerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApiApplication.class, args);
	}

}
