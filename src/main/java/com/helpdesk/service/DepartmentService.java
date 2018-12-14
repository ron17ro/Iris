package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.DepartmentRepository;
import com.helpdesk.model.Department;

@Service
public class DepartmentService {
	@Autowired
	private final DepartmentRepository departmentRepository;	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll(){
		List<Department> departments = new ArrayList<>();
		for(Department department : departmentRepository.findAll()) {
			departments.add(department);
		}
		return departments;
	}
	
}
