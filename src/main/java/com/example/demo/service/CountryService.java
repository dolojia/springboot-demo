package com.example.demo.service;

import java.util.List;

import com.example.demo.persistence.Country;
import com.github.pagehelper.PageInfo;

public interface CountryService {

	int insert(Country record);

	int insertSelective(Country record);

	List<Country> findByName(String support);

	PageInfo<Country> queryByPage(String support, Integer pageNo, Integer pageSize);

}
