package com.mycompany.webapp.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.product.Brand;
import com.mycompany.webapp.dto.product.Depth1;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.ProductRegisterDto;
import com.mycompany.webapp.dto.product.SearchForm;
import com.mycompany.webapp.dto.product.Sizes;
import com.mycompany.webapp.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

	@Autowired ProductService productService;
	@RequestMapping("")
	public String content() {
		return "common/product";
	}
	
	
	@RequestMapping("/list")
	public String getProductList(/*@RequestParam(defaultValue = "1") int pageNo, */Model model, HttpSession session) {
		List<Depth1> d1nameList = productService.getDepth1();
		model.addAttribute("d1nameList", d1nameList);
		
		return "product/productList";
	}
	
	@GetMapping("/add")
	public String showAddView(Model model) {
		List<Depth1> d1nameList = productService.getDepth1();
		model.addAttribute("d1nameList", d1nameList);
		List<Brand> brandList = productService.getBrandList();
		model.addAttribute("brandList", brandList);
		List<Sizes> sizeList = productService.getSizeList();
		model.addAttribute("sizeList", sizeList);
		return "product/add";
	}
	
	@PostMapping("/add")
	public String register(@ModelAttribute ProductRegisterDto productInfo, HttpServletRequest request) throws IllegalStateException, IOException {
		log.info("productInfo ="+ productInfo);

		// 파일 파트 내용 읽기
//		log.info("file originalname: " + productInfo.getImg1().getOriginalFilename());
//		log.info("file contenttype: " + productInfo.getImg1().getContentType());
//		log.info("file size: " + productInfo.getImg1().getSize());
		
		String resultProduct = productService.addProduct(productInfo);
		log.info("result product = " + resultProduct);
		
//		String rootPath = request.getSession().getServletContext().getRealPath("/");  
//		String rootPath = "C:\\hyundai_itne\\eclipse-workspace\\back-office\\src\\main\\resources\\static";  
//		String attachPath = "/upload/";
		/* 파일이 없으면 뒤에 .jpg가 안붙는 오류 발생. */
//		String saveName1 = new Date().getTime() + "-" + productInfo.getImg1().getOriginalFilename();
//		String saveName2 = new Date().getTime() + "-" + productInfo.getImg2().getOriginalFilename();
//		String saveName3 = new Date().getTime() + "-" + productInfo.getImg3().getOriginalFilename();
//		File file1 = new File(rootPath + attachPath + saveName1);
//		File file2 = new File(rootPath + attachPath + saveName2);
//		File file3 = new File(rootPath + attachPath + saveName3);
//		productInfo.getImg1().transferTo(file1);
//		productInfo.getImg2().transferTo(file2);
//		productInfo.getImg3().transferTo(file3);
		
//		file.
		/*
		if (productInfo.getImg1().getOriginalFilename().equals("")) {
			log.info("It is empty String");
			productInfo.setImg1(null); // 1. null로 설정 후
		}
		
		log.info("img1 = " + productInfo.getImg1().getOriginalFilename());
		log.info("img2 = " + productInfo.getImg2().getOriginalFilename()); // 2. NullPointer Exception
		*/
//		log.info(""+productInfo);
		return "redirect:/product/add";
	}
//	@RequestMapping("/create")
//	public String create() {
//		
//	}
	
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
