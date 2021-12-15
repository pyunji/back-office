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
import com.mycompany.webapp.dto.product.ProductRegisterMPDto;
import com.mycompany.webapp.dto.product.ProductRegisterNormDto;
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
	public ProductDto addProduct(ProductRegisterDto productInfo) {
		log.info("productInfo = " + productInfo);
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		if(productInfo.getPcommonid() != null) builder.part("pcommonid", productInfo.getPcommonid());
//		builder.part("pcolorid", productInfo.getPcolorid());
//		builder.part("pstockid", productInfo.getPstockid());
		if(productInfo.getScode() != null) builder.part("scode", productInfo.getScode());
		if(productInfo.getStock() != null) builder.part("stock", productInfo.getStock());
//		builder.part("regDate", productInfo.getRegDate());
		if(productInfo.getImg1().getResource() != null) builder.part("img1", productInfo.getImg1().getResource());
		if(productInfo.getImg2().getResource() != null) builder.part("img2", productInfo.getImg2().getResource());
		if(productInfo.getImg3().getResource() != null) builder.part("img3", productInfo.getImg3().getResource());
		if(productInfo.getColorImg().getResource() != null) builder.part("colorImg", productInfo.getColorImg().getResource());
//		builder.part("colorImg", productInfo.getColorImg().getResource());
		if(productInfo.getCcode() != null) 	builder.part("ccode", productInfo.getCcode());
		if(productInfo.getPprice() != null) 	builder.part("pprice", productInfo.getPprice());
		if(productInfo.getPname() != null) 	builder.part("pname", productInfo.getPname());
		if(productInfo.getPnote() != null) 	builder.part("pnote", productInfo.getPnote());
		if(productInfo.getBname() != null) 	builder.part("bname", productInfo.getBname());
		if(productInfo.getD1name() != null) 	builder.part("d1name", productInfo.getD1name());
		if(productInfo.getD2name() != null) 	builder.part("d2name", productInfo.getD2name());
		if(productInfo.getD3name() != null) 	builder.part("d3name", productInfo.getD3name());
		if(productInfo.getWcolorid() != null) 	builder.part("wcolorid", productInfo.getWcolorid());
//		builder.part("wcolorid", productInfo.getWcolorid());
		WebClient webClient = WebClient.create();
		ProductDto result = webClient
				.post()
				.uri("http://localhost:83/product/add")
//			.body(BodyInserters.fromValue(productInfo))
//				.header("Content-Type", "application/json")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(builder.build()))
				.retrieve()
				.bodyToMono(ProductDto.class)
				.block();
		
		return result;
	}
//	public String addProduct(ProductRegisterNormDto productInfo, ProductRegisterMPDto files) {
//		log.info("productInfo = " + productInfo);
//		MultipartBodyBuilder builder = new MultipartBodyBuilder();
//		builder.part("productInfo", productInfo);
//		if (files.getImg1() != null) builder.part("img1", files.getImg1());
//		if (files.getImg2() != null) builder.part("img2", files.getImg2());
//		if (files.getImg3() != null) builder.part("img3", files.getImg3());
//		if (files.getColorImg() != null) builder.part("colorImg", files.getColorImg());
////		builder.part("files", files);
//		WebClient webClient = WebClient.create();
//		String result = webClient
//				.post()
//				.uri("http://localhost:83/product/add")
////			.body(BodyInserters.fromValue(productInfo))
////				.header("Content-Type", "application/json")
//				.contentType(MediaType.MULTIPART_FORM_DATA)
//				.body(BodyInserters.fromMultipartData(builder.build()))
//				.retrieve()
//				.bodyToMono(String.class)
//				.block();
//		
//		return result;
//	}
}
