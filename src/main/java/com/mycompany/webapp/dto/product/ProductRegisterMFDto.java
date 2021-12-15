package com.mycompany.webapp.dto.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductRegisterMFDto {
	MultipartFile img1;
	MultipartFile img2;
	MultipartFile img3;
	MultipartFile colorImg;
}
