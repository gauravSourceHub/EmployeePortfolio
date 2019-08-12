package com.ems.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.CollectionUtils;

import io.swagger.annotations.ApiModelProperty;

public class Employee {

	private int emp_id;
	private String emp_name;
	private String emp_email;
	private List<Department> deptList;

	@ApiModelProperty(value="Employee Id")
	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	@ApiModelProperty(value="Employee Name")
	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	@ApiModelProperty(value="Employee Email")
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	@ApiModelProperty(value="List of Department")
	public List<Department> getDeptList() {
//		if(deptList==null) {
//			deptList= new ArrayList<Department>();
//		}
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
}
