package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事情報のリポジトリクラス.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class ArticleRepository {
	
	private final static RowMapper<Article>ARTICLE_ROW_MAPPER=(rs,i)->{
		Article article=new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 記事の全件検索
	 * 
	 * @return　全件検索結果
	 */
	public List<Article>findAll(){
		String Sql
		="SELECT id,name,content FROM articles ORDER BY id desc";
		
		List<Article>articleList=template.query(Sql,ARTICLE_ROW_MAPPER );
		return articleList;
		
	}
	
	/**
	 * 記事を投稿するメソッド
	 * 
	 * @param article　投稿したい記事
	 */
	public void insert(Article article) {
		String Sql
		="INSERT  INTO articles(name,content) VALUES(:name,:content) ";
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(article);
		
		template.update(Sql, param);
	}
	
	/**
	 * 記事を削除する
	 * 
	 * @param id　ID
	 */
	public void deleteById(int id) {
		String Sql
		="DELETE FROM articles WHERE id=:id";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("id", id);
		
		template.update(Sql, param);
	}
}
