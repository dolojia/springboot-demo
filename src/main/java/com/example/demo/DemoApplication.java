package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class DemoApplication {

	Logger logger = LogManager.getLogger(DemoApplication.class);

	@Autowired
	private EnginesMapper enginesMapper;

	@RequestMapping("/")
	public String home() {
		System.out.println("[log4j2] this is system.out");
		logger.trace("[log4j2] this is start");
		logger.debug("[log4j2] this is debug");
		logger.info("[log4j2] this is info");
		logger.warn("[log4j2] this is warn");
		logger.error("[log4j2] this is error");
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/enginess")
	public Object enginess() {
		Map<String, Object> map = new HashMap<>();
		List<Engines> enginess = enginesMapper.findUserByName("YES");
		map.put("data", enginess);
		map.put("status", "success");
		map.put("code", 0);
		return map;
	}
}
