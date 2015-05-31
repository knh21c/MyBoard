package net.myboard.service;

import java.util.List;

import net.myboard.value.Article;
import net.myboard.value.BoardFile;
import net.myboard.value.Comment;

public interface ArticleService {
	void insertData(Article article, List<BoardFile> fList);
	void insertComment(Comment comment);
	void updateData(Article article);
	void updateHit(Article article);
	void deleteAllData();
	boolean deleteArticle(Article article);
	boolean editArticle(Article article);
	List<Article> searchArticle(String key, String type);
	Article selectData(Article article);
	List<Comment> selectComments(Comment comment);
	List<BoardFile> selectFile(int id);
	String selectPw(Article article);
	List<Article> selectAllData();
}
