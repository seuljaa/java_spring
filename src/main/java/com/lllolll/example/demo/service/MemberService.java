package com.lllolll.example.demo.service;

import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.MemberRepository;


@Service
public class MemberService {
	
	//private MemberRepository memberRepository;
	
	public void join(String loginId, String loginPw, String name, String nickname, Integer cellphonNo, String email) {
		//memberRepository.join(loginId, loginPw, name, nickname, cellphonNo, email);
	}
}
