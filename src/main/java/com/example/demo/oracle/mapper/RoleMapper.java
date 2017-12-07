package com.example.demo.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Role;

@Mapper
public interface RoleMapper {
	
	int insert(Role record);

	int getRoles(Role record);

	@Select("select * from role where id in (#{id})")
	List<Role> getRolesByIds(Long id);
}