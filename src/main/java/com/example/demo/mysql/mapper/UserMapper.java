package com.example.demo.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	@Select("select id,name,login,password,role_id roleId from user where name = #{name}")
	User findByUsername(String name);

	@Select("select id,name,login,password,age,role_id roleId from user where id = #{id}")
	User getUserById(Long id);

	@Delete("delete FROM user where id = #{id}")
	int delUserById(Long id);

	@Update("update user set name=#{name},age=#{age} where id = #{id}")
	int updateUserById(User record);

	@Insert("INSERT INTO user(id,name,password,age,login,role_id) VALUES (#{id},#{name},#{password},#{age},#{login},#{roleId})")
	int insertUser(User record);

	@Select("select * from user")
	List<User> getUsers();

}