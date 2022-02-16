package com.lllolll.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ResultData doAdd(HttpSession httpSession, String title, String body) {

		boolean islogined = false;

		int loginMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			islogined = true;
			loginMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (islogined == false) {
			return ResultData.from("F-3", "로그인 후 이용해주세요.");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "타이틀을 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return ResultData.from("F-1", "바디를 입력해주세요.");
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body, loginMemberId);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.showArticle(id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData delete(HttpSession httpSession, int id) {

		Article article = articleService.showArticle(id);

		boolean islogined = false;

		int loginMemberId = 0;
		int postMemberId = article.getMemberId();
		
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			islogined = true;
			loginMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (islogined == false) {
			return ResultData.from("F-2", "로그인 후 이용해주세요.");
		}
		
		if (loginMemberId != postMemberId) {
			return ResultData.from("F-3", "본인이 작성한 게시글만 삭제할 수 있습니다.");
		}

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제했습니다.", id), "id", id);
	}

	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public ResultData modify(HttpSession httpSession, int id, String title, String body) {
		
		Article article = articleService.showArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}
		
		boolean islogined = false;

		int loginMemberId = 0;
		int postMemberId = article.getMemberId();
		
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			islogined = true;
			loginMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if (islogined == false) {
			return ResultData.from("F-2", "로그인 후 이용해주세요.");
		}
		
		if (loginMemberId != postMemberId) {
			return ResultData.from("F-3", "본인이 작성한 게시글만 수정할 수 있습니다.");
		}

		

		articleService.modifyArticle(id, title, body);
		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정했습니다.", id), "article", article);
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List articles = articleService.showArticles();
		
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(int id) {
		Article article = articleService.showArticle(id);

		if (article == null) {

			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);
	}
}