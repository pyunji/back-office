package com.mycompany.webapp.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.member.Member;
import com.mycompany.webapp.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Resource MemberService memberService;
	
	@RequestMapping("")
	public String content() {
		return "common/member";
	}
	@RequestMapping("/list")
	public String memberList(Model model) {
		List<Member> members = memberService.selectAllMembers();
		model.addAttribute("members",members);
		return "member/memberList";
	}
	
	@RequestMapping("/grade")
	public String memberGrade() {
		return "member/grade";
	}
}
