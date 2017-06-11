package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 描述：错误页 面扭转<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月11日上午10:34:13 <br>
 * E-mail: dolojia@gmail.com<br>
 */
@Controller
@ApiIgnore
public class ErrorController {

	@RequestMapping(value = "/401", method = RequestMethod.GET)
	public String error401() {
		return "401";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error403() {
		return "403";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404() {
		return "404";
	}

	@RequestMapping(value = "500", method = RequestMethod.GET)
	public String error500() {
		return "500";
	}

}
