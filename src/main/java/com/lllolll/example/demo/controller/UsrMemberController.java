package com.lllolll.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.service.MemberService;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Member;
import com.lllolll.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1","loginId(을)를 입력해주세요.");
		}
		
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2","loginPw(을)를 입력해주세요.");
		}
		
		if (Ut.empty(name)) {
			return ResultData.from("F-3","name(을)를 입력해주세요.");
		}
		
		if (Ut.empty(nickname)) {
			return ResultData.from("F-4","nickname(을)를 입력해주세요.");
		}
		
		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5","cellphoneNo(을)를 입력해주세요.");
		}
		
		if (Ut.empty(email)) {
			return ResultData.from("F-6","email(을)를 입력해주세요.");
		}
		
		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if ( joinRd.isFail() ) {
			return joinRd;			
		}
		
		Member member = memberService.getMemberById((int)joinRd.getData1());
		
		return ResultData.newData(joinRd, member);
	}
	
	@RequestMapping("/usr/member/login")
	@ResponseBody
	public ResultData logIn(HttpSession httpSession, String loginId, String loginPw) {
		
		boolean islogined = false;
		
		if ( httpSession.getAttribute("loginedMemberId") != null) {
			islogined = true;
		}
		
		if( islogined == true ) {
			return ResultData.from("F-5", "이미 로그인되어있는 아이디 입니다.");
		}
		
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1","loginId(을)를 입력해주세요.");
		}
		
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2","loginPw(을)를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId); 
		
		if ( member ==  null) {
			return ResultData.from("F-3", "존재하지 않는 회원입니다.");
		}
		
		if ( member.getLoginPw().equals(loginPw) == false ) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		
		httpSession.setAttribute("loginedMemberId", member.getLoginId());
		
		return ResultData.from("S-1", Ut.f("%s님 환영합니다.", member.getName())); 
	}
	
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public ResultData logout(HttpSession httpSession, String loginId, String loginPw) {
		
		boolean islogined = true;
		
		if ( httpSession.getAttribute("loginedMemberId") == null) {
			islogined = false;
		}
		
		if( islogined == false ) {
			return ResultData.from("F-5", "로그인 되어있지 않은 ID입니다.");
		}
		
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1","loginId(을)를 입력해주세요.");
		}
		
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2","loginPw(을)를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId); 
		
		if ( member ==  null) {
			return ResultData.from("F-3", "존재하지 않는 회원입니다.");
		}
		
		if ( member.getLoginPw().equals(loginPw) == false ) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		
		httpSession.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", Ut.f("%s님 로그아웃 되었습니다..", member.getName())); 
	}
}


