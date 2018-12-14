package com.helpdesk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.helpdesk.model.Customer;
import com.helpdesk.service.CustomerService;
import com.helpdesk.service.HibernateSearchService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private HibernateSearchService hibernateSearchService;
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/all-customers", method = RequestMethod.GET)
	public String showCustomers(ModelMap model) {
		model.addAttribute("customers",  customerService.findAll());
		//model.put("customers",  customerService.findAll());
		return "all-customers";
	}
	

	@RequestMapping(value = "/view-customer", method = RequestMethod.GET)
	public String showCustomer(@RequestParam("id") int id,ModelMap model) {
		model.addAttribute("flag", true);	
		Customer customer = customerService.findCustomerById(id);		
		model.put("addCustomer", customer);
		return "customer";
	}
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/add-customer", method = RequestMethod.GET )
	public String showAddTicketPage(ModelMap model) {
		model.addAttribute("flag", false);	
		model.addAttribute("addCustomer", new Customer());
		model.addAttribute("edit", false);	
		return "customer";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/add-customer", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute Customer customer, ModelMap model){
		model.addAttribute("edit", false);	
		customerService.save(customer);
		model.addAttribute("customers", customerService.findAll());
		return "all-customers";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/delete-customer", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		if(id==0)
			throw new RuntimeException("Something went wrong");
		
		customerService.delete(id);
		return "redirect:/all-customers";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/update-customer", method = RequestMethod.GET)
	public String showUpdateCustomer(@RequestParam int id, ModelMap model) {		
		model.addAttribute("edit", true);	
		Customer customer = customerService.findCustomerById(id);		
		model.put("addCustomer", customer);
		return "customer";
	}
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/update-customer", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute Customer customer, ModelMap model) {		
		model.addAttribute("edit", true);	
		customerService.save(customer);
		return "all-customers";
	}
	
	@PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
	@RequestMapping(value = "/search-customers", method = RequestMethod.GET)
    public String searchCustomers(@ModelAttribute String search,BindingResult bindingResult, HttpServletRequest request) {
		String searchText = request.getParameter("search");		
		
        List<Customer> searchResults = null;
        
        if(searchText != "") {
	        try {
	            searchResults = hibernateSearchService.fuzzySearchCustomers(searchText);
	            request.setAttribute("search", searchResults);          
	            request.setAttribute("customers", searchResults);
	        } catch (Exception ex) {
	           ex.printStackTrace();
	        }
        }
        else {
        	request.setAttribute("customers", customerService.findAll());
        }
        return "all-customers";
    }
}
