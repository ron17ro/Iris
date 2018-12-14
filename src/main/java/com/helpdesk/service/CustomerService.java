package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.CustomersRepository;
import com.helpdesk.model.Customer;


@Service
public class CustomerService {
	@Autowired
	private final CustomersRepository customerRepository;	
	public CustomerService(CustomersRepository customerRepository) {
			
			this.customerRepository = customerRepository;
		}
		
	   public Customer findCustomerById(int id) {
	        for (Customer customer : customerRepository.findAll()) {
	            if (customer.getCustomer_id() ==id) {
	                return customer; 
	            }
	        }
	        return null;
	    }
		public List<Customer> findAll(){
			List<Customer> customers = new ArrayList<>();
			for(Customer customer : customerRepository.findAll()) {
				customers.add(customer);
			}
			return customers;
		}
		public void save(Customer customer) {
			customerRepository.save(customer);
		}
		public void delete(int id) {
			customerRepository.delete(id);
		}
	
}
