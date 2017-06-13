package com.samplebackend.Event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.samplebackend.user.User;

@Entity
@Table(name="Event_USER")
public class EventUser {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long EventUserId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name="eventId")
	private Event eventId;

	public long getEventUserId() {
		return EventUserId;
	}

	public void setEventUserId(long eventUserId) {
		EventUserId = eventUserId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}
	
	
	
	
}
