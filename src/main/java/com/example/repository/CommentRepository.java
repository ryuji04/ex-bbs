package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント情報のリポジトリクラス
 * 
 * @author adachiryuji
 *
 */
@Repository
public class CommentRepository {
	private static final RowMapper<Comment>COMMENT_ROW_MAPPER=(rs,i)->{
		Comment comment=new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		
		return comment;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 記事のIDからコメントを検索
	 * 
	 * @param articleId
	 * @return
	 */
	public List<Comment>findByArticled(int articleId){
		String Sql
		="SELECT id,name,content,article_id FROM comments WHERE article_id=:articleId ORDER BY id desc";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("articleId",articleId );
		
		List<Comment>commentList=template.query(Sql, param,COMMENT_ROW_MAPPER);
		
		return commentList;
	}
	
	/**
	 * コメントを投稿
	 * 
	 * @param comment
	 */
	public void insert(Comment comment) {
		String Sql
		="INSERT INTO comments(name,content,article_id) VALUES(:name,:content,:articleId)";
		
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(comment);
		
		template.update(Sql,param);
	}
	
	/**
	 * コメント削除
	 * 
	 * @param articleId　記事ID
	 */
	public void deleteByArticled(int articleId) {
		String Sql
		="DELETE FROM comments WHERE article_id=:articleId";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("articleId", articleId);
		
		template.update(Sql, param);
	}
}





