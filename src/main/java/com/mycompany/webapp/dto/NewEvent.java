package com.mycompany.webapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NewEvent {
	private String etitle;
	private String econtent;
	private String eissueDate;
	private String eexpireDate;
	private int elimitCount;
	private String eimg;
}
