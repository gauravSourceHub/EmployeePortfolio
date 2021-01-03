package com.ems.empApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ems.empApp.dao.EmsDao;
import com.ems.empApp.dao.EmsDaoImpl;
import com.ems.service.EmployeeServiceImpl;

@SpringBootApplication
@Configuration
public class App {
	public static void main(String[] args) {
		System.out.println("here123");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public IEmsProcessor employeeServiceImpl() {
		return new EmployeeServiceImpl();
	}

	@Bean
	public EmsDao daoImpl() {
		return new EmsDaoImpl();
	}
}
