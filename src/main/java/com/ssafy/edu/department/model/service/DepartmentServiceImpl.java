package com.ssafy.edu.department.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.edu.department.model.Departments;
import com.ssafy.edu.department.model.DepartmentsExample;
import com.ssafy.edu.department.model.mapper.DepartmentsMapper;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentsMapper departmentsMapper;
	
	public DepartmentServiceImpl(DepartmentsMapper departmentsMapper) {
		super();
		this.departmentsMapper = departmentsMapper;
	}

	@Override
	public List<Departments> selectByDepartment() throws SQLException {
		DepartmentsExample depcriteria=new DepartmentsExample();
		depcriteria.setOrderByClause("department_id asc");
		return departmentsMapper.selectByExample(depcriteria);
	}

	@Override
	public Departments selectDepartmentById(int id) throws SQLException {
		return departmentsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Departments> selectByGroup() throws SQLException {
		DepartmentsExample depcriteria=new DepartmentsExample();
		depcriteria.setOrderByClause("department_id asc");
		return departmentsMapper.selectByGroup(depcriteria);
	}

}
