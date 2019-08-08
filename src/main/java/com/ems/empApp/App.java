package com.ems.empApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ems.service.EmployeeServiceImpl;

@SpringBootApplication
public class App 
{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	IEmsProcessor employeeServiceImpl() {
		return new EmployeeServiceImpl();
	}
}
