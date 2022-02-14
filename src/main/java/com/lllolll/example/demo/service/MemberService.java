package com.lllolll.example.demo.service;

import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.MemberRepository;
import com.lllolll.example.demo.vo.Member;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		// ID 중복체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) {
			return -1;
		}
		
		// 닉네임+이메일 중복체크
		oldMember = getMemberByNicknameAndEmail(nickname, email);

		if (oldMember != null) {
			return -2;
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastId();
	}

	private Member getMemberByNicknameAndEmail(String nickname, String email) {
		return memberRepository.getMemberByNicknameAndEmail(nickname, email);
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
