package com.helpdesk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.dao.UsersRepository;
import com.helpdesk.model.User;


@Service
@EnableAsync(proxyTargetClass=true)
@EnableCaching
public class UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private final UsersRepository usersRepository;
	
	public UserService(UsersRepository usersRepository) {
		
		this.usersRepository = usersRepository;
	}
	
	    public void updatePassword(String password, int userId) {
	        usersRepository.updatePassword(password, userId);
	    }
	
    public User findUserById(int id) {
        for (User user : usersRepository.findAll()) {
            if (user.getUser_id() ==id) {
                return user;
            }
        }
        return null;
    }
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
    }

	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		for(User user : usersRepository.findAll()) {
			users.add(user);
		}
		return users;
	}
	
	public void save(User user) {		
		usersRepository.save(user);
	}

	public void delete(int id) {
		usersRepository.delete(id);
	}
	public Optional<User> findUserByResetToken(String resetToken) {
		return usersRepository.findByResetToken(resetToken);
	}
	public Optional<User> findByEmail(String email) {
		 return usersRepository.findByEmail(email);
		
	}
	public Optional<User> findByUsername(String username) {
		 return usersRepository.findByUsername(username);
		
	}
}
