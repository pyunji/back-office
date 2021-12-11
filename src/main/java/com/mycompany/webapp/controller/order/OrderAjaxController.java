package com.mycompany.webapp.controller.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.order.OrderDto;
import com.mycompany.webapp.dto.order.OrderResult;
import com.mycompany.webapp.dto.order.OrderSearchForm;
import com.mycompany.webapp.service.order.OrderAjaxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/order/ajax")
public class OrderAjaxController {
	
	@Resource
	OrderAjaxService orderAjaxService;
	
	@PostMapping("/result")
	public String result(Model model, HttpSession session, OrderSearchForm orderSearchForm) {
		log.info(orderSearchForm.getEndRegDate());
		log.info(orderSearchForm.getOid());
		log.info(orderSearchForm.getMid());
		log.info(orderSearchForm.getStartRegDate());
		
		orderSearchForm.setPager(new Pager(10, 5, 0, orderSearchForm.getPageNo()));
		OrderResult orderResult = orderAjaxService.getSearchResult(orderSearchForm);
		List<OrderDto> orderList = orderResult.getOrderList();
		model.addAttribute("orderList", orderList);
		System.out.println("최종적으로 가져온 OrderList" + orderList.toString());
		log.info("pager = "+ orderResult.getPager());
		log.info("pager.getTotalRows() = "+ orderResult.getPager().getTotalRows());
		return "order/orderListFragment";
		
	}
}
