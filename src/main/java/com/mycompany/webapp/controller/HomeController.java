package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {
	@RequestMapping("")
	public String home(HttpSession session) {
//		HttpSession session = request.getSession();
		String testData = "test";
		session.setAttribute("test", testData);
		String data = (String) session.getAttribute("test");
		log.info("data = " + data);
		return "index";
	}
}
