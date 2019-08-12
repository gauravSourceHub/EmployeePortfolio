package com.ems.models;

import io.swagger.annotations.ApiModelProperty;

public class Department {

	private int dptId;
	private String dptName;
	private int empId;

	@ApiModelProperty(value="Department Id")
	public int getDptId() {
		return dptId;
	}

	public void setDptId(int dptId) {
		this.dptId = dptId;
	}
	
	@ApiModelProperty(value="Department Name")
	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	@ApiModelProperty(value="Employee Id")
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
}
