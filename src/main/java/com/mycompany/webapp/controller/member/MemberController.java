package com.mycompany.webapp.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.member.Grade;
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
	
	@RequestMapping("/list")
	public String memberList(Model model) {
//		List<Member> members = memberService.selectAllMembers();
//		model.addAttribute("members",members);
		return "member/memberList";
	}
	
	@RequestMapping("/grade")
	public String memberGrade(Model model) {
		List<Grade> grades = memberService.getGrades();
		model.addAttribute("grades",grades);
		
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
	
	@RequestMapping("/info")
	public String selectMember(Model model,@RequestParam String mid) {
		log.info("selectMember 실행");
		log.info(mid);
		
		Member member = memberService.selectMember(mid);
		model.addAttribute("member", member);
//		if(member.getBirth()!=null) {
//			Date date = member.getBirth();
//			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//			String stringDate = f.format(date);
//			log.info(stringDate);
//			model.addAttribute("mbirth",stringDate);
//		}else {
//			model.addAttribute("mbirth",null);
//		}
		
		return "member/updateForm";
	}
	
	@PostMapping("/update")
	public String updateMember(Member member) {
		log.info("updateMember 실행");
		log.info(member.toString());
		
		String result = memberService.updateMember(member);
		
		log.info(result);
		
		return "/member/memberList";
	}
}
