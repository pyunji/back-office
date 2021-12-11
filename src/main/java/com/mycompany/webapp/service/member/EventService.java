package com.mycompany.webapp.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.member.Event;
import com.mycompany.webapp.dto.member.EventResult;
import com.mycompany.webapp.dto.member.EventSearchForm;
import com.mycompany.webapp.dto.product.Depth3;

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
		return result;
	}
	
   public EventResult getSearchResult(EventSearchForm searchForm) {
      WebClient webClient = WebClient.create();
      log.info(searchForm.toString());
      EventResult eventResult = webClient
            .post()
            .uri("http://localhost:83/event/result")
            .body(BodyInserters.fromValue(searchForm))
            .retrieve()
            .bodyToMono(EventResult.class)
            .block();
      return eventResult;
   }
}
