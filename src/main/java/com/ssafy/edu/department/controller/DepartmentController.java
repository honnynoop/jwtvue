package com.ssafy.edu.department.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.department.model.service.DepartmentService;
import com.ssafy.edu.department.model.Departments;


//http://localhost:8098/hrms/api/employees
//springdoc.api-docs.path=/api-docs
//http://localhost:8098/hrms/swagger-ui/index.html
@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	  private DepartmentService departmentService;
	  
	  public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping("/")
	  public ResponseEntity<?> departmentList() {
		logger.debug("departmentList call");
		try {
			List<Departments> list = departmentService.selectByDepartment();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Departments>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/group")
	  public ResponseEntity<?> departmentGruopList() {
		logger.debug("departmentList call");
		try {
			List<Departments> list = departmentService.selectByGroup();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Departments>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}  
	  @GetMapping("/{id}")
	  public ResponseEntity<Departments> getDepartmentById(@PathVariable int id) throws Exception{
		  Optional<Departments> department = Optional.ofNullable(departmentService.selectDepartmentById(id));
		  return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	  }
	  
	  private ResponseEntity<String> exceptionHandling(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
