package com.cisco.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping("/hello")
	private String sayHello() {
		return "index";
	}
	@RequestMapping("/")
	private String goHome() {
		return "angular_app";
	}

}
