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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.samplebackend.Event.Event;

@Entity
@Table(name="Users")
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
private boolean enabled=true;
private String role="ROLE_USER";

public String getUsername() {
	return username;
}


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


public boolean isEnabled() {
	return enabled;
}


public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}



}
