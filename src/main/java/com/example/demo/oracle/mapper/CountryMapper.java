package com.example.demo.oracle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Country;
import com.github.pagehelper.PageInfo;

@Mapper
public interface CountryMapper {
	
    int insert(Country record);

    int insertSelective(Country record);
    
    @Select("select * from country where countryname = #{support}")
    List<Country> findByName(String support);
    
    @Select("select * from country")
    List<Country> findByPage();
    
    PageInfo<Country> queryByPage(Integer pageNo,Integer pageSize);
}