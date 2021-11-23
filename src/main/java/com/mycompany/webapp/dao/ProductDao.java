package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductList;

@Mapper
public interface ProductDao {
	List<ProductList> getProductList(Pager pager);
	
	List<ProductList> getProductSampleList();
	
	int countAll();
}
