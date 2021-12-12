package com.mycompany.webapp.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.member.Member;
import com.mycompany.webapp.dto.member.MemberResult;
import com.mycompany.webapp.dto.member.MemberSearchForm;
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
//		List<Member> members = memberService.selectAllMembers();
//		model.addAttribute("members",members);
		return "member/memberList";
	}
	
	@RequestMapping("/grade")
	public String memberGrade() {
		return "member/grade";
	}
	
	@PostMapping("/result")
	public String result(Model model, MemberSearchForm searchForm) {
		log.info("result 실행");
		log.info(searchForm.toString());
		searchForm.setPager(new Pager(10, 5, 0, searchForm.getPageNo()));
		MemberResult memberResult = memberService.getSearchResult(searchForm);
		model.addAttribute("memberList",memberResult.getMemberList());
		model.addAttribute("pager",memberResult.getPager());
		
		log.info(model.toString());
		log.info(memberResult.getPager().toString());
		
		return "/member/memberListFragment";
	}
}
