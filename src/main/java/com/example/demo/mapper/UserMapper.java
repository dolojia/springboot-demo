package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	int insert(User record);

	int insertSelective(User record);

	@Select("select id,name,login,password,role_id roleId from user where name = #{name}")
	User findByUsername(String name);

}