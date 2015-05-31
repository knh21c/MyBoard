package net.myboard.repository;

import java.util.List;

import net.myboard.value.Article;
import net.myboard.value.BoardFile;
import net.myboard.value.Comment;

public interface ArticleDao {
	void insertData(Article article) throws Exception;
	void insertFile(BoardFile file) throws Exception;
	void updateContent(Article article) throws Exception;
	void updateHit(Article article) throws Exception;
	void deleteData(Article article) throws Exception;
	void insertComment(Comment comment) throws Exception;
	void deleteComment(Comment comment) throws Exception;
	void deleteAllBoard() throws Exception;
	void deleteAllComments() throws Exception;
	void deleteAllBoardFile() throws Exception;
	Article selectData(Article article);
	String selectPw(Article article);
	int selectLastIndex();
	List<Comment> selectComments(Comment comment);
	List<Article> selectAllData();
	List<BoardFile> selectFile(int id);
	List<Article> searchTotal(String key);
	List<Article> searchSubject(String key);
	List<Article> searchAuthor(String key);
	List<Article> searchContent(String key);
}
