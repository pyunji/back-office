package com.mycompany.webapp.controller.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.member.Event;
import com.mycompany.webapp.dto.member.EventResult;
import com.mycompany.webapp.dto.member.EventSearchForm;
import com.mycompany.webapp.dto.member.NewEvent;
import com.mycompany.webapp.service.member.EventService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/event")
public class EventController {
	
	@Resource EventService eventService;
	private SimpleDateFormat formater;
	
	@GetMapping("/list")
	public String eventList(Model model) {
//		List<Event> events = eventService.selectAllEvent();
//		model.addAttribute("events",events);
		return "event/eventList";
	}
	
	@GetMapping("/eventEnroll")
	public String eventEnroll() {
		return "event/eventEnroll";
	}
	
	@PostMapping("/add")
	public String addEvent(NewEvent nevent) throws ParseException {
		log.info("addEvent 실행");
		
		Event event = new Event();
		event.setEtitle(nevent.getEtitle());
		event.setEcontent(nevent.getEcontent());
		formater = new SimpleDateFormat("yyyy-MM-dd");
		event.setEissueDate(formater.parse(nevent.getEissueDate()));
		event.setEexpireDate(formater.parse(nevent.getEexpireDate()));
		event.setElimitCount(nevent.getElimitCount());
		event.setEimg(nevent.getEimg());
		
		log.info(event.toString());
		String result = eventService.addEvent(event);
		log.info("result : "+ result);
		return "redirect:/event/eventEnroll";
	}
	
	@PostMapping("/result")
	public String result(Model model, EventSearchForm eventSearchForm) {
		log.info("result 실행");
		log.info(eventSearchForm.toString());
		eventSearchForm.setPager(new Pager(10,5,0,eventSearchForm.getPageNo()));
		EventResult eventResult = eventService.getSearchResult(eventSearchForm);
		model.addAttribute("eventList",eventResult.getEventList());
		model.addAttribute("pager",eventResult.getPager());
		
		log.info(model.toString());
		
		return "/event/eventListFragment";
	}
}
