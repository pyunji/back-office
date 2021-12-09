package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.product.Depth1;

@Service
public class ProductService {
	public List<Depth1> getDepth1() {
		WebClient webClient = WebClient.create();
		List<Depth1> depth1 = webClient
				.get()
				.uri("http://localhost:83/product/search")
				.retrieve()
				.bodyToFlux(Depth1.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return depth1;
	}
}
