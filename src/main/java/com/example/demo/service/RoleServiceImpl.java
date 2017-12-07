package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.oracle.mapper.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRoles(Long id) {
		// TODO Auto-generated method stub
		return roleMapper.getRolesByIds(id);
	}

}
