package com.samplebackend;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.Event.Event;
import com.samplebackend.Event.EventDAO;
import com.samplebackend.Event.EventUser;
import com.samplebackend.Event.EventUserDAO;
import com.samplebackend.job.Job;
import com.samplebackend.job.JobUser;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
public class RESTEventController {
	
	@Autowired
	EventDAO eventdao;
	@Autowired
	UserDAO userdao;
	@Autowired
	EventUserDAO eventuserdao;
	
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
	
	@RequestMapping("/viewevents")
	public ResponseEntity<List<Event>> events() {

		List<Event> list = eventdao.listEvents();

		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);

	}
	@RequestMapping("/deleteevent/{eventId}")
	public ResponseEntity<List<Event>> deleteEvent(@PathVariable("eventId") int eventId) {
				
		Event event  = eventdao.getEventById(eventId);	
		
		event.setPosted(false);
		
		eventdao.addEvent(event);		
		List<Event> list = eventdao.listEvents();
		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updateevent", method=RequestMethod.POST)
	public ResponseEntity<List<Event>> editEvent(@RequestBody JSONObject data) {
		System.out.println(data);
		
		Event event = eventdao.getEventById(Integer.parseInt(data.get("EventId").toString()));
		
		event.setEventId(Integer.parseInt(data.get("EventId").toString()));
		event.setTitle(data.get("EventTitle").toString());
		event.setDescription(data.get("EventDesc").toString());
		
		event.setEventFrom(data.get("EventDateFrom").toString());
		event.setEventTo(data.get("EventDateTo").toString());

		eventdao.addEvent(event);
		
		List<Event> list = eventdao.listEvents();
		
		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);

		
	}
	
	@RequestMapping(value="/applyevent", method=RequestMethod.POST)
	public ResponseEntity<String> applyjob(@RequestBody JSONObject data){
		

		System.out.println(data);
		
		Event event = eventdao.getEventById(Integer.parseInt((data.get("EventID").toString())));
		User user2 = userdao.getUserByEmail(data.get("User").toString());
		
		EventUser eventUser = new EventUser();
		
		eventUser.setEventId(event);
		eventUser.setUserId(user2);
		
		
		eventuserdao.addEventApplied(eventUser);
		
		JSONObject json = new JSONObject();

		json.put("status", "Job is Applied");
		System.out.println(json.toString());
		
		

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	
	
}
