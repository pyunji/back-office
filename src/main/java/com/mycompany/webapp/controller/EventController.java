package com.mycompany.webapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Event;
import com.mycompany.webapp.dto.NewEvent;
import com.mycompany.webapp.service.EventService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/event")
public class EventController {
	
	@Resource EventService eventService;
	private SimpleDateFormat formater;
	
	@GetMapping("/list")
	public String eventList(Model model) {
		List<Event> events = eventService.selectAllEvent();
		model.addAttribute("events",events);
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
}
