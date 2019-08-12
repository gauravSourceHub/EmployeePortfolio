package com.ems.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	 @Override
	 public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Employee employee = new Employee();
	  employee.setEmp_id(rs.getInt("empId"));
	  employee.setEmp_name(rs.getString("empName"));
	  employee.setEmp_email(rs.getString("empEmail"));
	  return employee;
	 }
}
