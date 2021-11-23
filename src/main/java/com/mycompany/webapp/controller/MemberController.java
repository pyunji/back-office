package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	@RequestMapping("")
	public String content() {
		return "common/member";
	}
	@RequestMapping("/list")
	public String memberList() {
		return "utilities-color";
	}
}
