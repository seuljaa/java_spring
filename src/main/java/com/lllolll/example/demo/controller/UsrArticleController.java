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
	public ResultData doAdd(String title, String body) {
		
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "타이틀을 입력해주세요.");
		}
		
		if (Ut.empty(body)) {
			return ResultData.from("F-1", "바디를 입력해주세요.");
		}
		
		ResultData writeArticleRd = articleService.writeArticle(title, body);
		
		int id = (int)writeArticleRd.getData1();
		
		Article article = articleService.showArticle(id);
		
		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData delete(int id) {
		Article article = articleService.showArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제했습니다.", id), id);
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public ResultData modify(int id, String title, String body) {
		Article article = articleService.showArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정했습니다.", id), id);
	}
	
	@RequestMapping("/usr/article/showArticles")
	@ResponseBody
	public ResultData showArticles() {
		List articles = articleService.showArticles();
		
		return ResultData.from("S-1", "게시물 리스트 입니다.", articles);
	}
	
	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(int id) {
		Article article = articleService.showArticle(id);
		
		if (article == null) {
			
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}
}