package com.lllolll.example.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	public Rq(HttpServletRequest req) {

		HttpSession httpSession = req.getSession();
		boolean islogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			islogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		this.isLogined = islogined;
		this.loginedMemberId = loginedMemberId;
	}

}
