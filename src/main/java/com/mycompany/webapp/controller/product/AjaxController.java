package com.mycompany.webapp.controller.product;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.product.Depth2;
import com.mycompany.webapp.dto.product.Depth3;
import com.mycompany.webapp.dto.product.ProductDto;
import com.mycompany.webapp.dto.product.ProductResult;
import com.mycompany.webapp.dto.product.SearchForm;
import com.mycompany.webapp.service.product.AjaxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/product/ajax")
public class AjaxController {
	
	@Resource AjaxService ajaxService;
	
	@PostMapping("/setdepth2")
	public String setDepth2(Model model, String d1name) {
		log.info("d1name = " + d1name);
		List<Depth2> d2nameList = ajaxService.getDepth2(d1name);
		log.info("d2nameList = " + d2nameList);
		model.addAttribute("d2nameList", d2nameList);
		return "/product/depth2";
	}
	
	@PostMapping("/setdepth3")
	public String setDepth3(Model model, String d2name) {
		log.info("d2name = " + d2name);
		List<Depth3> d3nameList = ajaxService.getDepth3(d2name);
		log.info("d3nameList = " + d3nameList);
		model.addAttribute("d3nameList", d3nameList);
		return "/product/depth3";
	}
	
	@PostMapping("/result")
	public String result(Model model, SearchForm searchForm) {
		/* 앞단에서 pager를 설정하는 유일한 부분 */
		searchForm.setPager(new Pager(10, 5, 0, searchForm.getPageNo()));
		ProductResult productResult = ajaxService.getSearchResult(searchForm);
		List<ProductDto> productList = productResult.getProductList();
		model.addAttribute("productList", productList);

		model.addAttribute("pager", productResult.getPager());
		log.info("최종적으로 가져온 productList" + productList.toString());
		log.info("pager = "+ productResult.getPager());
		log.info("pager.getTotalRows() = "+ productResult.getPager().getTotalRows());
		return "/product/productListFragment";
	}
	
	@PostMapping("/bin/result")
	public String binResult(Model model, SearchForm searchForm) {
		/* 앞단에서 pager를 설정하는 유일한 부분 */
		searchForm.setPager(new Pager(10, 5, 0, searchForm.getPageNo()));
		ProductResult productResult = ajaxService.getBinResult(searchForm);
		List<ProductDto> productList = productResult.getProductList();
		model.addAttribute("productList", productList);
		
		model.addAttribute("pager", productResult.getPager());
		log.info("최종적으로 가져온 productList" + productList.toString());
		log.info("pager = "+ productResult.getPager());
		log.info("pager.getTotalRows() = "+ productResult.getPager().getTotalRows());
		return "/product/binFragment";
	}
	
	@PostMapping("/bin")
	public String goToBin(@RequestBody List<String> products) {
		log.info("toDeleteIds = " + products);
		ajaxService.goToBin(products);
		return "redirect:/product/bin";
	}
	
	@PostMapping("/returnfrombin")
	public String returnFromBin(@RequestBody List<String> products) {
		log.info("returnDeleteIds = " + products);
		ajaxService.returnFromBin(products);
		return "redirect:/product/bin";
	}
}
