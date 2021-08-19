package com.example.form;
/**
 * 記事情報のフォームクラス
 * 
 * @author adachiryuji
 *
 */
public class ArticleForm {
	
	/** 投稿者の名前 */
	private String name;
	/**　投稿内容 */
	private String content;
	
	
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
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
		
}
