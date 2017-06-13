package com.samplebackend.Event;

import java.util.List;

public interface EventDAO {

	public void addEvent(Event event);
	public Event getEventById(long id);
	public void deleteEvent(Event event);	
	public List<Event> listEvents();
	
	//public void deleleEvent(Event event);
	
}
