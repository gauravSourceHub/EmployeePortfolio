package com.ems.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ems.empApp.EmsResponse;


@Component
@Path("/employee")
public class EmployeeService {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public EmsResponse fetchCustomerDetails() {
		EmsResponse cmsResponse = employeeServiceImpl.doProcess();
		return cmsResponse;
	}

	//details by id
	//delete by id
	//post employee
	//put patch
}
