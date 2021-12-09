package com.mycompany.webapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.webapp.dto.Member;

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
}
