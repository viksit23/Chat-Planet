package com.samplebackend.user;



import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.samplebackend.Event.Event;

@Entity

public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long userId;
private String username;
private String email;
private String password;
@Transient
private String cpassword;
private String address;
private String phone;

	//

/*@OneToMany(fetch=FetchType.LAZY,mappedBy="eventId")
private Set<Event> eventId;
*/
	//

@ManyToMany(fetch = FetchType.LAZY, mappedBy = "User")
public Set<Event> getEvent() {
	return this.getEvent();
}


public String getUsername() {
	return username;
}

/*public Set<Event> getEventId() {
	return eventId;
}

public void setEventId(Set<Event> eventId) {
	this.eventId = eventId;
}*/

//






public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCpassword() {
	return cpassword;
}
public void setCpassword(String cpassword) {
	this.cpassword = cpassword;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
}
