package com.helpdesk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.rest.NetClient;
@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "homepage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	} 
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String returnLoginPage(ModelMap model) {
//		//model.put("name", getLoggedinUserName());
//		return "login";
//	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {		
		model.put("name", getLoggedinUserName());
		return "welcome";
	}
	
	@RequestMapping(value = "/search-dictionary", method = RequestMethod.GET)
	public @ResponseBody String searchOxfordDictonary(@ModelAttribute String search, ModelMap model, HttpServletRequest request) {
		String wordToBeSearched =  request.getParameter("search");
		System.out.println(wordToBeSearched);
		
		if(wordToBeSearched!="") {
			try {
				String definitions = NetClient.netClientGet(wordToBeSearched);
				return definitions;
			}catch(Exception e) {				
				model.addAttribute("invalidSearch","Te word is invalid or could not be found.");
			}
		}

		return "homepage";
	}
	
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

}
