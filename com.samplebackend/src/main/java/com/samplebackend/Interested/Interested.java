package com.samplebackend.Interested;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.samplebackend.Event.Event;
import com.samplebackend.user.User;


@Entity
public class Interested {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="eventId")
	private Event eventId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
