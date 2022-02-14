package com.lllolll.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.ArticleRepository;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Article;
import com.lllolll.example.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository=articleRepository;
	}
	
	public ResultData writeArticle(String title, String body) {
		
		articleRepository.writeArticle(title, body);
		
		int id = articleRepository.getLastId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), id);
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
	
	public int getLastId() {
		return articleRepository.getLastId();
	}

}
