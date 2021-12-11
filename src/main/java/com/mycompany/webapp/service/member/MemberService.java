package com.mycompany.webapp.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.member.EventResult;
import com.mycompany.webapp.dto.member.Member;
import com.mycompany.webapp.dto.member.MemberResult;
import com.mycompany.webapp.dto.member.MemberSearchForm;

@Service
public class MemberService {
	public List<Member> selectAllMembers() {
		WebClient webClient = WebClient.create();
		List<Member> members = webClient
				.get()
				.uri("http://localhost:83/member")
				.retrieve()
				.bodyToFlux(Member.class)
				.collect(Collectors.toList())
				.share()
				.block();
		return members;
	}
	
   public MemberResult getSearchResult(MemberSearchForm searchForm) {
	      WebClient webClient = WebClient.create();
	      MemberResult memberResult = webClient
	            .post()
	            .uri("http://localhost:83/member/result")
	            .body(BodyInserters.fromValue(searchForm))
	            .retrieve()
	            .bodyToMono(MemberResult.class)
	            .block();
	      return memberResult;
   }
}
