package com.samplebackend.Event;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.samplebackend.user.User;
@Entity
public class Event {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long eventId;
	private String title;
	
	@Type(type="text")
	private String description;	
	
	private Date postdate;
	
	private boolean posted=true;
	
	private String eventFrom;
	private String eventTo;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User u;
	
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}*/
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "Event_User", joinColumns = {
			@JoinColumn(name = "eventId", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "userId",
					nullable = false, updatable = false) })
	public Set<User> getuserId() {
		return this.getuserId();
		}
	
	
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public boolean isPosted() {
		return posted;
	}
	public void setPosted(boolean posted) {
		this.posted = posted;
	}
	public String getEventFrom() {
		return eventFrom;
	}
	public void setEventFrom(String eventFrom) {
		this.eventFrom = eventFrom;
	}
	public String getEventTo() {
		return eventTo;
	}
	public void setEventTo(String eventTo) {
		this.eventTo = eventTo;
	}
	
	
	
	
	
}
