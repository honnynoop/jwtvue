package com.ssafy.edu.employee.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.ssafy.edu.employee.model.Employees;
import com.ssafy.edu.employee.model.EmployeesExample;
import com.ssafy.edu.util.PageRequest;



public interface EmployeeService {

	List<Employees> selectByEmployee() throws SQLException;

	Employees selectByPrimaryKey(int empid) throws SQLException;
	
	PageInfo<Employees>  selectAllEmployeePages() throws SQLException;
	PageInfo<Employees> selectAllEmployeePagesWith(PageRequest preq)throws SQLException;
	
	PageInfo<Employees> selectAllEmployeePagesWithSearch(PageRequest preq) throws SQLException ;
	PageInfo<Employees> selectEmployee(PageRequest preq)throws SQLException ;
	
	List<Employees> selectByExampleWithLimit(int limit,int offset);

	void addEmployee(Employees pemp);
}
