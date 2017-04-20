package com.samplebackend.user;

import java.util.List;

public interface UserDAO 
	{

	public void addUser(User user);
	public void updateUser(User user);
	public User getUserById(int userid);
	public User getUserByEmail(String email);
	public List<User> listUser();
	public List<User> getAllUserExceptMe(String email);
	}

