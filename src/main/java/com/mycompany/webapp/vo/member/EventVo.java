package com.mycompany.webapp.vo.member;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.member.Event;

import lombok.Data;

@Data
public class EventVo {
	private Integer eno;
	private String etitle;
	private String econtent;
	private Date eissueDate;
	private Date eexpireDate;
	private Integer elimitCount;
	private Integer ecount;
	private String eimg;
	private Integer estatus;
	private String ethumbnail;
}
