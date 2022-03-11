package com.lllolll.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.lllolll.example.demo.vo.Rq;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq)req.getAttribute("rq");

		if (rq.isLogined() == false) {
			return ResultData.from("F-3", "로그인 후 이용해주세요.");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "타이틀을 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return ResultData.from("F-1", "바디를 입력해주세요.");
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId());

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		
		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		int postMemberId = article.getMemberId();

		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("로그인 후 이용해주세요.");
		}
		
		if (rq.getLoginedMemberId() != postMemberId) {
			return Ut.jsHistoryBack("본인이 작성한 게시글만 삭제할 수 있습니다.");
		}

		articleService.deleteArticle(id);

		return Ut.jsReplace(Ut.f("%d번 게시글을 삭제했습니다.", id), "../article/list");
	}

	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public ResultData modify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}

		int postMemberId = article.getMemberId();

		if (rq.isLogined() == false) {
			return ResultData.from("F-2", "로그인 후 이용해주세요.");
		}

		if (rq.getLoginedMemberId() != postMemberId) {
			return ResultData.from("F-3", "본인이 작성한 게시글만 수정할 수 있습니다.");
		}

		articleService.modifyArticle(id, title, body);
		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정했습니다.", id), "article", article);
	}

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model) {
		Rq rq = (Rq)req.getAttribute("rq");

		List articles = articleService.showArticles(rq.getLoginedMemberId());

		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);

		if (article == null) {

			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);
	}

}