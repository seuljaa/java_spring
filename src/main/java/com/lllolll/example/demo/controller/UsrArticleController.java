package com.lllolll.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	private int id;
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		id = 1;
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {
		Article article = new Article(id, title, body);
		
		articles.add(article);
		
		return id++ + "번 게시글이 추가되었습니다.";
	}
	
	@RequestMapping("/usr/article/showArticles")
	@ResponseBody
	public List showArticles() {
		
		return articles;
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Article{
	int num;
	String title;
	String body;
}
