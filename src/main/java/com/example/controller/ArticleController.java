package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事情報のコントローラクラス
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("/")
public class ArticleController {
	
	
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 掲示板画面へフォードする
	 * 
	 * @return　掲示板画面
	 */
	@RequestMapping("")
		public String index(Model model) {
		
		
		List<Article>articleList=articleRepository.findAll();
		
		for(Article article:articleList) {
			int articleId=article.getId();
			List<Comment>commentList=commentRepository.findByArticled(articleId);
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		
		return "article";
	}
	
	/**
	 * 記事情報を挿入する
	 * 
	 * @param article　投稿したい記事
	 * @return　掲示板画面へフォワード
	 */
	@RequestMapping("insertArticle")
	public String insertArticle(ArticleForm articleform) {
		Article article=new Article();
		article.setName(articleform.getName());
		article.setContent(articleform.getContent());
		articleRepository.insert(article);
		
		return "redirect:/";
	}
	
	/**
	 * 投稿された記事情報を削除する
	 * 
	 * @param id　ID
	 * @return　掲示板へフォワード
	 */
	@RequestMapping("delete")
	public String delete(int id) {
		articleRepository.deleteById(id);
		
		return"redirect:/";
	}
	
	/**
	 * コメントを投稿する
	 * 
	 * @param commentform　コメントのフォームクラス
	 * @return　掲示板画面へリダイレクト
	 */
	@RequestMapping("insertComment")
	public String insertComment(CommentForm commentForm ) {
		Comment comment=new Comment();
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		System.out.println("comment"+comment);
		commentRepository.insert(comment);
		
		return "redirect:/";
	}
	
	/**
	 * 記事とコメントを削除する
	 * 
	 * @param commentForm　コメントフォーム
	 * @return　掲示板画面へリダイレクト
	 */
	@RequestMapping("deleteArticle")
	public String deleteArticle(CommentForm commentForm) {
		Comment comment=new Comment();
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));		
		
		commentRepository.deleteByArticled(comment.getArticleId());
		articleRepository.deleteById(comment.getArticleId());
		
		
		return "redirect:/";
	}
}










