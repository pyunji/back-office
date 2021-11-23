package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductList;

@Service
public class ProductService {
	@Resource private ProductDao productDao;
	
	public List<ProductList> getProductList(Pager pager){
		return productDao.getProductList(pager);
	}
	
	public List<ProductList> getProductSampleList(){
		return productDao.getProductSampleList();
	}
	
	public Integer getTotalCount() {
		return productDao.countAll();
	}
}
