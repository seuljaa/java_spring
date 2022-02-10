package com.lllolll.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.vo.Article;

@Component
public class ArticleRepository {
	private int articlesLastId;
	private List<Article> articles;
	
	public ArticleRepository() {
		articles = new ArrayList<>();
		articlesLastId = 0;
		
	}
	
	public void makeTestData() {
		for (int num = 1; num < 11; num++) {
			String title = "제목"+num;
			String body = "내용"+num;
			writeArticle(title, body);
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
	
	public Article showArticle(int id) {
		Article article = new Article();
		article = articles.get(id-1);
		return article;
	}
	
	public List showArticles() {
		return articles;
	}
}
