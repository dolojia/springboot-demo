package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Country;
import com.example.demo.entity.User;
import com.example.demo.mysql.mapper.UserMapper;
import com.example.demo.oracle.mapper.CountryMapper;
import com.example.demo.service.CountryService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperTest.class)
@ComponentScan
public class MapperTest {

	@Autowired
	private CountryMapper countryMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired(required = true)
	private CountryService countryService;

	/**
	 * 数据库连接测试
	 */
	@Test
	public void testEngines() {
		List<Country> countrys = countryMapper.findByName("Armenia");
		System.out.println(countrys.toString());
	}

	/**
	 * 分页测试
	 */
	@Test
	public void testPageInfo() {
		PageInfo<Country> page = countryService.queryByPage(1, 20);
		System.out.println(page);
	}

	@Test
	public void testGetUser() {
		User user = userMapper.getUserById(1L);
		System.out.println(user);
	}

}
