package com.mycompany.webapp.service.order;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.order.OrderResult;
import com.mycompany.webapp.dto.order.OrderSearchForm;

@Service
public class OrderAjaxService {

	public OrderResult getSearchResult(OrderSearchForm orderSearchForm) {
		WebClient webClient = WebClient.create();
		OrderResult orderResult = webClient
				.post()
				.uri("http://localhost:83/order/search/result")
				.body(BodyInserters.fromValue(orderSearchForm))
				.retrieve()
				.bodyToMono(OrderResult.class)
//				.collectList()
//				.share()
				.block();
		return orderResult;
	}
}
