package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

	@RequestMapping("/login") 
	public String login() {
		return "account/login";
	}
	
	@RequestMapping("/error/403")
	public String error403() {
		return "error/403";
	}
}
