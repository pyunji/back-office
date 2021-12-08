package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {
	
	@Resource TestService testService;
	
	@RequestMapping("")
	public String test() {
		return "It's OK";
	}
	@RequestMapping("/orders")
	public String ordersTest(Model model) {
		List<Orders> orders = testService.showOrders();
		model.addAttribute("orders", orders);
		return "test/test";
	}
}
