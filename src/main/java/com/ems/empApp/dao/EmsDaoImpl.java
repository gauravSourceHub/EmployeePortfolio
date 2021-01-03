package com.ems.empApp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ems.empApp.EmsException;
import com.ems.models.Department;
import com.ems.models.DepartmentRowMapper;
import com.ems.models.Employee;
import com.ems.models.EmployeeRowMapper;

@Repository
public class EmsDaoImpl implements EmsDao {

	@Autowired
	JdbcTemplate emsJdbcTemplate;

	@Override
	public List<Employee> getAllEmployee() throws EmsException {
		
		Map<Integer, Employee> map = new HashMap<Integer, Employee>();
//		String query = "SELECT * from employee";
//		RowMapper<Employee> rowMapper = new EmployeeRowMapper();
//		List<Employee> list = emsJdbcTemplate.query(query, rowMapper);
//		
//		query = "SELECT * from Department";
//		RowMapper<Department> dptRowMapper  = new DepartmentRowMapper();
//		List<Department> dptList = emsJdbcTemplate.query(query, dptRowMapper);
//		
//		list.stream().forEach(emp -> {
//			int empId = emp.getEmp_id();
//			Employee employee = map.get(empId);
//			if (employee == null) {
//				map.put(empId, emp);
//			}
//
//			dptList.stream().forEach(dpt -> {
//				if (dpt.getEmpId() == emp.getEmp_id()) {
//					List<Department> dptListTemp = emp.getDeptList();
//					if (dptListTemp == null) {
//						dptListTemp = new ArrayList<Department>();
//						emp.setDeptList(dptListTemp);
//					}
//					dptListTemp.add(dpt);
//				}
//			});
//
//		});
		
		Employee emp = new Employee();
		emp.setEmp_id(1);
		emp.setEmp_name("john");
		emp.setEmp_email("john@gmail.com");
		List<Department> dptLst = new ArrayList();
		Department dpt = new Department();
		dpt.setDptId(10);
		dpt.setDptName("departmentName");
		dpt.setEmpId(1);
		dptLst.add(dpt);
		emp.setDeptList(dptLst);
		map.put(1,emp);
		return new ArrayList<Employee>(map.values());
	}

	@Override
	public Employee getEmployeeById(String empId) throws EmsException {
		String query = "SELECT * from employee where empId = ? ";
		Object[] params = new Object[] { Integer.parseInt(empId) };
		RowMapper<Employee> rowMapper = new EmployeeRowMapper();
        Employee employee = emsJdbcTemplate.queryForObject(query, params, rowMapper);
        
        query = "SELECT * from Department where empId = ? ";
		params = new Object[] { Integer.parseInt(empId) };
		RowMapper<Department> dptRowMapper = new DepartmentRowMapper();
        List<Department> departmentList = emsJdbcTemplate.query(query,dptRowMapper, params);
		employee.setDeptList(departmentList);
		System.out.println("here");
        return employee;
	}


	@Override
	public boolean addEmployee(Employee employee) throws EmsException {
		boolean status = false;
		String query = "INSERT INTO Employee (empId, empName, empEmail) VALUES (?, ?, ?);";
		Object[] params = new Object[] { employee.getEmp_id(), employee.getEmp_name(), employee.getEmp_email() };
		try {
			int rowAffected = emsJdbcTemplate.update(query, params);
			if (rowAffected != 0) {
				status = true;
				List<Department> departmentList = employee.getDeptList();
				if(departmentList!=null) {
					for(Department dpt : departmentList) {
						String dptQuery = "INSERT INTO Department (dptId, dptName, empId) VALUES (?, ?, ?);";
						Object[] dptParams = new Object[] { dpt.getDptId(), dpt.getDptName(), employee.getEmp_id() };
						try {
							status = false;
							int dptRowAffected = emsJdbcTemplate.update(dptQuery, dptParams);
							if (dptRowAffected != 0) {
								status = true;
							}
						} catch (Exception e) {
							throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
									"addEmployee method in EmsDaoImpl failed");
						}
					}
				}
			}
		} catch (Exception e) {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
					"addEmployee method in EmsDaoImpl failed");
		}
		return status;
	}

	@Override
	public boolean saveEmployee(Employee employee, String empId) throws EmsException {
		boolean status = false;
		Employee emp = getEmployeeById(empId);
		if (emp != null) {
			emp.setEmp_name(employee.getEmp_name());
			emp.setEmp_email(employee.getEmp_email());
			Object[] params = new Object[] { emp.getEmp_name(), emp.getEmp_id() };
			String query = "update employee set empName = ? where empId = ?";
			try {
				int rowAffected = emsJdbcTemplate.update(query, params);
				if (rowAffected != 0) {
					status = true;
				}
			} catch (Exception e) {
				throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
						"saveEmployee method in EmsDaoImpl failed");
			}
		} else {
			throw new EmsException(Response.Status.BAD_REQUEST.getStatusCode(), "empId not valid", "empId not valid");
		}
		return status;
	}

	@Override
	public boolean deleteEmployee(String id) throws EmsException {
		boolean status = false;
		String query = "Delete from employee where empId = ? ";
		Object[] params = new Object[] { Integer.parseInt(id) };

		try {
			int rowAffected = emsJdbcTemplate.update(query, params);
			if (rowAffected != 0) {
				status = true;
			}
		} catch (Exception e) {
			throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
					"deleteEmployee method in EmsDaoImpl failed");
		}
		return status;
	}

	@Override
	public boolean updateEmployee(String requestString, String empId) throws EmsException {
		boolean status = false;
		Employee emp = getEmployeeById(empId);
		if (emp != null) {
			emp.setEmp_email(requestString);
			Object[] params = new Object[] { emp.getEmp_email(), emp.getEmp_id() };
			String query = "update employee set empEmail = ? where empId = ?";
			try {
				int rowAffected = emsJdbcTemplate.update(query, params);
				if (rowAffected != 0) {
					status = true;
				}
			} catch (Exception e) {
				throw new EmsException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage(),
						"updateEmployee method in EmsDaoImpl failed");
			}
		} else {
			throw new EmsException(Response.Status.BAD_REQUEST.getStatusCode(), "empId not valid", "empId not valid");
		}
		return status;

	}

}
