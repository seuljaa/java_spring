package com.lllolll.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.service.MemberService;
import com.lllolll.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public Object join(String loginId, String loginPw, String name, String nickname, String cellphonNo, String email) {
		int id = memberService.join(loginId, loginPw, name, nickname, cellphonNo, email); 
		
		if ( id == -1 ) {
			return "해당 로그인아이디는 이미 사용중입니다.";
		}
		
		Member member = memberService.getMemberById(id);
		return member;
	}
	
}


