package com.mycompany.webapp.controller.product;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.product.Brand;
import com.mycompany.webapp.dto.product.Depth1;
import com.mycompany.webapp.dto.product.Depth2;
import com.mycompany.webapp.dto.product.Depth3;
import com.mycompany.webapp.dto.product.ModifyForm;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.ProductModifyDto;
import com.mycompany.webapp.dto.product.ProductRegisterDto;
import com.mycompany.webapp.dto.product.SearchForm;
import com.mycompany.webapp.dto.product.Sizes;
import com.mycompany.webapp.service.product.AjaxService;
import com.mycompany.webapp.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

	@Autowired ProductService productService;
	
	@Resource AjaxService ajaxService;
	
	@RequestMapping("")
	public String content() {
		return "common/product";
	}
	
	@RequestMapping("/list")
	public String getProductList(Model model) {
		List<Depth1> d1nameList = productService.getDepth1();
		model.addAttribute("d1nameList", d1nameList);
		
		return "product/productList";
	}
	
	@GetMapping("/add")
	public String showAddView(Model model) {
		/* 초기 select 태그에 들어갈 값 세팅 시작 */
		List<Depth1> d1nameList = productService.getDepth1();
		model.addAttribute("d1nameList", d1nameList);
		List<Brand> brandList = productService.getBrandList();
		model.addAttribute("brandList", brandList);
		List<Sizes> sizeList = productService.getSizeList();
		model.addAttribute("sizeList", sizeList);
		/* 초기 select 태그에 들어갈 값 세팅 끝 */
		
		return "product/add";
	}
	
	@PostMapping("/add")
	public String register(
			@ModelAttribute ProductRegisterDto productInfo, 
			HttpServletRequest request
			) throws IllegalStateException, IOException {
		log.info("productInfo ="+ productInfo);

		ProductDto resultProduct = productService.addProduct(productInfo);
		log.info("result product = " + resultProduct);
		
		return "redirect:/product/add";
	}
	
	@PostMapping("/modify")
	public String showModifyView(
			Model model, 
			@ModelAttribute ModifyForm modifyForm
			) {
		ProductDto orgData = productService.getOrgData(modifyForm);
		model.addAttribute("orgData", orgData);
		log.info("orgData = " + orgData);
		/* 초기 select 태그에 들어갈 값 세팅 시작 */
		List<Depth1> d1nameList = productService.getDepth1();
		model.addAttribute("d1nameList", d1nameList);
		List<Depth2> d2nameList = ajaxService.getDepth2(orgData.getD1name());
		model.addAttribute("d2nameList", d2nameList);
		List<Depth3> d3nameList = ajaxService.getDepth3(orgData.getD2name());
		model.addAttribute("d3nameList", d3nameList);
		
		List<Brand> brandList = productService.getBrandList();
		model.addAttribute("brandList", brandList);
		List<Sizes> sizeList = productService.getSizeList();
		model.addAttribute("sizeList", sizeList);
		/* 초기 select 태그에 들어갈 값 세팅 끝 */
		
		return "product/modify";
	}
	
	@PostMapping("/modify/process")
	public String modify(@ModelAttribute ProductModifyDto modifyProduct) {
		log.info("modifyProduct = " + modifyProduct);
		productService.modifyProduct(modifyProduct);
		return "redirect:/product/list";
	}
	
	@RequestMapping("/category")
	public String updateCategory() {
		return "product/category";
	}
	
	@PostMapping("/list/result")
	public String listResult(@ModelAttribute SearchForm searchForm) {
		log.info("searchForm = " + searchForm);
		if (searchForm.getPstockid() != "") {
			
		}
		return "product/productListResult";
	}
}
