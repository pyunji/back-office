package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.display.ShareByBrand;
import com.mycompany.webapp.dto.display.ShareByBrandResult;
import com.mycompany.webapp.dto.display.StatResult;
import com.mycompany.webapp.service.order.BrandService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {
	@Resource BrandService brandService;
	@RequestMapping("")
	public String home(HttpSession session, Model model) {
		
		
		
		StatResult statByDay = brandService.getStatByDay();
		StatResult statByMonth = brandService.getStatByMonth();
		StatResult statByYear = brandService.getStatByYear();
		
		ShareByBrandResult shareByBrandResult = brandService.getShareByBrand();
		
		List<ShareByBrand> shareByBrandList = shareByBrandResult.getShareByBrandList(); 
		int totalPriceAll = 0;
		int totalCountAll = 0;
		for(int i = 0; i < shareByBrandList.size(); i++) {
			totalPriceAll += shareByBrandList.get(i).getTotalprice();
			totalCountAll += shareByBrandList.get(i).getTotalcount();
		}
		System.out.println(totalPriceAll);
		
		
		model.addAttribute("shareByBrandList",shareByBrandList);
		model.addAttribute("totalPriceAll",totalPriceAll);
		model.addAttribute("totalCountAll",totalCountAll);
		model.addAttribute("statByDay", statByDay);
		model.addAttribute("statByMonth", statByMonth);
		model.addAttribute("statByYear", statByYear);
		
		return "index";
	}
}
