package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.Engines;

@Mapper
public interface EnginesMapper {
	
    int insert(Engines record);

    int insertSelective(Engines record);
    
    @Select("select * from Engines where support = #{support}")
    List<Engines> findUserByName(String support);
}