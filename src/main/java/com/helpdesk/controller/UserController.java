package com.helpdesk.controller;


import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helpdesk.model.Role;
import com.helpdesk.model.User;
import com.helpdesk.service.DepartmentService;
import com.helpdesk.service.FileSystemStorageService;
import com.helpdesk.service.HibernateSearchService;
import com.helpdesk.service.RoleService;
import com.helpdesk.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FileSystemStorageService storageService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/all-users", method = RequestMethod.GET)
	public String showUsers(ModelMap model) {
		model.addAttribute("users",  userService.findAll());
		return "all-users";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/add-user", method = RequestMethod.GET )
	public String showAddTicketPage(ModelMap model,  HttpServletRequest request) {
		model.addAttribute("flag", false);
		request.setAttribute("addUser", new User());
		request.setAttribute("departments", departmentService.findAll());
		request.setAttribute("roles", roleService.findAll());
		request.setAttribute("edit", false);
		return "user";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String saveUser(@RequestParam("profilePicture") MultipartFile profilePicture,@ModelAttribute User user,
			ModelMap model,HttpServletRequest request,RedirectAttributes redirectAttributes){		
		
		System.out.print(user.getRoles().toString());
		try {
			storageService.store(profilePicture);
			System.out.println("You successfully uploaded " + profilePicture.getOriginalFilename() + "!");
			model.addAttribute("message", "You successfully uploaded " + profilePicture.getOriginalFilename() + "!");
			//file.add(file.getOriginalFilename());
		} catch (Exception e) {
			model.addAttribute("message", "FAIL to upload " + profilePicture.getOriginalFilename() + "!");
		}
		
		List<Role> r =user.getRoles();
		for(Role role:r) {
			if(role==null) {
				r.remove(role);
			}
		}
		user.setPicture(profilePicture.getOriginalFilename());
		user.setRoles(r);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		request.setAttribute("users", userService.findAll());
		request.setAttribute("edit", false);
		return "all-users";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		if(id==0)
			throw new RuntimeException("Something went wrong");
		
		userService.delete(id);
		return "redirect:/all-users";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/update-user", method = RequestMethod.GET)
	public String displayUpdateUser(@RequestParam int id, ModelMap model, HttpServletRequest request) {
		User user = userService.findUserById(id);
		//user.setPassword(passwordEncoder.encode(user.getPassword()));		
		model.addAttribute("flag", false);
		request.setAttribute("addUser", user);
		request.setAttribute("departments", departmentService.findAll());
		request.setAttribute("roles", roleService.findAll());
	//	request.setAttribute("profilePicture", user.getPicture());
		
		try{
			if(user.getPicture() != null && !user.getPicture().equals("")) {
				model.addAttribute("profilePictureDisplay", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
					 "serveFile",  user.getPicture()).build());
				request.setAttribute("profilePicture", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
					 "serveFile",  user.getPicture()).build());
			}
			else {
				model.addAttribute("profilePictureDisplay", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
						 "serveFile",  "avatar.jpg").build());
				request.setAttribute("profilePicture", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
						 "serveFile",  "avatar.jpg").build());
				
			}
		}catch(Exception e){
			throw e;
		}
		System.out.println(user.getPicture()+"");
		request.setAttribute("edit", true);
		return "user";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute User user,@ModelAttribute("profilePicture") MultipartFile profilePicture, HttpServletRequest request, ModelMap model) {
		request.setAttribute("edit", true);			
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPicture(profilePicture.getOriginalFilename());

		try {
			storageService.store(profilePicture);
			System.out.println("You successfully uploaded " + profilePicture.getOriginalFilename() + "!");
			model.addAttribute("message", "You successfully uploaded " + profilePicture.getOriginalFilename() + "!");

		} catch (Exception e) {
			model.addAttribute("message", "FAIL to upload " + profilePicture.getOriginalFilename() + "!");
		}
		userService.save(user);
		request.setAttribute("users", userService.findAll()); 
		return "all-users";
	}
	@RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
	public String editPRofileGet(ModelMap model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		User user = userService.findByUsername(auth.getName()).get();
		request.setAttribute("addUser", user);
		request.setAttribute("departments", departmentService.findAll());
		request.setAttribute("roles", roleService.findAll());
		request.setAttribute("profilePicture", user.getPicture());
		
		try{
			if(user.getPicture() != null && !user.getPicture().equals("")) {
				model.addAttribute("profilePictureDisplay", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
					 "serveFile",  user.getPicture()).build());
				request.setAttribute("profilePicture", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
					 "serveFile",  user.getPicture()).build());
			}
			else {
				model.addAttribute("profilePictureDisplay", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
						 "serveFile",  "avatar.jpg").build());
				request.setAttribute("profilePicture", MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, 
						 "serveFile",  "avatar.jpg").build());
				
			}
		}catch(Exception e){
			throw e;
		}
	//	System.out.println(user.getPicture()+"");
		request.setAttribute("edit", true);
		return "user";
	}
	
 
	@RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
	public String editProfilePost(@ModelAttribute User user,@ModelAttribute("profilePicture") MultipartFile profilePicture, HttpServletRequest request, ModelMap model) {
		request.setAttribute("edit", true);			

		user.setPicture(profilePicture.getOriginalFilename());
		
		try {
			storageService.store(profilePicture);
			System.out.println("You successfully uploaded " + profilePicture.getOriginalFilename() + "!");
			model.addAttribute("message", "You successfully uploaded " + profilePicture.getOriginalFilename() + "!");

		} catch (Exception e) {
			model.addAttribute("message", "FAIL to upload " + profilePicture.getOriginalFilename() + "!");
		}
		user.setPassword(request.getParameter("password"));
		userService.save(user);
		request.setAttribute("users", userService.findAll()); 
		return "all-users";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/view-user", method = RequestMethod.GET)
	public String viewUser(@RequestParam int id, ModelMap model) {
		User user = userService.findUserById(id);
		model.put("user", user);
		model.put("flag", true);
		model.put("hiddenFlag", "hidden");
		return "all-users";
	}
	
	@Autowired
	private HibernateSearchService hibernateSearchService;
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/search-users", method = RequestMethod.GET)
    public String search(@ModelAttribute String search,BindingResult bindingResult, HttpServletRequest request) {
		String searchText = request.getParameter("search");
		LOGGER.info("search param is " + searchText);
        List<User> searchResults = null;
        
        if(searchText != "") {
        try { 
            searchResults = hibernateSearchService.fuzzySearchUsers(searchText);
            System.out.println(searchResults.size());
            LOGGER.info(searchResults.toString());
            request.setAttribute("search", searchResults);          
            request.setAttribute("users", searchResults);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        }else {
        	 request.setAttribute("users", userService.findAll());
        }
       
       
        return "all-users";
    }

}
