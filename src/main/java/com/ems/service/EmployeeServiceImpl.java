package com.ems.service;

import com.ems.empApp.EmsResponse;
import com.ems.empApp.IEmsProcessor;
import com.ems.models.Employee;

public class EmployeeServiceImpl implements IEmsProcessor{

	public EmsResponse doProcess() {
		System.out.println("here");
		Employee emp = new Employee();
		emp.setEmp_id("1");
		EmsResponse emsResponse = new EmsResponse();
		emsResponse.setEmp(emp);
		return emsResponse;
	}

}
