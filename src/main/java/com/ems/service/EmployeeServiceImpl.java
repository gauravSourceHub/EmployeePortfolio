package com.ems.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.ems.empApp.EmsException;
import com.ems.empApp.EmsRequest;
import com.ems.empApp.EmsResponse;
import com.ems.empApp.IEmsProcessor;
import com.ems.empApp.ResponseMetaData;
import com.ems.empApp.dao.EmsDao;
import com.ems.models.Employee;

@Service("employeeServiceImpl")
@Configuration
public class EmployeeServiceImpl implements IEmsProcessor {

	@Autowired
	EmsDao emsDaoImpl;

	public EmsResponse doProcess(EmsRequest emsRequest) throws EmsException {

		Instant apiStartTime = Instant.now();
		EmsResponse emsResponse = new EmsResponse();
		Map<Object, Object> mapObject = emsRequest.getParams();
		String empId = "";
		String createOrUpdate = "";
		if (mapObject != null) {
			empId = mapObject.get("empId") != null ? (String) mapObject.get("empId") : "";
			createOrUpdate = mapObject.get("createOrUpdate") != null ? (String) mapObject.get("createOrUpdate") : "";
		}
		if (!StringUtils.isEmpty(empId)) {
			if (StringUtils.isEmpty(createOrUpdate)) {
				Employee emp = getEmployeeById(empId);
				emsResponse.setResponseData(emp);
			} else if (createOrUpdate.equals("delete")) {
				emsResponse.setResponseData(deleteEmployeeById(empId));
			} else if (createOrUpdate.equals("put")) {
				Employee emp = (Employee) emsRequest.getInput();
				emsResponse.setResponseData(saveEmployee(emp, empId));
			} else if (createOrUpdate.equals("patch")) {
				String email = mapObject.get("email") != null ? (String) mapObject.get("email") : "";
				emsResponse.setResponseData(updateEmployee(email, empId));
			}
		} else {
			if (StringUtils.isEmpty(createOrUpdate)) {
				emsResponse.setResponseData(getAllEmployee());
			} else if (createOrUpdate.equals("post")) {
				Employee emp = (Employee) emsRequest.getInput();
				emsResponse.setResponseData(createEmployee(emp));
			}
		}
		ResponseMetaData resMeataData = new ResponseMetaData();

		resMeataData.setResponseTime(Duration.between(apiStartTime, Instant.now()).toMillis());
		resMeataData.setStatusCode(Response.Status.OK.getStatusCode());
		resMeataData.setStatusDescription("Success");
		emsResponse.setResponseMetaData(resMeataData);
		return emsResponse;
	}

	private List<Employee> getAllEmployee() throws EmsException {
		List<Employee> employeeList = emsDaoImpl.getAllEmployee();
		return employeeList;
	}

	private Employee getEmployeeById(String empId) throws EmsException {
		Employee emp = null;
		try {
			emp = emsDaoImpl.getEmployeeById(empId);
		} catch (EmsException e) {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
					"getAllEmployee method in EmsDaoImpl failed");
		}
		return emp;
	}

	private Map<Object, Object> deleteEmployeeById(String empId) throws EmsException {
		boolean status = emsDaoImpl.deleteEmployee(empId);
		Map<Object, Object> mapResult = new HashMap<Object, Object>();
		if (status) {
			mapResult.put("status", "Successfully deleted");
		}else {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "",
					"invalid empId");
		}
		return mapResult;
	}

	private Map<Object, Object> createEmployee(Employee emp) throws EmsException {
		boolean status = emsDaoImpl.addEmployee(emp);
		Map<Object, Object> mapResult = new HashMap<Object, Object>();
		if (status) {
			mapResult.put("status", "Successfully created");
		}else {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "",
					"invalid request");
		}
		return mapResult;
	}

	private Map<Object, Object> saveEmployee(Employee emp, String empId) throws EmsException {
		boolean status = emsDaoImpl.saveEmployee(emp, empId);
		Map<Object, Object> mapResult = new HashMap<Object, Object>();
		if (status) {
			mapResult.put("status", "Successfully saved");
		}else {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "",
					"invalid request");
		}
		return mapResult;
	}

	private Map<Object, Object> updateEmployee(String email, String empId) throws EmsException {
		boolean status = emsDaoImpl.updateEmployee(email, empId);
		
		Map<Object, Object> mapResult = new HashMap<Object, Object>();
		if (status) {
			mapResult.put("status", "Successfully updated");
		}else {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "",
					"invalid request");
		}
		return mapResult;
	}

}
