package com.helpdesk.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
