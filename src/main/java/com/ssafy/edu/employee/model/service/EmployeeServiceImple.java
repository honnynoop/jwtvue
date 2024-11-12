package com.ssafy.edu.employee.model.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.edu.employee.model.Employees;
import com.ssafy.edu.employee.model.EmployeesExample;
import com.ssafy.edu.employee.model.mapper.EmployeesMapper;
import com.ssafy.edu.util.PageRequest;
import com.ssafy.edu.util.ToLike;




@Service
public class EmployeeServiceImple implements EmployeeService {

	//@Autowired
	private EmployeesMapper employeeMapper;
	
	public EmployeeServiceImple(EmployeesMapper employeeMapper) {
		super();
		this.employeeMapper = employeeMapper;
	}

	@Override
	public List<Employees> selectByEmployee() throws SQLException {
		EmployeesExample empcriteria=new EmployeesExample();
		empcriteria.setOrderByClause("employee_id desc");
		return employeeMapper.selectByExample(empcriteria);
	}

	@Override
	public Employees selectByPrimaryKey(int empid) throws SQLException {
		return employeeMapper.selectByPrimaryKey(empid);
	}
    // 
	@Override
	public PageInfo<Employees> selectAllEmployeePages() throws SQLException {
		EmployeesExample empcriteria=new EmployeesExample();
		empcriteria.setOrderByClause("employee_id desc");
		//(5-1)*10 ~ 5*10
		PageHelper.startPage(5, 10);
		//PageHelper.offsetPage(0, 10);
		List<Employees> list= employeeMapper.selectByExample(empcriteria);
	    PageInfo<Employees> page = new PageInfo<Employees>(list);
		return page;
	}
/*
 pageNum, pageSize
 {
  "total": 107,
  "list": [
    {
      "employeeId": 166,
      "firstName": "Sundar",
      "lastName": "Ande",
      "email": "SANDE",
      "phoneNumber": "011.44.1346.629268",
      "hireDate": "2000-02-20T15:00:00.000+00:00",
      "jobId": "SA_REP",
      "salary": 6400,
      "commissionPct": 0.1,
      "managerId": 147,
      "departmentId": 80
    },
    ...
    ],
  "pageNum": 5,
  "pageSize": 10,
  "size": 10,
  "startRow": 41,
  "endRow": 50,
  "pages": 11,
  "prePage": 4,
  "nextPage": 6,
  "isFirstPage": false,
  "isLastPage": false,
  "hasPreviousPage": true,
  "hasNextPage": true,
  "navigatePages": 8,
  "navigatepageNums": [
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8
  ],
  "navigateFirstPage": 1,
  "navigateLastPage": 8
}
 
 
 */

	@Override
	public PageInfo<Employees> selectAllEmployeePagesWithSearch(PageRequest preq) throws SQLException {
		EmployeesExample empcriteria=new EmployeesExample();
		empcriteria.setOrderByClause("employee_id desc");
		empcriteria.createCriteria().andFirstNameLike(preq.getSearch());
		
		//(pageNum-1)*pageSize ~ pageNum*pageSize
		PageHelper.startPage(preq.getPageNum(), preq.getPageSize());
		//PageHelper.offsetPage(0, 10);
		List<Employees> list= employeeMapper.selectByExample(empcriteria);
	    PageInfo<Employees> page = new PageInfo<Employees>(list);
		return page;
	}

	@Override
	public PageInfo<Employees> selectAllEmployeePagesWith(PageRequest preq) throws SQLException {
		EmployeesExample empcriteria=new EmployeesExample();
		if(preq.getKeyword()!=null && preq.getKeyword().equals("first_name")) {
			empcriteria.or().andFirstNameLike(ToLike.toLike(preq.getSearch()));
		}else if(preq.getKeyword()!=null && preq.getKeyword().equals("email")) {
			empcriteria.or().andEmailLike(ToLike.toLike(preq.getSearch()) );
		}
		
		//(pageNum-1)*pageSize ~ pageNum*pageSize
		PageHelper.startPage(preq.getPageNum(), preq.getPageSize());
		//PageHelper.offsetPage(1 order by ${orderByClause}, 10);
		
		List<Employees> list= employeeMapper.selectByExample(empcriteria);
	    PageInfo<Employees> page = new PageInfo<Employees>(list);
		return page;
	}
	
	@Override
	public PageInfo<Employees> selectEmployee(PageRequest preq) throws SQLException {
		EmployeesExample empcriteria=new EmployeesExample();
		//(pageNum-1)*pageSize ~ pageNum*pageSize
		PageHelper.startPage(preq.getPageNum(), preq.getPageSize());
		//PageHelper.offsetPage(0, 10);
		List<Employees> list= employeeMapper.findByPagingAndSearch(empcriteria);
	    PageInfo<Employees> page = new PageInfo<Employees>(list);
		return page;
	}

	@Override
	public List<Employees> selectByExampleWithLimit(int limit, int offset) {
		EmployeesExample example = new EmployeesExample();
		//example.createCriteria().andCommissionPctIsNotNull();

		// 페이징 값 설정 (offset 10, limit 20)
		List<Employees> customers = employeeMapper.selectByExampleWithLimit(example, limit, offset);
		return customers;
	}

	@Override
	public void addEmployee(Employees pemp) {
		employeeMapper.insertSelective(pemp);
	}
	
	
}
