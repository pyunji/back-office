package com.mycompany.webapp.dto.member;

import java.util.List;

import com.mycompany.webapp.dto.Pager;

import lombok.Data;

@Data
public class EventResult {
	List<Event> eventList;
	Pager pager;
}
