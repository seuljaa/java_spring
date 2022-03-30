package com.lllolll.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private Rq rq;
	
	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(int boardId, String title, String body, String replaceUri) {
		if (rq.isLogined() == false) {
			return rq.jsHistoryBack("로그인 후 이용해주세요.");
		}

		if (Ut.empty(title)) {
			return rq.jsHistoryBack("제목을 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("내용을 입력해주세요.");
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body, rq.getLoginedMemberId(), boardId);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		
		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}

		return rq.jsReplace(Ut.f("%d번 게시글이 생성되었습니다.", id), replaceUri);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {
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
	public String showModify(Model model, int id) {
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
	public String modify(int id, String title, String body) {
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
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "title,body") String searchKeywordType, @RequestParam(defaultValue = "") String searchKeyword) {
		Board board = boardService.getBoardById(boardId);
		
		if ( board == null) {
			return rq.historyBackJsOnView(Ut.f("%d번 게시판은 존재하지 않습니다.", boardId));
		}
		
		int articlesCount = articleService.showArticlesCount(boardId, searchKeywordType, searchKeyword);
		
		int itemsCountInPage = 10;
		int pageCount = (int)Math.ceil((double)articlesCount / itemsCountInPage); 
		List articles = articleService.showArticles(rq.getLoginedMemberId(), boardId, page, itemsCountInPage, searchKeywordType, searchKeyword);

		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		model.addAttribute("board", board);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		
		articleService.increaseHitCount(id);
		
		Article article = articleService.showArticle(rq.getLoginedMemberId(), id);
		if (article == null) {

			return rq.historyBackJsOnView("게시글이 존재하지않습니다.");
		}

		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/showArticle")
	@ResponseBody
	public ResultData showArticle(int id) {
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