package com.samplebackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyNewController {

	@RequestMapping(value="/blahblah",method=RequestMethod.GET)
	public ModelAndView n(){
		return new ModelAndView("Hi");
	}
	
}
