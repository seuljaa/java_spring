package com.lllolll.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lllolll.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public void writeArticle(@Param("title") String title, @Param("body") String body);
	
	public void deleteArticle(@Param("id") int id);
	
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
	
	public Article showArticle(@Param("id") int id);
	
	public List<Article> showArticles();
	
	public int getLastId();
}
