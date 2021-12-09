package com.mycompany.webapp.controller.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.product.Depth2;
import com.mycompany.webapp.dto.product.Depth3;
import com.mycompany.webapp.service.product.AjaxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/product/ajax")
public class AjaxController {
	
	@Resource AjaxService ajaxService;
	
	@PostMapping("/setdepth2")
	public String setDepth2(Model model, String depth1) {
		log.info("depth1 = " + depth1);
		List<Depth2> depth2List = ajaxService.getDepth2(depth1);
		log.info("depth2List = " + depth2List);
		model.addAttribute("depth2List", depth2List);
		return "/product/depth2";
	}
	
	@PostMapping("/setdepth3")
	public String setDepth3(Model model, String depth2) {
		log.info("depth2 = " + depth2);
		List<Depth3> depth3List = ajaxService.getDepth3(depth2);
		log.info("depth3List = " + depth3List);
		model.addAttribute("depth3List", depth3List);
		return "/product/depth3";
	}
}
