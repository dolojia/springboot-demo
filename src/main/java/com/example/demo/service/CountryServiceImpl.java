package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.oracle.mapper.CountryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryMapper countryMapper;

	@Override
	public int insert(Country record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Country record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Country> findByName(String support) {
		// TODO Auto-generated method stub
		return countryMapper.findByName(support);
	}

	@Override
	public PageInfo<Country> queryByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		// sql前插入分页数据
		PageHelper.startPage(pageNo, pageSize);
		List<Country> list = countryMapper.findByPage();
		// 用PageInfo对结果进行包装
		PageInfo<Country> page = new PageInfo<Country>(list);
		// 测试PageInfo全部属性
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getStartRow());
		System.out.println(page.getEndRow());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(page.getFirstPage());
		System.out.println(page.getLastPage());
		System.out.println(page.isHasPreviousPage());
		System.out.println(page.isHasNextPage());
		return page;
	}

}
