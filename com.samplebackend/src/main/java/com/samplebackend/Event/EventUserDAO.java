package com.samplebackend.Event;

import java.util.List;



public interface EventUserDAO {
	
	public List<EventUser> getall();
	public void addEventApplied(EventUser eventUser);

}
