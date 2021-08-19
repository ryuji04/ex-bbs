package com.example.form;
/**
 * コメント情報のフォームクラス
 * 
 * @author adachiryuji
 *
 */
public class CommentForm {
	/** 記事投稿者ID */
	private String articleId;
	/** コメント投稿者の名前 */
	private String name;
	/** コメント内容　*/
	private String content;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
