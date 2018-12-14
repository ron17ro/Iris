package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.RoleRepository;
import com.helpdesk.model.Role;

@Service
public class RoleService {
	@Autowired
	private final RoleRepository rolesRepository;	
	
	public RoleService(RoleRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		for(Role role : rolesRepository.findAll()) {
			roles.add(role);
		}
		return roles;
	}
	
	public Role findByRoleName(String roleName) {
		if (roleName != null)
			for(Role role : rolesRepository.findAll()) {
				if(role.getRole_name().equals(roleName)) {
					return role;
				}
		
			}
		return null;
	}
	
}
