package com.helpdesk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity
@Table(name="customer")
public class Customer {
	@Id
	@Column(name="customer_id")
	@GeneratedValue
	private int customer_id;
	
	@Column(name="customer_name")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String 	customer_name;
	
	@Column(name="contact_person")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String 	contact_person;
	
	@Column(name="email")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String 	email;
	
	@Column(name="phone")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String phone;
	
	@Column(name="address")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String address;
	
	@Column(name="contract_number")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String contract_number;

	
	public Customer() {
		
	}
	
	public Customer(int customer_id, String customer_name, String contact_person, String email, String phone, String address,
			String contract_number) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.contact_person = contact_person;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.contract_number = contract_number;

	}

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", contact_person="
				+ contact_person + ", phone=" + phone + ", address=" + address + ", contract_number=" + contract_number
				+ "]";
	}
	
	
	
}
