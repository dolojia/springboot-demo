package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 描述：spring-boot启动主方法<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月11日上午10:32:27 <br>
 * E-mail: dolojia@gmail.com<br>
 * (exclude = { DataSourceAutoConfiguration.class }) 屏蔽原数据源
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 方法名称：configure <br>
	 * 描述：部署至Tomcat需要 修改启动类， 继承 SpringBootServletInitializer 并重写 configure 方法
	 * 
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

}
