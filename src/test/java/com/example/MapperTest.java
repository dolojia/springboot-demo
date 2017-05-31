package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.mapper.CountryMapper;
import com.example.demo.persistence.Country;
import com.example.demo.service.CountryService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperTest.class)
@ComponentScan
public class MapperTest {

	@Autowired
	private CountryMapper countryMapper;

	@Autowired(required = true)
	private CountryService countryService;

	@Test
	public void testEngines() {
		List<Country> countrys = countryMapper.findByName("Armenia");
		System.out.println(countrys.toString());
	}

	@Test
	public void testPageInfo() {
		PageInfo<Country> page = countryService.queryByPage("Armenia", 1, 20);
		System.out.println(page);
	}

}
