package com.mycompany.webapp.service.order;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.order.CancelOid;
import com.mycompany.webapp.dto.order.OrderProductResult;
import com.mycompany.webapp.dto.order.OrderResult;
import com.mycompany.webapp.dto.order.OrderSearchForm;

@Service
public class OrderAjaxService {

	public OrderResult getSearchResult(OrderSearchForm orderSearchForm) {
		WebClient webClient = WebClient.create();
		OrderResult orderResult = webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/search/result")
				.body(BodyInserters.fromValue(orderSearchForm))
				.retrieve()
				.bodyToMono(OrderResult.class)
//				.collectList()
//				.share()
				.block();
		return orderResult;
	}
	public OrderProductResult getSearchProductResult(OrderSearchForm orderSearchForm) {
		WebClient webClient = WebClient.create();
		OrderProductResult orderProductResult = webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/product/search/productResult")
				.body(BodyInserters.fromValue(orderSearchForm))
				.retrieve()
				.bodyToMono(OrderProductResult.class)
//				.collectList()
//				.share()
				.block();
		return orderProductResult;
	}
	public String cancelOrderResult(CancelOid cancelOid) {
		WebClient webClient = WebClient.create();
		String stateOrderCancel = webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/cancelOrder")
				.body(BodyInserters.fromValue(cancelOid))
				.retrieve()
				.bodyToMono(String.class)
			//	.collectList()
			//	.share()
				.block();
		return stateOrderCancel;
		
	}
}
