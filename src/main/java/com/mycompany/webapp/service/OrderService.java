package com.mycompany.webapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.Orders;

public class OrderService {
	public List<Orders> showOrders() {
		WebClient webClient = WebClient.create();
		List<Orders> orders = webClient
				.get()
				.uri("http://localhost:83/orde/orders")
				.retrieve()
				.bodyToFlux(Orders.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return orders;
	}
}
