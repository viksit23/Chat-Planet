package com.samplebackend;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.Event.Event;
import com.samplebackend.Event.EventDAO;

@RestController
public class RESTEventController {
	
	@Autowired
	EventDAO eventdao;
	
	
	@RequestMapping(value="/postevent", method=RequestMethod.POST)
	public ResponseEntity<List<Event>> postEvent(@RequestBody JSONObject data) {
		System.out.println(data);

		Date date = new Date();
		System.out.println(date);

		Event event = new Event();
		
		event.setTitle(data.get("EventTitle").toString());
		event.setDescription(data.get("EventDesc").toString());
		event.setPostdate(date);
		event.setEventFrom(data.get("EventDateFrom").toString());
		event.setEventTo(data.get("EventDateTo").toString());

		eventdao.addEvent(event);

		List<Event> list = eventdao.listEvents();

		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
	}
	
	
	
	
}
