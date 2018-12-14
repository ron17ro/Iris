package com.helpdesk.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
