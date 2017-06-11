package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	public User findByUsername(String username);
	
	List<User> getUsers();
	
	User getUserById(Long id);

	int delUserById(Long id);

	int updateUserById(User record);

	int insertUser(User record);

}
