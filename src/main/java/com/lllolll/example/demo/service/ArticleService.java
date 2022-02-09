package com.lllolll.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.vo.Article;

@Service
public class ArticleService {
	
	private int id;
	private List<Article> articles;
	
	public ArticleService() {
		articles = new ArrayList<>();
		id = 1;
		
		for (int num = 1; num < 11; num++) {
			Article article = new Article(num, "제목"+num, "내용"+num);
			articles.add(article);
		}
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {
		Article article = new Article(id, title, body);
		
		articles.add(article);
		
		return id++ + "번 게시글이 추가되었습니다.";
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {
		articles.remove(id-1);
		return id + "번 게시글이 삭제되었습니다.";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String modify(int id, String title, String body) {
		Article article = new Article(id, title, body);
		articles.set(id-1, article);
		return id + "번 게시글이 수정되었습니다.";
	}
}
