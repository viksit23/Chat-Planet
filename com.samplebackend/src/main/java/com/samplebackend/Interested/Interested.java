package com.samplebackend.Interested;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.samplebackend.Event.Event;
import com.samplebackend.user.User;


@Entity
public class Interested {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToMany
	@JoinColumn(name="userId")
	private List<User> userId;
	@ManyToMany
	@JoinColumn(name="eventId")
	private List<Event> eventId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<User> getUserId() {
		return userId;
	}
	public void setUserId(List<User> userId) {
		this.userId = userId;
	}
	
//	public Event getEventId() {
//		return eventId;
//	}
//	public void setEventId(Event eventId) {
//		this.eventId = eventId;
//	}
	
}
