package com.helpdesk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity(name="users")
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="first_name")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String first_name;
	
	@Column(name="last_name")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String last_name;
	
	@Column(name="username")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String username;
	
	private String password;
	/*
	@Column(name="manager")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String manager;*/
	
	@IndexedEmbedded	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "department")
	private Department department;
	
	@Column(name="email")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String email;
	
	@Column(name="phone")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private int phone;
	private Date created;
	
	 @IndexedEmbedded
	 @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	 @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	 private List<Role> roles = new ArrayList<Role>();
	 @Column(name = "reset_token")
	 private String resetToken;
	 @Column(name = "picture")
	 private String picture;


	public User() {
		 
	 }
	
	 public User(User users) {
			this.user_id=users.getUser_id();
			this.first_name=users.getFirst_name();
			this.last_name=users.getLast_name();
			this.username=users.getUsername();
			this.password=users.getPassword();
			//this.manager=users.getManager();
			this.department=users.getDepartment();
			this.email=users.getEmail();
			this.phone=users.getPhone();
			this.created=users.getCreated();
			this.roles= users.getRoles();
	}


	public User(int user_id, String first_name, String last_name, String username, String password,
			Department department, String email, int phone, Date created, List<Role> roles) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.department = department;
		this.email = email;
		this.phone = phone;
		this.created = created;
		this.roles = roles;
	}

	@Override
		public String toString() {
			return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", username="
					+ username + ", password=" + password + ",  department=" + department
					+ ", email=" + email + ", phone=" + phone + ", created=" + created + ", roles=" + roles + "]";
		}

	public String getName() {
		return first_name+ " " + last_name;
	}
	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;//(String)passwordEncoder.encode(password);
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFirst_name() {
		return this.first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return this.last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/*public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}*/
	public Department getDepartment() {
		return this.department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return this.phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	 public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
