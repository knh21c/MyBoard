package net.myboard.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.myboard.repository.ArticleDao;
import net.myboard.value.Article;
import net.myboard.value.BoardFile;
import net.myboard.value.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao dao;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	private DefaultTransactionDefinition def;
	private TransactionStatus status;

	public void init() {
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		status = txManager.getTransaction(def);
	}

	@Override
	public void insertData(Article article, List<BoardFile> fList) {
		// TODO Auto-generated method stub
		init();
		try {
			article.setHit(0);
			String content = article.getContent();
			content = content.replace("\r\n", "<br>");
			article.setContent(content);
			dao.insertData(article);
			int rs = dao.selectLastIndex();
			for(BoardFile temp : fList){
				temp.setId(rs);
				dao.insertFile(temp);
			}
			txManager.commit(status);
		} catch (Exception e) {
			txManager.rollback(status);
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Article article) {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	public void updateHit(Article article) {
		// TODO Auto-generated method stub
		init();
		try {
			article.setHit(article.getHit() + 1);
			dao.updateHit(article);
			txManager.commit(status);
		} catch (Exception e) {
			txManager.rollback(status);
			e.printStackTrace();
		}
	}

	@Override
	public Article selectData(Article article) {
		// TODO Auto-generated method stub
		List<BoardFile> fList;
		Article rsArticle = dao.selectData(article);
		updateHit(rsArticle);
		String content = rsArticle.getContent();
		fList = dao.selectFile(article.getId());
		for(int i=0; i<fList.size(); i++){
			content = content.replace("<image" + i + ">", "<img src='http://localhost:8080/MyBoard/image/" + fList.get(i).getTempFileName() + ".jpg'>");
		}
		rsArticle.setContent(content);
		return rsArticle;
	}

	public String selectPw(Article article) {
		return dao.selectPw(article);
	}

	@Override
	public List<Article> selectAllData() {
		// TODO Auto-generated method stub
		List<Article> list = dao.selectAllData();
		Collections.reverse(list);
		return list;
	}

	@Override
	public boolean deleteArticle(Article article) {
		// TODO Auto-generated method stub
		init();
		String selectedPw = dao.selectPw(article);
		try {
			if (article.getPw().equals(selectedPw)) {
				dao.deleteData(article);
				dao.deleteComment(new Comment(article.getId()));
				txManager.commit(status);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			txManager.rollback(status);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editArticle(Article article) {
		// TODO Auto-generated method stub
		init();
		String selectedPw = selectPw(article);
		String content = article.getContent();
		content = content.replace("\r\n", "<br>");
		article.setContent(content);
		try {
			if (article.getPw().equals(selectedPw)) {
				dao.updateContent(article);
				txManager.commit(status);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			txManager.rollback(status);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void insertComment(Comment comment) {
		// TODO Auto-generated method stub
		init();
		try{
			String commentStr = comment.getComment();
			commentStr = commentStr.replace("\r\n", "<br>");
			comment.setComment(commentStr);
			dao.insertComment(comment);
			txManager.commit(status);
		}catch(Exception e){
			txManager.rollback(status);
			e.printStackTrace();
		}
	}

	@Override
	public List<Comment> selectComments(Comment comment) {
		// TODO Auto-generated method stub
		return dao.selectComments(comment);
	}

	@Override
	public List<Article> searchArticle(String key, String type) {
		// TODO Auto-generated method stub
		List<Article> list = null;
		key = "%" + key + "%";
		if(type.equals("total")){
			list = dao.searchTotal(key);
		}else if(type.equals("subject")){
			list = dao.searchSubject(key);
		}else if(type.equals("author")){
			list = dao.searchAuthor(key);
		}else if(type.equals("content")){
			list = dao.searchContent(key);
		}
		Collections.reverse(list);
		return list;
	}

	@Override
	public List<BoardFile> selectFile(int id) {
		// TODO Auto-generated method stub
		return dao.selectFile(id);
	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub
		init();
		try{
			dao.deleteAllBoard();
			dao.deleteAllComments();
			dao.deleteAllBoardFile();
			txManager.commit(status);
		}catch(Exception e){
			txManager.rollback(status);
			e.printStackTrace();
		}
	}
}
