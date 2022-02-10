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
	
	@Insert("INSERT INTO article SET title= #{title}, `body`= #{body}, updateDate = NOW(), regDate = NOW()")
	public void writeArticle(@Param("title") String title, @Param("body") String body);
	
	@Delete("DELETE FROM article WHERE id= #{id}")
	public void deleteArticle(@Param("id") int id);
	
	@Update("UPDATE article SET title= #{title}, `body`= #{body}, updateDate = NOW() WHERE id = #{id}")
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
	
	@Select("SELECT * FROM article WHERE id= #{id}")
	public Article showArticle(@Param("id") int id);
	
	@Select("SELECT * FROM article ORDER BY title DESC")
	public List<Article> showArticles();
	
	@Select("SELECT COUNT(*) FROM article")
	public int getLastId();
}
