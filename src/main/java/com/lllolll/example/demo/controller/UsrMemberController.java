package com.lllolll.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.service.MemberService;

@Controller
public class UsrMemberController {
	
	private int count;
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public String join(String loginId, String loginPw, String name, String nickname, String cellphonNo, String email) {
		memberService.join(loginId, loginPw, name, nickname, cellphonNo, email);
		return "성공";
	}
	
}


