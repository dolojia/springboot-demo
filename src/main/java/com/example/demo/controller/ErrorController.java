package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
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
