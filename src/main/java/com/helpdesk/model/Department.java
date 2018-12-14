package com.helpdesk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Table(name="department")
@Entity
public class Department {

	@Id
	@Column(name="department_id")
	private int department_id;
	
	@Column(name="department_name",insertable=false, updatable=false)
	@Field
	private String department_name;
	
	@Column(name="manager")
	@Field
	private String manager;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable
	private List<User> users = new ArrayList<>();
	
	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
}
