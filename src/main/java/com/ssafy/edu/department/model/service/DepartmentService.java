package com.ssafy.edu.department.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.edu.department.model.Departments;
import com.ssafy.edu.department.model.DepartmentsExample;


public interface DepartmentService {
	List<Departments> selectByDepartment() throws SQLException;

	Departments selectDepartmentById(int id) throws SQLException;
	
	List<Departments> selectByGroup() throws SQLException;
}
