package com.ssafy.edu.employee.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ssafy.edu.employee.model.EmployeeResponse;
import com.ssafy.edu.employee.model.Employees;
import com.ssafy.edu.employee.model.service.EmployeeService;
import com.ssafy.edu.util.PageRequest;



//http://localhost:8080/hrms/api/employees
//springdoc.api-docs.path=/api-docs
//http://localhost:8098/hrms/swagger-ui/index.html
@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

	  @Autowired
	  private EmployeeService employeeService;

	  @GetMapping("/")
	  public List<Employees> getAllEmployees() throws Exception {
	    return employeeService.selectByEmployee();
	  }
	  
	  @GetMapping("/{id}")
	  public ResponseEntity<Employees> getEmployeeById(@PathVariable int id) throws Exception{
		  Optional<Employees> employee = Optional.ofNullable(employeeService.selectByPrimaryKey(id));
		  return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	  }
	  
	  @GetMapping("/page")
	  public PageInfo<Employees> selectAllEmployeePages() throws Exception {
	    return employeeService.selectAllEmployeePages();
	  }
	  @PostMapping("/pagewith")
	  public PageInfo<Employees> selectAllEmployeePagesWithSearch(@RequestBody PageRequest preq ) throws Exception {
	    return employeeService.selectAllEmployeePagesWith(preq);
	  }
	  
	  @PostMapping("/page/emp")
	  public PageInfo<Employees> selectEmployee(@RequestBody PageRequest preq ) throws Exception {
	    return employeeService.selectAllEmployeePagesWith(preq);
	  }
	  
	  @GetMapping("/pagewt/{limit}/{offset}")
	  public ResponseEntity<List<Employees>> getEmployeeWithPage(@PathVariable int limit,@PathVariable int offset) throws Exception{
		  Optional<List<Employees>> employee = Optional.ofNullable(employeeService.selectByExampleWithLimit(limit,offset));
		  return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	  }
	  /*
{
  "employeeId": 209,
  "firstName": "yurim",
  "lastName": "ji",
  "email": "jiyurim",
  "phoneNumber": "010-234-345",
  "hireDate": "2023-11-07T06:08:28.627Z",
  "jobId": "IT_PROG",
  "salary": 10000,
  "commissionPct": null,
  "managerId": 102,
  "departmentId": 60
}
	   */
	  @PostMapping("/regist")
	  public ResponseEntity<?> addEmployee(@RequestBody Employees pemp ) throws Exception {
	  
		try {
			  employeeService.addEmployee(pemp);
			  return  new ResponseEntity<EmployeeResponse>(new EmployeeResponse("success",200,"사원추가성공"),HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return  new ResponseEntity<EmployeeResponse>(new EmployeeResponse("fail",500,"사원추가실패"),HttpStatusCode.valueOf(200));
		}
		
	  }
}
//selectAllEmployeePagesWithSearch(int pageNum, int pageSize
