package com.mycompany.webapp.service.order;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.display.ShareByBrandResult;
import com.mycompany.webapp.dto.display.StatResult;

@Service
public class BrandService {

	public ShareByBrandResult getShareByBrand() {
		WebClient webClient = WebClient.create();
		ShareByBrandResult shareByBrandResult =  webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/getShareByBrand")
				.retrieve()
				.bodyToMono(ShareByBrandResult.class)
//				.collectList()
//				.share()
				.block();
		return shareByBrandResult;
	}
	public StatResult getStatByDay() {
		WebClient webClient = WebClient.create();
		StatResult statResult =  webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/getDataByDay")
				.retrieve()
				.bodyToMono(StatResult.class)
//				.collectList()
//				.share()
				.block();
		return statResult;
	}
	public StatResult getStatByMonth() {
		WebClient webClient = WebClient.create();
		StatResult statResult =  webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/getDataByMonth")
				.retrieve()
				.bodyToMono(StatResult.class)
//				.collectList()
//				.share()
				.block();
		return statResult;
	}
	public StatResult getStatByYear() {
		WebClient webClient = WebClient.create();
		StatResult statResult =  webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/order/getDataByYear")
				.retrieve()
				.bodyToMono(StatResult.class)
//				.collectList()
//				.share()
				.block();
		return statResult;
	}

}
