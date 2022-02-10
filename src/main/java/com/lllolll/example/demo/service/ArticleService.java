package com.lllolll.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.ArticleRepository;
import com.lllolll.example.demo.vo.Article;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository=articleRepository;
		articleRepository.makeTestData();
	}
	
	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}

	public void deleteArticle(int i) {
		articleRepository.deleteArticle(i);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
	}

	public List showArticles() {
		return articleRepository.showArticles();
	}

	public Article showArticle(int id) {
		return articleRepository.showArticle(id);
	}
}
