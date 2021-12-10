package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.product.Depth2;
import com.mycompany.webapp.dto.product.Depth3;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.SearchForm;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AjaxService {
	public List<Depth2> getDepth2(String d1name) {
		WebClient webClient = WebClient.create();
		List<Depth2> d2nameList = webClient
				.post()
				.uri("http://localhost:83/product/setdepth2")
				.body(BodyInserters.fromFormData("d1name", d1name))
				.retrieve()
				.bodyToFlux(Depth2.class)
				.collect(Collectors.toList())
//				.collectList()
//				.share()
				.block();
		log.info("여기 확인 = " + d2nameList);
		log.info("depth2List.get(0).getClass()"+d2nameList.get(0).getClass());
		return d2nameList;
	}
	
	public List<Depth3> getDepth3(String d2name) {
		WebClient webClient = WebClient.create();
		List<Depth3> d3nameList = webClient
				.post()
				.uri("http://localhost:83/product/setdepth3")
				.body(BodyInserters.fromFormData("d2name", d2name))
				.retrieve()
				.bodyToFlux(Depth3.class)
				.collect(Collectors.toList())
//				.collectList()
//				.share()
				.block();
		log.info("여기 확인 = " + d3nameList);
		log.info("depth3List.get(0).getClass()"+d3nameList.get(0).getClass());
		return d3nameList;
	}
	
	public List<ProductDto> getSearchResult(SearchForm searchForm) {
		WebClient webClient = WebClient.create();
		List<ProductDto> productList = webClient
				.post()
				.uri("http://localhost:83/product/search/result")
				.body(BodyInserters.fromValue(searchForm))
				.retrieve()
				.bodyToFlux(ProductDto.class)
				.collect(Collectors.toList())
//				.collectList()
//				.share()
				.block();
		return productList;
	}
}
