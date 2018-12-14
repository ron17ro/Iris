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
@Table(name="ticket_status")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="status_id")
	private int status_id;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="status_name")
	private String status_name;
	
	public Status() {		
	}
	
	
	
	public Status(int status_id, String status_name) {
		super();
		this.status_id = status_id;
		this.status_name = status_name;
	}



	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", status_name=" + status_name + "]";
	}
	
	
}
