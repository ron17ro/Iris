package com.helpdesk.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.Customer;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Integer>{
	
}
