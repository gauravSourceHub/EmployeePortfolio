package com.ems.empApp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ems.models.Department;
import com.ems.models.Employee;
import com.ems.service.EmployeeService;
import com.ems.service.EmployeeServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService empService;
	
	@Mock 
	private EmployeeServiceImpl empServiceImpl;
	
	MockMvc mockMvc;
	
	
	@Before()
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEmployeeDetails() throws Exception {
		ResponseMetaData responseMetaData = new ResponseMetaData();
		responseMetaData.setStatusCode(200);
		responseMetaData.setStatusDescription("Success");
		
		EmsResponse emsExpectedResponse = new EmsResponse();
		emsExpectedResponse.setResponseData(getEmployeeDetails());
		emsExpectedResponse.setResponseMetaData(responseMetaData);
		
		Mockito.when(empServiceImpl.doProcess(Mockito.anyObject())).thenReturn(emsExpectedResponse);
	}
	
	private List<Employee> getEmployeeDetails(){
		
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setEmp_id(1);
		emp.setEmp_email("gaurav@yahoo.com");
		emp.setEmp_name("Gaurav");
		
		List<Department> deptList = new ArrayList<Department>();
		Department dpt = new Department();
		dpt.setDptId(10);
		dpt.setDptName("Technology");
		dpt.setEmpId(1);
		deptList.add(dpt);

		emp.setDeptList(deptList);
		employeeList.add(emp);
		return employeeList;	
	}
}
