package com.ems.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ems.empApp.EmsResponse;
import com.ems.models.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Component
@Path("/employee")
@Api(value = "Employee Service API")
public class EmployeeService {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@GET
	@Path("/details")
	@ApiOperation(value = "Employee Details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
	@Produces(MediaType.APPLICATION_JSON)
	public EmsResponse fetchEmployeeDetails() {
		EmsResponse cmsResponse = employeeServiceImpl.doProcess();
		return cmsResponse;
	}

	//details by id
	//delete by id
	//post employee
	//put patch
}
