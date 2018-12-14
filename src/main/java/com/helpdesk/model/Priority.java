package com.helpdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


@Indexed
@Entity
@Table(name="ticket_priority")
public class Priority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int priority_id;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="priority_name")
	private String priority_name;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="priority_description")
	private String priority_description;
	
	
	public int getPriority_id() {
		return priority_id;
	}
	public void setPriority_id(int priority_id) {
		this.priority_id = priority_id;
	}
	public String getPriority_name() {
		return priority_name;
	}
	public void setPriority_name(String priority_name) {
		this.priority_name = priority_name;
	}
	public String getPriority_description() {
		return priority_description;
	}
	public void setPriority_description(String priority_description) {
		this.priority_description = priority_description;
	}

}
