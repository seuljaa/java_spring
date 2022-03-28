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
import com.lllolll.example.demo.service.BoardService;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Article;
import com.lllolll.example.demo.vo.Board;
import com.lllolll.example.demo.vo.ResultData;
import com.lllolll.example.demo.vo.Rq;

@Controller
public class UsrArticleController {

	private ArticleService articleService;
	private BoardService boardService;
	
	public UsrArticleController(ArticleService articleService, BoardService boardService) {
		this.articleService = articleService;
		this.boardService = boardService;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(HttpServletRequest req, String title, String body, String replaceUri) {
		Rq rq = (Rq)req.getAttribute("rq");

		if (rq.isLogined() == false) {
			return rq.jsHistoryBack("로그인 후 이용해주세요.");
		}

		if (Ut.empty(title)) {
			return rq.jsHistoryBack("제목을 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("내용을 입력해주세요.");
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId());

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		
		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}

		return rq.jsReplace(Ut.f("%d번 게시글이 생성되었습니다.", id), replaceUri);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		
		if (article == null) {
			return rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}

		int postMemberId = article.getMemberId();

		if (rq.isLogined() == false) {
			return rq.jsHistoryBack("로그인 후 이용해주세요.");
		}
		
		if (rq.getLoginedMemberId() != postMemberId) {
			return rq.jsHistoryBack("본인이 작성한 게시글만 삭제할 수 있습니다.");
		}

		articleService.deleteArticle(id);

		return rq.jsReplace(Ut.f("%d번 게시글을 삭제했습니다.", id), "../article/list");
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		
		if (article == null) {
			return rq.historyBackJsOnView(Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		if(actorCanModifyRd.isFail()) {
			return rq.historyBackJsOnView(actorCanModifyRd.getMsg());
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}

	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String modify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq)req.getAttribute("rq");

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBack(Ut.f("%d번 게시글이 존재하지 않습니다.", id));
		}

		int postMemberId = article.getMemberId();

		if (rq.isLogined() == false) {
			return rq.jsHistoryBack("로그인 후 이용해주세요.");
		}

		if (rq.getLoginedMemberId() != postMemberId) {
			return rq.jsHistoryBack("본인이 작성한 게시글만 수정할 수 있습니다.");
		}

		articleService.modifyArticle(id, title, body);
		return rq.jsReplace(Ut.f("%d번 게시글을 수정했습니다.", id), Ut.f("../article/detail?id=%d", id));
	}

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, int boardId) {
		Board board = boardService.getBoardById(boardId);
		
		Rq rq = (Rq)req.getAttribute("rq");

		List articles = articleService.showArticles(rq.getLoginedMemberId());

		model.addAttribute("board", board);
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
	
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, Model model) {
		return "usr/article/write";
	}

}