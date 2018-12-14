package com.helpdesk.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer>{

		Optional<User> findByUsername(String username);
	
		Optional<User> findByEmail(String email);

	    @Modifying
	    @Query("UPDATE users u SET u.password = :password where u.user_id = :id")
	    void updatePassword(@Param("password") String password, @Param("id") Integer id);

	    Optional<User> findByResetToken(String resetToken);
}
