package com.ems.empApp.dao;

import java.util.List;

import com.ems.empApp.EmsException;
import com.ems.models.Employee;

public interface EmsDao {

	public List<Employee> getAllEmployee() throws EmsException;

	public Employee getEmployeeById(String id) throws EmsException;

	public boolean addEmployee(Employee employee) throws EmsException;

	public boolean saveEmployee(Employee employee, String empId) throws EmsException;

	public boolean updateEmployee(String requestString, String empId) throws EmsException;

	public boolean deleteEmployee(String id) throws EmsException;
}
