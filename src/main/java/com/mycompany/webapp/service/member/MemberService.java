package com.mycompany.webapp.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.member.Member;
import com.mycompany.webapp.dto.member.MemberResult;
import com.mycompany.webapp.dto.member.MemberSearchForm;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	public List<Member> selectAllMembers() {
		WebClient webClient = WebClient.create();
		List<Member> members = webClient
				.get()
				.uri("http://localhost:83/member/list")
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
   
   public Member selectMember(String mid) {
	   log.info("WebClient" + mid);
		WebClient webClient = WebClient.create();
		Member member = webClient
		    .get()
		    .uri("http://localhost:83/member/{mid}",mid)
		    .retrieve()
		    .bodyToMono(Member.class)
		    .block();
		return member;
   }
   
   public String updateMember(Member member) {
	   WebClient webClient = WebClient.create();
	   String result = webClient
            .post()
            .uri("http://localhost:83/member/update")
            .body(BodyInserters.fromValue(member))
			.retrieve()
			.bodyToMono(String.class)
			.share()
			.block();
      return result;
   }
}
