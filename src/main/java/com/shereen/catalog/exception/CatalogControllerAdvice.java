package com.shereen.catalog.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CatalogControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND) // 400
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFound(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorStatus", HttpStatus.NOT_FOUND);
		model.setViewName("400-error");
		return model;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGeneralException(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		return model;
	}

}
