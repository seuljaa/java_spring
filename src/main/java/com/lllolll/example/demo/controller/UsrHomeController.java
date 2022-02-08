package com.lllolll.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	private int count;
	
	public UsrHomeController() {
		count = 0;
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요.";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑습니다.";
	}
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "또 만나요.";
	}
	
	@RequestMapping("/usr/home/count")
	@ResponseBody
	public int count() {
		
		return count++;
	}
	
	@RequestMapping("/usr/home/count/back")
	@ResponseBody
	public String count_back() {
		count = 0;
		return "값이 0으로 초기화되었습니다.";
	}
	
	@RequestMapping("usr/home/count/modify")
	@ResponseBody
	public String count_modify(int i) {
		count = i;
		return "값이" + i + "(으)로 수정되었습니다.";
	}
}


