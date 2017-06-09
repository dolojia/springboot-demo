package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
// 1.通过 @EnableWebSecurity注解开启Spring Security的功能。
// 使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，可以开启security的注解，
// 我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	/**
	 * 方法名称：configure <br>
	 * 描述： Request层面的配置，对应XML Configuration中的<http>元素 <br>
	 * 作者：dolojia <br>
	 * 修改日期：2017年6月9日下午3:19:18
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf验证
		http.csrf().disable()
				// 对请求进行认证
				.authorizeRequests()
				// 过滤请求，过滤的所有请求 都放行
				.antMatchers("/", "/home", "/401", "/403", "/404", "/500").permitAll()
				// 所有请求需要身份认证
				.anyRequest().authenticated()
				// formLogin() 定义当需要用户登录时候，转到的登录页面
				.and().formLogin().loginPage("/login")
				// defaultSuccessUrl默认成功跳转页，如不设置默认为"/"
				.defaultSuccessUrl("/").permitAll()
				// 退出登录
				.and().logout().permitAll();
	}

	/**
	 * 方法名称：configure <br>
	 * 描述：身份验证配置，用于注入自定义身份验证Bean和密码校验规则 <br>
	 * 作者：dolojia <br>
	 * 修改日期：2017年6月9日下午3:18:54
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		{
			auth.userDetailsService(userDetailsService);
			// auth.userDetailsService(buildUserDetailsService());
			// auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
			// auth.inMemoryAuthentication().withUser("Dolo").password("admin").roles("ADMIN");
		}
	}

	/**
	 * 方法名称：configure <br>
	 * 描述： Web层面的配置，一般用来配置无需安全检查的路径 <br>
	 * 作者：dolojia <br>
	 * 修改日期：2017年6月9日下午3:19:35
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	}

}
