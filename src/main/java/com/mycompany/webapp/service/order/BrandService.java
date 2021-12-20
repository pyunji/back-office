package com.mycompany.webapp.service.order;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.display.ShareByBrand;
import com.mycompany.webapp.dto.display.ShareByBrandList;

@Service
public class BrandService {

	public ShareByBrandList getShareByBrand() {
		WebClient webClient = WebClient.create();
		ShareByBrandList shareByBrandList =  webClient
				.post()
				.uri("http://localhost:83/order/getShareByBrand")
				.retrieve()
				.bodyToMono(ShareByBrandList.class)
//				.collectList()
//				.share()
				.block();
		return shareByBrandList;
	}

}
