package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.PriorityRepository;
import com.helpdesk.model.Priority;

@Service
public class PriorityService {
	@Autowired
	private final PriorityRepository priorityRepository;

	public PriorityService(PriorityRepository priorityRepository) {
		
		this.priorityRepository = priorityRepository;
	}
	

	public List<Priority> findAll(){
		List<Priority> priorities = new ArrayList<>();
		for(Priority priority : priorityRepository.findAll()) {
			priorities.add(priority);
		}
		return priorities;
	}
	
}
