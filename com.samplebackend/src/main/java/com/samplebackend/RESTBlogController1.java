package com.samplebackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTBlogController1 {

	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public ResponseEntity<String> abc()
	{
		return new ResponseEntity<String>("Hi",HttpStatus.OK);
	}
	
}
