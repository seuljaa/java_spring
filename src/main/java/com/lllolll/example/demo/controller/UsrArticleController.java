package com.lllolll.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.service.ArticleService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		articlesLastId = 0;
		
		for (int num = 1; num < 11; num++) {
			Article article = new Article(num, "제목"+num, "내용"+num);
			articles.add(article);
		}
	}
	
	public Article writeArticle(String title, String body) {
		int lastId = articles.size() + 1;
		Article article = new Article(lastId, title, body);
		articles.add(article);
		articlesLastId = lastId;
		return article;
	}
	
	public void deleteArticle(int id) {
		articles.remove(id);
	}
	
	public Article modifyArticle(int id, String title, String body) {
		Article article = new Article(id, title, body);
		articles.set(id-1, article);
		return article;
	}
	
	
	
	
	
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {
		writeArticle(title, body);
		return articlesLastId + "번 게시글이 추가되었습니다.";
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {
		deleteArticle(id-1);
		return id + "번 게시글이 삭제되었습니다.";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String modify(int id, String title, String body) {
		modifyArticle(id, title, body);
		return id + "번 게시글이 수정되었습니다.";
	}
	
	@RequestMapping("/usr/article/showArticles")
	@ResponseBody
	public List showArticles() {
		return articles;
	}
	
	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public Article showArticle(int id) {
		Article article = new Article();
		article = articles.get(id-1);
		return article;
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
