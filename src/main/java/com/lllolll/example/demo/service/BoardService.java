package com.lllolll.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.repository.ArticleRepository;
import com.lllolll.example.demo.repository.BoardRepository;
import com.lllolll.example.demo.util.Ut;
import com.lllolll.example.demo.vo.Article;
import com.lllolll.example.demo.vo.Board;
import com.lllolll.example.demo.vo.ResultData;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}
