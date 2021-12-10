package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource TestService testService;
	
	@PostMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		String oid = request.getParameter("orders_id");
		String mid = request.getParameter("user_id");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		//List<Orders> orders = testService.selectByOidShowOrders();
		//model.addAttribute("orders", orders);
		model.addAttribute("message","Controller에서 html로 데이터를 보내는 테스트입니다. message를 키값으로 사용합니다.");
		//파라미터를 HttpServletRequest로 받아옴
		log.info("orderid: " + oid);
		log.info("userid: " + mid);
		log.info("startDate: " + startDate);
		log.info("endDate: " + endDate);
		return "order/search";
	}
	
	@GetMapping("/test")
	public String test(@RequestParam("userId") String userId, Model model) {
		
		log.info(userId);
		List<Orders> orders = testService.showOrders();
		model.addAttribute("orders", orders);

		return "order/test";
	}
	
	
}
