package com.mycompany.webapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.Orders;
@Service
public class TestService {
	public List<Orders> showOrders() {
		WebClient webClient = WebClient.create();
		List<Orders> orders = webClient
				.get()
				.uri("http://localhost:83/test/orders")
				.retrieve()
				.bodyToFlux(Orders.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return orders;
	}
//	MultiValueMap<String,String> formData = 
//	public List<Orders> selectByOidShowOrders() {
//		WebClient webClient = WebClient.create();
//		List<Orders> orders = webClient
//			
//				.get()
//				.uri("http://localhost:83/test/orders") //요청 URL
//				.retrieve()
//				.bodyToFlux(Orders.class)
//				.collect(Collectors.toList())
//				.share()
//				.block();
//		return orders;
//	}
	
}
