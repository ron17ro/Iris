package com.helpdesk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

}
