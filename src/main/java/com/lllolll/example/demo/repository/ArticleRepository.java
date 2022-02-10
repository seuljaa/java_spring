package com.lllolll.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public Article writeArticle(String title, String body);
	
	public void deleteArticle(int id);
	
	public Article modifyArticle(int id, String title, String body);
	
	@Select("select * from article where id= #{id}")
	public Article showArticle(@Param("id") int id);
	
	public List showArticles();
}
