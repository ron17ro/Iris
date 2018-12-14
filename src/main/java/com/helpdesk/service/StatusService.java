package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.dao.StatusRepository;
import com.helpdesk.model.Status;


@Service
@Transactional
public class StatusService {
	private final StatusRepository statusRepository;	
	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	public List<Status> findAll(){
		List<Status> statuses = new ArrayList<>();
		for(Status status : statusRepository.findAll()) {
			statuses.add(status);
		}
		return statuses;
	}
}
