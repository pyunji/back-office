package com.mycompany.webapp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Event {
	private int eno;
	private String etitle;
	private String econtent;
	private Date eissueDate;
	private Date eexpireDate;
	private int elimitCount;
	private int ecount;
	private String eimg;
	private int estatus;
}
