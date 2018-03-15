//package com.example.demo.security;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
//@Configuration
//public class ErrorPageConfig {
//
//	@Bean
//	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//		return new MyCustomizer();
//	}
//
//	private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
//
//		/**
//		 * 方法名称：customize <br>
//		 * 描述： 根据错误状态跳转相应错误页<br>
//		 * 作者：dolojia <br>
//		 * 修改日期：2017年6月9日上午10:16:40
//		 *
//		 * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
//		 * @param container
//		 */
//		// 利用了Spring自带的EmbeddedServletContainerCustomizer进行设置。当Spring发现有类型为EmbeddedServletContainerCustomizer注册进来，便会调用EmbeddedServletContainerCustomizer的customize方法
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
//			ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
//			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
//			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
//			container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
//		}
//
//	}
//
//}