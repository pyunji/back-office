package com.mycompany.webapp.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
	
	@RequestMapping("/list")
	public String getOrderList(Model model, HttpSession session) {
		
		Pager pager = new Pager();
			
		
		return "order/orderList";
	}
	
	@RequestMapping("/dashboard")
	public String getDashboard() {
		return "order/dashboard";
	}
	
}
