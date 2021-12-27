package com.mycompany.webapp.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.product.Depth1;
import com.mycompany.webapp.dto.product.ModifyForm;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.ProductModifyDto;
import com.mycompany.webapp.dto.product.ProductRegisterDto;
import com.mycompany.webapp.dto.product.SearchForm;
import com.mycompany.webapp.dto.product.Sizes;
import com.mycompany.webapp.vo.BrandVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	public List<Depth1> getDepth1() {
		WebClient webClient = WebClient.create();
		List<Depth1> d1nameList = webClient
				.get()
				.uri("http://kosa1.iptime.org:50520/product/search")
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
	
	public List<BrandVo> getBrandList() {
		WebClient webClient = WebClient.create();
		List<BrandVo> brandList = webClient
				.get()
				.uri("http://kosa1.iptime.org:50520/product/brand")
				.retrieve()
				.bodyToFlux(BrandVo.class)
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
				.uri("http://kosa1.iptime.org:50520/product/sizes")
				.retrieve()
				.bodyToFlux(Sizes.class)
				.collect(Collectors.toList())
				.share()
				.block();
		log.info("sizeList" + sizeList);
		return sizeList;
	}
	
	public ProductDto addProduct(ProductRegisterDto productInfo) {
		log.info("productInfo = " + productInfo);
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		if(productInfo.getPcommonid() != null) 				builder.part("pcommonid", productInfo.getPcommonid());
		if(productInfo.getScode() != null) 					builder.part("scode", productInfo.getScode());
		if(productInfo.getStock() != null) 					builder.part("stock", productInfo.getStock());
		if(productInfo.getImg1().getResource() != null) 	builder.part("img1", productInfo.getImg1().getResource());
		if(productInfo.getImg2().getResource() != null) 	builder.part("img2", productInfo.getImg2().getResource());
		if(productInfo.getImg3().getResource() != null) 	builder.part("img3", productInfo.getImg3().getResource());
		if(productInfo.getColorImg().getResource() != null) builder.part("colorImg", productInfo.getColorImg().getResource());
		if(productInfo.getCcode() != null) 					builder.part("ccode", productInfo.getCcode());
		if(productInfo.getPprice() != null) 				builder.part("pprice", productInfo.getPprice());
		if(productInfo.getPname() != null) 					builder.part("pname", productInfo.getPname());
		if(productInfo.getPnote() != null) 					builder.part("pnote", productInfo.getPnote());
		if(productInfo.getBname() != null) 					builder.part("bname", productInfo.getBname());
		if(productInfo.getD1name() != null) 				builder.part("d1name", productInfo.getD1name());
		if(productInfo.getD2name() != null) 				builder.part("d2name", productInfo.getD2name());
		if(productInfo.getD3name() != null) 				builder.part("d3name", productInfo.getD3name());
		if(productInfo.getWcolorid() != null) 				builder.part("wcolorid", productInfo.getWcolorid());
		WebClient webClient = WebClient.create();
		ProductDto result = webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/product/add")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(builder.build()))
				.retrieve()
				.bodyToMono(ProductDto.class)
				.block();
		
		return result;
	}
	
	public ProductDto modifyProduct(ProductModifyDto productInfo) {
		log.info("productInfo = " + productInfo);
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		if(productInfo.getPcommonid() != null) 				builder.part("pcommonid", productInfo.getPcommonid());
		if(productInfo.getPstockid() != null) 				builder.part("pstockid", productInfo.getPstockid());
		if(productInfo.getScode() != null) 					builder.part("scode", productInfo.getScode());
		if(productInfo.getStock() != null) 					builder.part("stock", productInfo.getStock());
															builder.part("regCheck", productInfo.isRegCheck());
		if(productInfo.getImg1().getResource() != null) 	builder.part("img1", productInfo.getImg1().getResource());
		if(productInfo.getImg2().getResource() != null) 	builder.part("img2", productInfo.getImg2().getResource());
		if(productInfo.getImg3().getResource() != null) 	builder.part("img3", productInfo.getImg3().getResource());
		if(productInfo.getColorImg().getResource() != null) builder.part("colorImg", productInfo.getColorImg().getResource());
		if(productInfo.getCcode() != null) 					builder.part("ccode", productInfo.getCcode());
		if(productInfo.getPprice() != null) 				builder.part("pprice", productInfo.getPprice());
		if(productInfo.getPname() != null) 					builder.part("pname", productInfo.getPname());
		if(productInfo.getPnote() != null) 					builder.part("pnote", productInfo.getPnote());
		if(productInfo.getBname() != null) 					builder.part("bname", productInfo.getBname());
		if(productInfo.getD1name() != null) 				builder.part("d1name", productInfo.getD1name());
		if(productInfo.getD2name() != null) 				builder.part("d2name", productInfo.getD2name());
		if(productInfo.getD3name() != null) 				builder.part("d3name", productInfo.getD3name());
		if(productInfo.getHiddenD1name() != null) 			builder.part("hiddenD1name", productInfo.getHiddenD1name());
		if(productInfo.getHiddenD2name() != null) 			builder.part("hiddenD2name", productInfo.getHiddenD2name());
		if(productInfo.getHiddenD3name() != null) 			builder.part("hiddenD3name", productInfo.getHiddenD3name());
		if(productInfo.getWcolorid() != null) 				builder.part("wcolorid", productInfo.getWcolorid());
		WebClient webClient = WebClient.create();
		ProductDto result = webClient
				.post()
				.uri("http://kosa1.iptime.org:50520/product/modify/process")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(builder.build()))
				.retrieve()
				.bodyToMono(ProductDto.class)
				.block();
		
		return result;
	}
	
	public ProductDto getOrgData(ModifyForm productInfo) {
		String URI = "http://kosa1.iptime.org:50520/product/modify";
		WebClient webClient = WebClient.create();
		ProductDto orgData = webClient
			.post()
			.uri(URI)
			.body(BodyInserters.fromValue(productInfo))
			.retrieve()
			.bodyToMono(ProductDto.class)
			.block();
		return orgData;
	}
}
