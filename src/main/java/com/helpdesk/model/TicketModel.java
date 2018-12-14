package com.helpdesk.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;


@Indexed
@Entity
@Table(name="ticket")
public class TicketModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ticket_id")
	private int ticket_id;
	
	@IndexedEmbedded
	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinColumn(name="priority")
	private Priority priority;
	
	@IndexedEmbedded
	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinColumn(name="customer")
	private Customer customer;
	
	@IndexedEmbedded
	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinColumn(name="owner")
	private User owner;
	 
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String subject;
	
	@IndexedEmbedded
	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinColumn(name ="status")
	private Status status;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String description;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String category;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date creation_date;
	

	
	public TicketModel(){

	}
	
//	@Override
//	public String toString() {
//		return "TicketModel [ticket_id=" + ticket_id + ", "
//				+ "priority=" + priority.getPriority_name().toString() + ", "
//				+ "customer=" + customer.getCustomer_name().toString() + ", owner="
//				+ owner.getFirst_name() + ", subject=" + subject.toString() 
//				+ ", status=" + status.getStatus_name().toString() + ", description=" + description.toString()
//				+ ", category="	+ category + ", creation_date=" + creation_date.toString() + "]";
//	}


	public TicketModel(int ticket_id, Priority priority, Customer customer, User owner, String subject,
			Status status, String description, String category, Date creation_date) {
		super();
		this.ticket_id = ticket_id;
		this.priority = priority;
		this.customer = customer;
		this.owner = owner;
		this.subject = subject;
		this.status = status;
		this.description = description;
		this.category = category;
		this.creation_date = creation_date;
	}



	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}


	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Customer getCustomer() {
			return customer;//.getCustomer_name();
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getOwner() {
			return owner;//.getName();
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Status getStatus() {
		return status;//.getStatus_name();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreation_date() {
		return creation_date;
	} 

	public void setCreation_date(Date creation_date) {
	    SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String strDate = sm.format(creation_date);
		
		try {
			this.creation_date =  sm.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	


}
