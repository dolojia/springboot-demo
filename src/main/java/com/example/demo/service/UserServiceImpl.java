package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userMapper.getUsers();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

	@Override
	public int delUserById(Long id) {
		// TODO Auto-generated method stub
		return userMapper.delUserById(id);
	}

	@Override
	public int updateUserById(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateUserById(record);
	}

	@Override
	public int insertUser(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(record);
	}

}
