package com.mycompany.webapp.controller.order;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.order.CancelOid;
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
	public String result(Model model, HttpSession session, @ModelAttribute OrderSearchForm orderSearchForm) {
		System.out.println(orderSearchForm.getOCsState0());

		orderSearchForm.setPager(new Pager(10, 5, 0, orderSearchForm.getPageNo()));
		//orderResult값을 가져옴
		OrderResult orderResult = orderAjaxService.getSearchResult(orderSearchForm);
		//order
		List<OrderDto> orderList = orderResult.getOrderList();
		model.addAttribute("orderList", orderList);
		model.addAttribute("pager",orderResult.getPager());
		System.out.println("최종적으로 가져온 OrderList" + orderList.toString());
		log.info("pager = "+ orderResult.getPager());
		log.info("pager.getTotalRows() = "+ orderResult.getPager().getTotalRows());
		return "order/orderListFragment";
		
	}
	
	@PostMapping("/cancelResult")
	public String cancelResult(Model model, HttpSession session,CancelOid cancelOid) {
		log.info(Arrays.toString(cancelOid.getCancelOid()));
		
		String orderCancelState = orderAjaxService.cancelOrderResult(cancelOid);
		log.info("주문 취소 여부 " + orderCancelState);
		return "order/orderListFragment";
	}
}
