package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.product.Depth1;
import com.mycompany.webapp.dto.product.SearchForm;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	public List<Depth1> getDepth1() {
		WebClient webClient = WebClient.create();
		List<Depth1> d1nameList = webClient
				.get()
				.uri("http://localhost:83/product/search")
				.retrieve()
				.bodyToFlux(Depth1.class)
				.collect(Collectors.toList())
				.share()
				.block();
		log.info("d1nameList" + d1nameList);
		return d1nameList;
	}
	
	public void getSearchResult(SearchForm searchForm) {
		if (searchForm.getPstockid() != "") {
			
		}
	}
}
