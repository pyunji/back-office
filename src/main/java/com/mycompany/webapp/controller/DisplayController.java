package com.mycompany.webapp.controller;

import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.display.ShareByBrand;
import com.mycompany.webapp.dto.display.ShareByBrandList;
import com.mycompany.webapp.service.order.BrandService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/display")
@Slf4j
public class DisplayController {
	
	@Resource BrandService brandService;
	@RequestMapping("")
	public String content() {
		return "common/display";
	}
	
	@RequestMapping("/dashboard")
	public String displayList(Model model){
		log.info("displayList실행");
		ShareByBrandList shareByBrandList = new ShareByBrandList();
		
		//shareByBrandList = brandService.getShareByBrand();
		
		for(Entry<String,ShareByBrand> entrySet : shareByBrandList.getShareByBrand().entrySet()) {
			System.out.println(entrySet.getKey() + ":" + entrySet.getValue());
		}
		model.addAttribute("shareByBrandList", shareByBrandList.getShareByBrand());
		return "display/dashboard";
	}
}
