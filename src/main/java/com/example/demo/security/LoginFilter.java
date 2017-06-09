//package com.example.demo.security;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Filter;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
///**
// * Created by jack on 2017/4/28.
// */
//@Order(1) // @Order注解表示执行过滤顺序，值越小，越先执行
//@WebFilter(filterName = "loginFilter") // 需要在spring-boot的入口处加注解@ServletComponentScan,
//										// 如果不指定，默认url-pattern是/*
//class LoginFilter extends Filter {
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
//
//	}
//
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	public void doFilter(HttpServletRequest request ,HttpServletResponse response ,FilterChain chain ) {
//			    HttpSession session = request.getSession();
//
//			    println("LoginFilter:" + JSON.toJSONString(principal, SerializerFeature.PrettyFormat))
//
//			    var username = ""
//			    if (principal.isInstanceOf[UserDetails]) {
//			      username = principal.asInstanceOf[UserDetails].getUsername
//			    }
//			    else {
//			      username = principal.toString;
//			    }
//			    session.setAttribute("username", username);
//
//			    chain.doFilter(request, response);
//			  }
//}
