package com.helpdesk.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.Priority;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Integer> {

}
