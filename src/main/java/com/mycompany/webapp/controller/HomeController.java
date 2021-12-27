package com.mycompany.webapp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.display.ShareByBrand;
import com.mycompany.webapp.dto.display.ShareByBrandResult;
import com.mycompany.webapp.dto.display.StatResult;
import com.mycompany.webapp.service.order.BrandService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {
	@Resource BrandService brandService;
	@RequestMapping("")
	public String home(HttpSession session, Model model) throws ParseException {
		
//		String URL = "https://finance.naver.com/item/main.nhn?code=005930";
//		Document doc;
//
//		try {
//			doc = Jsoup.connect(URL).get();
//			Elements elem = doc.select(".date");
//			String[] str = elem.text().split(" ");
//
//			Elements todaylist =doc.select(".new_totalinfo dl>dd");
//			
//			String juga = todaylist.get(3).text().split(" ")[1];
//			String DungRakrate = todaylist.get(3).text().split(" ")[6];
//			String siga =  todaylist.get(5).text().split(" ")[1];
//			String goga = todaylist.get(6).text().split(" ")[1];
//			String zeoga = todaylist.get(8).text().split(" ")[1];
//			String georaeryang = todaylist.get(10).text().split(" ")[1];
//
//			String stype = todaylist.get(3).text().split(" ")[3]; //상한가,상승,보합,하한가,하락 구분
//
//			String vsyesterday = todaylist.get(3).text().split(" ")[4];
//			
//			System.out.println("삼성전자 주가------------------");
//			System.out.println("주가:"+juga);
//			System.out.println("등락률:"+DungRakrate);
//			System.out.println("시가:"+siga);
//			System.out.println("고가:"+goga);
//			System.out.println("저가:"+zeoga);
//			System.out.println("거래량:"+georaeryang);
//			System.out.println("타입:"+stype);
//			System.out.println("전일대비:"+vsyesterday);
//			System.out.println("가져오는 시간:"+str[0]+str[1]);
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		StatResult statByDay = brandService.getStatByDay();
		StatResult statByMonth = brandService.getStatByMonth();
		StatResult statByYear = brandService.getStatByYear();
		
		ShareByBrandResult shareByBrandResult = brandService.getShareByBrand();
		
		List<ShareByBrand> shareByBrandList = shareByBrandResult.getShareByBrandList(); 
		int totalPriceAll = 0;
		int totalCountAll = 0;
		List<ShareByBrand> topBrand = new ArrayList<>();
		
		for(int i = 0; i < shareByBrandList.size(); i++) {
			totalPriceAll += shareByBrandList.get(i).getTotalprice();
			totalCountAll += shareByBrandList.get(i).getTotalcount();
			
		if(topBrand.size() != 3) {
			topBrand.add(shareByBrandList.get(i));
		}else {
			for(int j = 0; j < 3; j++) {
				if(topBrand.get(j).getTotalprice() < shareByBrandList.get(i).getTotalprice()) {
					topBrand.set(j, shareByBrandList.get(i));
				}
			}
		}
			
		}
		
		
		
		model.addAttribute("shareByBrandList",shareByBrandList);
		model.addAttribute("totalPriceAll",totalPriceAll);
		model.addAttribute("totalCountAll",totalCountAll);
		model.addAttribute("statByDay", statByDay);
		model.addAttribute("statByMonth", statByMonth);
		model.addAttribute("statByYear", statByYear);
		model.addAttribute("topBrand",topBrand);
		return "index";
	}
}
