package com.lllolll.example.demo.service;

import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.MemberRepository;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Member;
import com.lllolll.example.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		// ID 중복체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는(은) 이미 사용중입니다.", loginId));
		}
		
		// 닉네임+이메일 중복체크
		oldMember = getMemberByNicknameAndEmail(nickname, email);

		if (oldMember != null) {
			return ResultData.from("F-8", Ut.f("해당 nickname(%s)과(와) email(%s)(은)는 이미 사용중입니다.", nickname, email));
		}
		
		int id = memberRepository.getLastId(); 
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.", "id", id); 
	}

	private Member getMemberByNicknameAndEmail(String nickname, String email) {
		return memberRepository.getMemberByNicknameAndEmail(nickname, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
