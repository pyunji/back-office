package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired ProductService productService;
	@RequestMapping("")
	public String content() {
		return "common/product";
	}
	
	
	@RequestMapping("/list")
	public String getProductList(/*@RequestParam(defaultValue = "1") int pageNo, */Model model) {

		// 페이징 처리를 위해 조건에 맞는 상품전체 개수 조회
//		int totalProduct = productService.getTotalCount();
		
		// Pager객체에 parameter값으로 (표시할 상품수, 표시할 페이지 그룹수, 전체 상품개수, 페이지번호)를 넣는다.
//		Pager pager = new Pager(12,5,totalProduct, pageNo);
//		List<ProductList> products = productService.getProductList(pager); 
		List<ProductList> products = productService.getProductSampleList();
//		model.addAttribute("pager",pager);
		model.addAttribute("products", products);
		return "product/productList";
	}
	
	@RequestMapping("/add")
	public String addProduct() {
		return "product/add";
	}
//	@RequestMapping("/create")
//	public String create() {
//		
//	}
}
