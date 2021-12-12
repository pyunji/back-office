package com.mycompany.webapp.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.member.Member;

@Service
public class AccountService {
	public Member getAccountInfo(String mid ) {
		WebClient webClient = WebClient.create();
		Member member = webClient
				.post()
				.uri("http://localhost:83/account/accountcheck")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(mid)
				.retrieve()
				.bodyToMono(Member.class)
				.block();
		return member;
	}
}
