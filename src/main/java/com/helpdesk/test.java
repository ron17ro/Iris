package com.helpdesk;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

	public static void main(String[] args) {

			String password = "admin";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println("admin"+hashedPassword);

			 password = "password1";
			 passwordEncoder = new BCryptPasswordEncoder();
			 hashedPassword = passwordEncoder.encode(password);

			System.out.println("password1 "+hashedPassword);

			 password = "password2";
			 passwordEncoder = new BCryptPasswordEncoder();
			 hashedPassword = passwordEncoder.encode(password);

			System.out.println("password2 " + hashedPassword);

			 password = "password1";
			 passwordEncoder = new BCryptPasswordEncoder();
			 hashedPassword = passwordEncoder.encode(password);

			System.out.println("manager1 " + hashedPassword);

	}

}
