package com.mycompany.webapp.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.member.Event;
import com.mycompany.webapp.dto.member.EventResult;
import com.mycompany.webapp.dto.member.EventSearchForm;
import com.mycompany.webapp.dto.member.NewEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventService {
	public List<Event> selectAllEvent() {
		WebClient webClient = WebClient.create();
		List<Event> events = webClient
				.get()
				.uri("http://kosa1.iptime.org:50520/event")
				.retrieve()
				.bodyToFlux(Event.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return events;
	}
	
//	public String addEvent(Event event) {
//		WebClient webClient = WebClient.create();
//		String result = webClient.post()
//				 .uri("http://kosa1.iptime.org:50520/event")
//				 .body(BodyInserters.fromValue(event))
//				 .retrieve()
//				 .bodyToMono(String.class)
//				 .share()
//				 .block();
//		return result;
//	}
	
	public String addEvent(NewEvent event) {
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		if(event.getEno()!=null) builder.part("eno", event.getEno());
		if(event.getEtitle()!=null) builder.part("etitle", event.getEtitle());
		if(event.getEcontent()!=null) builder.part("econtent", event.getEcontent());
		if(event.getEissueDate()!=null) builder.part("eissueDate", event.getEissueDate());
		if(event.getEexpireDate()!=null) builder.part("eexpireDate", event.getEexpireDate());
		if(event.getElimitCount()!=null) builder.part("elimitCount", event.getElimitCount());
		if(event.getEcount()!=null) builder.part("ecount", event.getEcount());
		if(event.getEimg().getResource()!=null) builder.part("eimg", event.getEimg().getResource());
		if(event.getEstatus()!=null) builder.part("estatus", event.getEstatus());
		if(event.getEthumbnail().getResource()!=null) builder.part("ethumbnail", event.getEthumbnail().getResource());
		log.info("webClient addEvent실행");
		WebClient webClient = WebClient.create();
		String result = webClient.post()
				 .uri("http://kosa1.iptime.org:50520/event")
				 .contentType(MediaType.MULTIPART_FORM_DATA)
				 .body(BodyInserters.fromMultipartData(builder.build()))
				 .retrieve()
				 .bodyToMono(String.class)
				 .block();
		return result;
	}
	
   public EventResult getSearchResult(EventSearchForm searchForm) {
      WebClient webClient = WebClient.create();
      log.info(searchForm.toString());
      EventResult eventResult = webClient
            .post()
            .uri("http://kosa1.iptime.org:50520/event/result")
            .body(BodyInserters.fromValue(searchForm))
            .retrieve()
            .bodyToMono(EventResult.class)
            .block();
      return eventResult;
   }
}
