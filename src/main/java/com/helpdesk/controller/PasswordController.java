package com.helpdesk.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helpdesk.model.User;
import com.helpdesk.service.EmailService;
import com.helpdesk.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Display forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView displayForgotPasswordPage() {
		return new ModelAndView("forgotpassword");
    }
    
    // Process form submission from forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String processForgotPasswordForm(ModelMap model, @RequestParam("email") String userEmail, HttpServletRequest request) {

		// Lookup user in database by e-mail
		Optional<User> userDB = userService.findByEmail(userEmail);
		User user = userDB.get();
		if (user == null) {
			
			model.addAttribute("errorMessage", "We didn't find an account associated with this e-mail address.");
		} else {
			
			// Generate random 36-character string token for reset password 
			//User user = optional.get();
			System.out.println("here");
			
			user.setResetToken(UUID.randomUUID().toString());

			// Save token to database
			userService.save(user);
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			//String appUrl = request.getScheme() + "://" + request.getServerName();
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("helpdesk.email1@gmail.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + url
					+ "/reset?token=" + user.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);

			// Add success message to view
			model.addAttribute("successMessage", "A password reset link has been sent to " + userEmail);
		}

		
		return "forgotpassword";

	}

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String displayResetPasswordPage(ModelMap model,HttpServletRequest request) {
		String requestToken =request.getParameter("token");
		Optional<User> userReset = userService.findUserByResetToken(requestToken);
		
		if (userReset.isPresent()) { // Token found in DB
			User user = userReset.get();
			model.put("token", requestToken);
		} else { // Token not found in DB
			model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
		}

	
		return "resetpassword";
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String setNewPassword(@RequestParam("token") String token, ModelMap model,HttpServletRequest request, RedirectAttributes redir) {
		// Find the user associated with the reset token
		
		//if(resetToken!=null)
		Optional<User> resetUser = userService.findUserByResetToken(token);
		
		
		
		// This should always be non-null but we check just in case
		if (resetUser.isPresent()) {
			User user = resetUser.get();
		
			// Set new password    
			user.setPassword(bCryptPasswordEncoder.encode(request.getParameter("password")));
            
			// Set the reset token to null so it cannot be used again
			user.setResetToken(null);

			// Save user 
			userService.save(user); 

			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

			 return "redirect:/login";
			 
		} else {
			model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
			
		}
		
		return "resetpassword";
   }
   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}
}