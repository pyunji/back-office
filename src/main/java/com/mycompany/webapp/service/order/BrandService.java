package com.mycompany.webapp.service.order;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.display.ShareByBrandList;
import com.mycompany.webapp.dto.display.ShareByBrandResult;

@Service
public class BrandService {

	public ShareByBrandResult getShareByBrand() {
		WebClient webClient = WebClient.create();
		ShareByBrandResult shareByBrandResult =  webClient
				.post()
				.uri("http://localhost:83/order/getShareByBrand")
				.retrieve()
				.bodyToMono(ShareByBrandResult.class)
//				.collectList()
//				.share()
				.block();
		return shareByBrandResult;
	}

}
