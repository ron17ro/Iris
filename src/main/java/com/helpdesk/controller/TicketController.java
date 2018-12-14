package com.helpdesk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.model.TicketModel;
import com.helpdesk.model.User;
import com.helpdesk.service.CustomUserDetailsService;
import com.helpdesk.service.CustomerService;
import com.helpdesk.service.HibernateSearchService;
import com.helpdesk.service.PriorityService;
import com.helpdesk.service.StatusService;
import com.helpdesk.service.TicketService;
import com.helpdesk.service.UserService;

@Controller
public class TicketController {
	
	@Autowired
	private TicketService ticketService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StatusService statusService;
	 
	
	@Autowired
	private PriorityService priorityService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	
	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public @ResponseBody List<String>  dispatch() {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		User user = (User) userDetailsService.loadUserByUsername(auth.getName());
		List<String> response = new ArrayList<>();
		String userID = String.valueOf(user.getUser_id());
		String userName = user.getName();
		response.add(userID);
		response.add(userName);
		
		return response; 

	}
	@RequestMapping(value = "/close", method = RequestMethod.GET)
	public @ResponseBody String  close(@ModelAttribute TicketModel ticket, ModelMap model) {	
		
		ticketService.save(ticket);
		return "ticket";
	}
	
	
	@RequestMapping(value = "/all-tickets", method = RequestMethod.GET)
	public String showTickets(ModelMap model) {
		model.put("tickets",  ticketService.findAll());
		return "all-tickets";
	}
	
	//@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping(value = "/add-ticket", method = RequestMethod.GET )
	public String showAddTicketPage(ModelMap model) {
		model.put("edit", false);		
		model.put("addTicket", new TicketModel());
	//	model.put("currentUser", userService.loadUserByUsername(getLoggedInUserName(model)));
		model.put("userID", 1);
		model.put("defaultUser", userService.findUserById(1));
		model.put("ownerName", userService.findUserById(1));
		model.put("priorities", priorityService.findAll());
		model.put("statuses", statusService.findAll());
		model.put("customers", customerService.findAll());
		return "ticket";
	}
	
	@RequestMapping(value = "/add-ticket", method = RequestMethod.POST )
	public String createTicketPage(@ModelAttribute TicketModel ticket, ModelMap model, HttpServletRequest request) {
		model.addAttribute("edit", false);
		int ownerID = Integer.parseInt((String)request.getParameter("ownerID"));
		ticket.setOwner(userService.findUserById(ownerID));
		ticket.setCreation_date(new Date());
		System.out.println(ticket.toString());
		ticketService.save(ticket);
		model.addAttribute("tickets", ticketService.findAll());
		return "all-tickets";
	}
	
	@RequestMapping(value = "/update-ticket", method = RequestMethod.GET )
	public String showUpdateTicketPage(@RequestParam int id, ModelMap model, HttpServletRequest request) {
		TicketModel ticket = ticketService.findTicketById(id);
		int ownerID = ticket.getOwner().getUser_id();
		User ownerName = ticket.getOwner();
		model.addAttribute("defaultID", ownerID);
		model.addAttribute("defaultUser", ownerName);
		model.addAttribute("addTicket", ticket);		 
		model.addAttribute("edit", true);
		model.put("priorities", priorityService.findAll());
		model.put("statuses", statusService.findAll());
		model.put("customers", customerService.findAll());
		return "ticket";
	}
	
	@RequestMapping(value = "/update-ticket", method = RequestMethod.POST )
	public String updateTicketPage(@ModelAttribute TicketModel ticket, ModelMap model, HttpServletRequest request) {
		model.addAttribute("edit", true);
		ticketService.save(ticket);
		model.addAttribute("tickets", ticketService.findAll());
		return "all-tickets";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/delete-ticket", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		if(id==0)
			throw new RuntimeException("Something went wrong");
		
		ticketService.delete(id);
		return "redirect:/all-tickets";
	}

	@Autowired
	private HibernateSearchService hibernateSearchService;
	private static final Logger LOGGER = Logger.getLogger(TicketController.class.getName());
	 
	
	@RequestMapping(value = "/search-tickets", method = RequestMethod.GET)
    public String search(@ModelAttribute String search,BindingResult bindingResult, HttpServletRequest request) {
		String searchText = request.getParameter("search");
		LOGGER.info("search param is " + searchText);
        List<TicketModel> searchResults = null;

        if(searchText != "") {
	        try {
	
	            searchResults = hibernateSearchService.fuzzySearchTickets(searchText);
	            System.out.println(searchResults.size());
	            LOGGER.info(searchResults.toString());
	            request.setAttribute("search", searchResults);          
	            request.setAttribute("tickets", searchResults);
	        } catch (Exception ex) {
	           ex.printStackTrace();
	        }
        }
        else {
        	request.setAttribute("tickets", ticketService.findAll());
        }
        return "all-tickets";
    }
}
