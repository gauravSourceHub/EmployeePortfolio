package com.ems.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentRowMapper implements RowMapper<Department> {

	 @Override
	 public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Department department = new Department();
	  department.setDptId(rs.getInt("dptId"));
	  department.setDptName(rs.getString("dptName"));
	  department.setEmpId(rs.getInt("empId"));
	  return department;
	 }
}
