package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Application;
import com.example.demo.common.JSONResult;
import com.example.demo.configuration.ApiUrlProperties;
import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 描述：<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月8日下午4:44:39 <br>
 * E-mail: dolojia@gmail.com<br>
 */
@Controller
@ApiIgnore
public class HelloController {

	Logger logger = LogManager.getLogger(Application.class);

	@Autowired
	private CountryService countryService;

	@Autowired
	private ApiUrlProperties apiUrlProperties;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String gohome() {
		return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ApiOperation(value = "say hello")
	public String hello() throws Exception {
		System.out.println("[log4j2] this is system.out");
		logger.trace("[log4j2] this is start");
		logger.debug("[log4j2] this is debug");
		logger.info("[log4j2] this is info");
		logger.warn("[log4j2] this is warn");
		logger.error("[log4j2] this is error");
		logger.error("[log4j2] this is apiUrlProperties.getAge" + apiUrlProperties.getAge());
		throw new NullPointerException("故意异常");
//		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/getCountrys", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@ResponseBody
	public String getCountrys() {
		List<Country> enginess = countryService.findByName("Armenia");
		enginess.stream().forEach(str -> System.out.println(enginess.toString()));
		return JSONResult.fillResultString(JSONResult.STATUS_SUCCESS, "/getCountrys", 200, enginess);
	}

	@RequestMapping(value = "/helloadmin", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String helloAdmin() {
		return "helloAdmin";
	}

	@RequestMapping(value = "/hellouser", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public String helloUser() {
		return "helloUser";
	}

}