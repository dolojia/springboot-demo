package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Country;
import com.github.pagehelper.PageInfo;

public interface CountryService {

	int insert(Country record);

	int insertSelective(Country record);

	List<Country> findByName(String support);

	PageInfo<Country> queryByPage(Integer pageNo, Integer pageSize);

}
