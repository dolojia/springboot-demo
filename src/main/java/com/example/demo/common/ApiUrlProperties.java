package com.example.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义properties文件内容读取
 * @author 100196
 *
 */
@Component
@ConfigurationProperties(
ignoreUnknownFields = false, prefix  = "usetest")
@PropertySource("classpath:apiurl.properties")
public class ApiUrlProperties {
	private String name;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
