package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.product.Brand;
import com.mycompany.webapp.dto.product.Depth1;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.ProductRegisterDto;
import com.mycompany.webapp.dto.product.SearchForm;
import com.mycompany.webapp.dto.product.Sizes;

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
	public List<Brand> getBrandList() {
		WebClient webClient = WebClient.create();
		List<Brand> brandList = webClient
				.get()
				.uri("http://localhost:83/product/brand")
				.retrieve()
				.bodyToFlux(Brand.class)
				.collect(Collectors.toList())
				.share()
				.block();
		log.info("brandList" + brandList);
		return brandList;
	}
	public List<Sizes> getSizeList() {
		WebClient webClient = WebClient.create();
		List<Sizes> sizeList = webClient
				.get()
				.uri("http://localhost:83/product/sizes")
				.retrieve()
				.bodyToFlux(Sizes.class)
				.collect(Collectors.toList())
				.share()
				.block();
		log.info("sizeList" + sizeList);
		return sizeList;
	}
	
//	public ProductDto addProduct(MultiValueMap<String, Object> productInfo) {
//		log.info("productInfo = " + productInfo);
//		WebClient webClient = WebClient.create();
//		 ProductDto product = webClient
//			.post()
//			.uri("http://localhost:83/product/add")
////			.body(BodyInserters.fromValue(productInfo))
//			.body(BodyInserters.fromMultipartData("productInfo", productInfo))
//			.retrieve()
//			.bodyToMono(ProductDto.class)
//			.block();
//		 
//		 return product;
//	}
//	
	public String addProduct(ProductRegisterDto productInfo) {
		log.info("productInfo = " + productInfo);
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("pcommonid", productInfo.getPcommonid());
		builder.part("productTest", productInfo.getImg1().getResource());
		WebClient webClient = WebClient.create();
		String result = webClient
				.post()
				.uri("http://localhost:83/product/add")
//			.body(BodyInserters.fromValue(productInfo))
//				.header("Content-Type", "application/json")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(builder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		return result;
	}
}
