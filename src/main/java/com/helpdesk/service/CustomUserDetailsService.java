package com.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.dao.UsersRepository;
import com.helpdesk.model.CustomUserDetails;
import com.helpdesk.model.User;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUsers = usersRepository.findByUsername(username);

		optionalUsers
			.orElseThrow(() ->new UsernameNotFoundException("User not found"));
		
		return optionalUsers
			.map(CustomUserDetails::new).get();
	}
//	public User findByEmail(String email) {
//		 return usersRepository.findByEmail(email);
//		
//	}
	/*@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@Override
	public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
	    if (emailExist(accountDto.getEmail())) {
	        throw new EmailExistsException(
	          "There is an account with that email adress:" + accountDto.getEmail());
	    }
	    User user = new User();
	    user.setFirstName(accountDto.getFirstName());
	    user.setLastName(accountDto.getLastName());
	     
	    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	     
	    user.setEmail(accountDto.getEmail());
	    user.setRole(new Role(Integer.valueOf(1), user));
	    return repository.save(user);
	}*/

}
