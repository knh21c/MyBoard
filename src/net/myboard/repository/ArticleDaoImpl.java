package net.myboard.repository;

import java.util.List;

import net.myboard.value.Article;
import net.myboard.value.BoardFile;
import net.myboard.value.Comment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public void insertData(Article article) throws Exception{
		// TODO Auto-generated method stub
		sqlSession.insert("query.insert", article);
	}

	@Override
	public void updateContent(Article article) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("query.updateContent", article);
	}
	
	@Override
	public void updateHit(Article article) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("query.updateHit", article);
	}

	@Override
	public void deleteData(Article article) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("query.delete", article);
	}

	@Override
	public Article selectData(Article article) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("query.select", article);
	}
	
	public String selectPw(Article article){
		return sqlSession.selectOne("query.selectPw", article);
	}

	@Override
	public List<Article> selectAllData() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.selectAll");
	}

	@Override
	public void insertComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("query.insertComment", comment);
	}

	@Override
	public List<Comment> selectComments(Comment comment) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.selectComments", comment);
	}
	
	@Override
	public void deleteComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.selectList("query.deleteComment", comment);
	}

	@Override
	public List<Article> searchTotal(String key) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.searchTotal", key);
	}

	@Override
	public List<Article> searchSubject(String key) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.searchSubject", key);
	}

	@Override
	public List<Article> searchAuthor(String key) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.searchAuthor", key);
	}

	@Override
	public List<Article> searchContent(String key) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.searchContent", key);
	}

	@Override
	public void insertFile(BoardFile file) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("query.insertFile", file);
	}

	@Override
	public int selectLastIndex() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("query.lastIndex");
	}

	@Override
	public List<BoardFile> selectFile(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("query.selectFile", id);
	}

	@Override
	public void deleteAllBoard() throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("query.deleteAllBoard");
	}

	@Override
	public void deleteAllComments() throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("query.deleteAllComments");
	}

	@Override
	public void deleteAllBoardFile() throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("query.deleteAllBoardFile");
	}
}
