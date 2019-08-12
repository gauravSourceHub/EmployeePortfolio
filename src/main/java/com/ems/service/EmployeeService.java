package com.ems.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.empApp.EmsException;
import com.ems.empApp.EmsRequest;
import com.ems.empApp.EmsResponse;
import com.ems.empApp.IEmsProcessor;
import com.ems.models.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.jaxrs.PATCH;

@Component
@Path("/")
@Api(value = "Employee Service API")
public class EmployeeService {

	@Autowired(required = true)
	private IEmsProcessor employeeServiceImpl;

	@GET
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse getAllEmployee() throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		emsRequest.setParams(params);
		EmsResponse emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}

	@GET
	@Path("/employee/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get employee by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse getEmployeeById(
			@ApiParam(value = "Specify Employee Id for which employee details to returned", required = true) @PathParam("empId") String empId)
			throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("empId", empId);
		emsRequest.setParams(params);
		EmsResponse emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}

	@DELETE
	@Path("/employee/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete employee by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse deleteEmployeeById(
			@ApiParam(value = "Specify Employee Id for which employee details to be deleted", required = true) @PathParam("empId") String empId)
			throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("empId", empId);
		params.put("createOrUpdate", "delete");
		emsRequest.setParams(params);
		EmsResponse emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}

	@POST
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create new employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse createEmployee(@RequestBody Employee emp) throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("createOrUpdate", "post");
		emsRequest.setParams(params);
		emsRequest.setInput(emp);
		EmsResponse emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}

	@PUT
	@Path("/employee/{empId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "save employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse saveEmployee(
			@ApiParam(value = "Specify Employee Id for which employee details to be saved", required = true) @PathParam("empId") String empId,
			@RequestBody Employee emp) throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("empId", empId);
		params.put("createOrUpdate", "put");
		emsRequest.setParams(params);
		emsRequest.setInput(emp);
		EmsResponse emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}

	@PATCH
	@Path("/employee/{empId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EmsResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public EmsResponse updateEmployee(
			@RequestBody Map<String, Object> partialUpdates,
			@ApiParam(value = "Specify Employee Id for which employee details to be partially updated", required = true) @PathParam("empId") String empId)
			throws EmsException {
		EmsRequest emsRequest = new EmsRequest();
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("empId", empId);
		String email = partialUpdates.get("emp_email")!=null?(String)partialUpdates.get("emp_email"):"";
		params.put("email", email);
		params.put("createOrUpdate", "patch");
		emsRequest.setParams(params);
		EmsResponse emsResponse = null;
		emsResponse = employeeServiceImpl.doProcess(emsRequest);
		return emsResponse;
	}
}
