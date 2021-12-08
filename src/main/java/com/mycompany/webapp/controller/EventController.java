package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/event")
public class EventController {
	@RequestMapping("/eventList")
	public String content() {
		return "event/eventList";
	}
	
	@RequestMapping("/eventEnroll")
	public String eventEnroll() {
		return "event/eventEnroll";
	}
}
