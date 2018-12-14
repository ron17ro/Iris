package com.helpdesk.service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.TicketsRepository;
import com.helpdesk.model.TicketModel;

@Service
public class TicketService {
	@Autowired
	private final TicketsRepository ticketRepository;
	private static final Logger LOGGER = Logger.getLogger(TicketService.class.getName());
	public TicketService(TicketsRepository ticketRepository) {
		
		this.ticketRepository = ticketRepository;
	}
	
	public List<TicketModel> findAll(){
		List<TicketModel> tickets = new ArrayList<>();
		for(TicketModel ticket : ticketRepository.findAll()) {
			tickets.add(ticket);
		}
		return tickets;
	} 
	public TicketModel findTicketById(int id) {
        for (TicketModel ticket : ticketRepository.findAll()) {
            if (ticket.getTicket_id() ==id) {
                return ticket;
            }
        }
        return null;
    }
	public void save(TicketModel ticket) {
		ticketRepository.save(ticket);
	}
	public void delete(int id) {
		ticketRepository.delete(id);
	}
}
