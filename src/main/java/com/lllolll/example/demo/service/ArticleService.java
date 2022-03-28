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
	
	public ResultData writeArticle(String title, String body, int memberId) {
		
		articleRepository.writeArticle(title, body, memberId);
		
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), "id", id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
	
	public ResultData modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = showArticle(0, id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "article", article);
	}

	public List showArticles(int actorId, int boardId) {
		List<Article> articles =  articleRepository.showArticles(boardId);
		
		for ( Article article : articles ) {
			updateForPrintData(actorId, article);
		}
		
		return articles;
	}

	public Article showArticle(int actorId, int id) {
		Article article = articleRepository.showArticle(id);
		
		updateForPrintData(actorId, article);
		return article;
	}
	
	private void updateForPrintData(int actorId, Article article) {
		if ( article == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());

		ResultData actorCanModifyRd = actorCanModify(actorId, article);
		article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
	}

	public int getLastId() {
		return articleRepository.getLastInsertId();
	}
	
	public ResultData actorCanModify(int actorId, Article article) {
		if( article == null ) {
			return ResultData.from("F1", "게시물이 존재하지 않습니다.");
		}
		
		if( article.getMemberId() != actorId ) {
			return ResultData.from("F2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "게시물 수정이 가능합니다.");
	}
	
	public ResultData actorCanDelete(int actorId, Article article) {
		if( article == null ) {
			return ResultData.from("F1", "게시물이 존재하지 않습니다.");
		}
		
		if( article.getMemberId() != actorId ) {
			return ResultData.from("F2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}

}
