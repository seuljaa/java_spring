package com.lllolll.example.demo.service;

import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.MemberRepository;


@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public void join(String loginId, String loginPw, String name, String nickname, String cellphonNo, String email) {
		memberRepository.join(loginId, loginPw, name, nickname, cellphonNo, email);
	}
}
