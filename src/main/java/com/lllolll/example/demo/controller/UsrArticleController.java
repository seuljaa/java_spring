package com.lllolll.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lllolll.example.demo.service.ArticleService;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Article;
import com.lllolll.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {
		articleService.writeArticle(title, body);
		int id = articleService.getLastId();
		return id + "번 게시글이 추가되었습니다.";
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {
		articleService.deleteArticle(id-1);
		return id + "번 게시글이 삭제되었습니다.";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String modify(int id, String title, String body) {
		articleService.modifyArticle(id, title, body);
		return id + "번 게시글이 수정되었습니다.";
	}
	
	@RequestMapping("/usr/article/showArticles")
	@ResponseBody
	public List showArticles() {
		return articleService.showArticles();
	}
	
	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(int id) {
		Article article = articleService.showArticle(id);
		
		if (article == null) {
			
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id));
	}
}