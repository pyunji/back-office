package com.mycompany.webapp.dto.order;

import com.mycompany.webapp.dto.Pager;

import lombok.Data;

@Data
public class OrderSearchForm {
	String startRegDate;
	String endRegDate;
	String oid;
	String mid;
	int pageNo;
	Pager pager;
}
