package com.helpdesk.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

//public class ErrorController {
	
	private static final Logger LOGGER = Logger.getLogger(ErrorController.class.getName());
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex){
		ModelAndView mv = new ModelAndView();
		LOGGER.info(ex.getLocalizedMessage());
		LOGGER.info( request.getRequestURL());
		 mv.addObject("exception", ex);
		 mv.addObject("url", request.getRequestURL());
		
		mv.setViewName("error");
		return mv;
	}
	  // Convert a predefined exception to an HTTP Status code
	  @ResponseStatus(value=HttpStatus.CONFLICT,
	                  reason="Data integrity violation")  // 409
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  public void conflict() {
	    // Nothing to do
	  }
	  
	  // Specify name of a specific view that will be used to display the error:
	//  @ExceptionHandler({SQLException.class,DataAccessException.class})
	  public String databaseError() {
	    // Nothing to do.  Returns the logical view name of an error page, passed
	    // to the view-resolver(s) in usual way.
	    // Note that the exception is NOT available to this view (it is not added
	    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
	    // below.
	    return "error";
	  }

}
