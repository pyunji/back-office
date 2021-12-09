package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.product.Depth2;
import com.mycompany.webapp.dto.product.Depth3;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AjaxService {
	public List<Depth2> getDepth2(String depth1) {
		WebClient webClient = WebClient.create();
		List<Depth2> depth2List = webClient
				.post()
				.uri("http://localhost:83/product/setdepth2")
				.body(BodyInserters.fromFormData("depth1", depth1))
				.retrieve()
				.bodyToFlux(Depth2.class)
				.collect(Collectors.toList())
//				.collectList()
//				.share()
				.block();
		log.info("여기 확인 = " + depth2List);
		log.info("depth2List.get(0).getClass()"+depth2List.get(0).getClass());
		return depth2List;
	}
	
	public List<Depth3> getDepth3(String depth2) {
		WebClient webClient = WebClient.create();
		List<Depth3> depth3List = webClient
				.post()
				.uri("http://localhost:83/product/setdepth3")
				.body(BodyInserters.fromFormData("depth2", depth2))
				.retrieve()
				.bodyToFlux(Depth3.class)
				.collect(Collectors.toList())
//				.collectList()
//				.share()
				.block();
		log.info("여기 확인 = " + depth3List);
		log.info("depth3List.get(0).getClass()"+depth3List.get(0).getClass());
		return depth3List;
	}
}
