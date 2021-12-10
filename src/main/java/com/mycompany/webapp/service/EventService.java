package com.mycompany.webapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.Event;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventService {
	public List<Event> selectAllEvent() {
		WebClient webClient = WebClient.create();
		List<Event> events = webClient
				.get()
				.uri("http://localhost:83/event")
				.retrieve()
				.bodyToFlux(Event.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return events;
	}
	
	public String addEvent(Event event) {
		WebClient webClient = WebClient.create();
		String result = webClient.post()
				 .uri("http://localhost:83/event")
				 .body(BodyInserters.fromValue(event))
				 .retrieve()
				 .bodyToMono(String.class)
				 .share()
				 .block();
		log.info("WebClient");
		return result;
	}
//	   public List<Depth3> getSearchResult(SearchForm searchForm) {
//		      WebClient webClient = WebClient.create();
//		      List<Depth3> d3nameList = webClient
//		            .post()
//		            .uri("http://localhost:83/product/search/result")
//		            .body(BodyInserters.fromValue(searchForm))
//		            .retrieve()
//		            .bodyToFlux(Depth3.class)
//		            .collect(Collectors.toList())
////		            .collectList()
////		            .share()
//		            .block();
//		      return d3nameList;
//		   }
}
