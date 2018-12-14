package com.helpdesk.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.TicketModel;

@Repository
public interface TicketsRepository extends CrudRepository<TicketModel, Integer>{
	//List<TicketModel> findByOwnerLike(Integer owner);
}
