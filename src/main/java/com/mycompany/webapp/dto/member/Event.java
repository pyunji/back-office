package com.mycompany.webapp.dto.member;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Event {
	private Integer eno;
	private String etitle;
	private String econtent;
	private Date eissueDate;
	private Date eexpireDate;
	private Integer elimitCount;
	private Integer ecount;
	private MultipartFile eimg;
	private Integer estatus;
	private MultipartFile ethumbnail;
}
