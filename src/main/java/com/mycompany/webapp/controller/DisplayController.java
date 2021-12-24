package com.mycompany.webapp.controller;

import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.display.ContextDto;
import com.mycompany.webapp.dto.display.ShareByBrand;
import com.mycompany.webapp.dto.display.ShareByBrandList;
import com.mycompany.webapp.service.member.MemberService;
import com.mycompany.webapp.service.order.BrandService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/display")
@Slf4j
public class DisplayController {

	@Resource
	BrandService brandService;

	@RequestMapping("")
	public String content() {
		return "common/display";
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		log.info("dashboard실행");
		ShareByBrandList shareByBrandList = new ShareByBrandList();

		// shareByBrandList = brandService.getShareByBrand();

		for (Entry<String, ShareByBrand> entrySet : shareByBrandList.getShareByBrand().entrySet()) {
			System.out.println(entrySet.getKey() + ":" + entrySet.getValue());
		}
		model.addAttribute("shareByBrandList", shareByBrandList.getShareByBrand());
		return "display/dashboard";
	}

	@RequestMapping("/displayList")
	public String displayList(Model model) {
		log.info("displayList 실행");

		return "display/displayList";
	}
	
	@RequestMapping("/dashBoardList")
	public String dashboardList(Model model) {
		log.info("dashBoardList 실행");

		return "display/dashBoardList";
	}


	@RequestMapping("/displayList2")
	public String displayList2(Model model) {
		log.info("displayList 실행");

		return "display/displayList2";
	}

	@Resource
	MemberService memberService;

	@PostMapping("/result")
	public String result(Model model, HttpSession session, @ModelAttribute ContextDto contextDto) {

		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "Hello there! How are you today?");
		System.out.println("test: " + contextDto.getContext0());
		System.out.println("test: " + contextDto.getIndex0());

		ContextDto contextResult = memberService.getContextResult(contextDto);
		model.addAttribute("contextResult", contextResult);
		return "display/displayList2";
	}

}
