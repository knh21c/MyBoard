package net.myboard.controller;

import java.util.ArrayList;
import java.util.List;

import net.myboard.service.ArticleService;
import net.myboard.service.FileUploadUtil;
import net.myboard.value.Article;
import net.myboard.value.BoardFile;
import net.myboard.value.Comment;

import org.apache.logging.log4j.core.appender.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	private ArticleService service;
	private FileManager fileManager;
	
	@RequestMapping(value="/index", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView toIndex(){
		ModelAndView mav = new ModelAndView("index", "message", service.selectAllData());
		mav.addObject("pageNum", 0);
		return mav;
	}
	
	public ModelAndView toIndex(String popup){
		ModelAndView mav = new ModelAndView("index", "message", service.selectAllData());
		mav.addObject("pageNum", 0);
		mav.addObject("popup", popup);
		return mav;
	}
	
	public ModelAndView toIndex(List<Article> list){
		ModelAndView mav = new ModelAndView("index", "message", list);
		mav.addObject("pageNum", 0);
		return mav;
	}
	
	@RequestMapping(value="/pageswitch", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView pageSwitch(int pageNum){
		ModelAndView mav = new ModelAndView("index", "message", service.selectAllData());
		mav.addObject("pageNum", pageNum);
		return mav;
	}
	
	@RequestMapping(value="/write", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView toWrite(){
		return new ModelAndView("write");
	}
	
	@RequestMapping(value="/cancel", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView cancel(){
		return toIndex();
	}
	
	@RequestMapping(value="/insert", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insert(Article article){
		ArrayList<BoardFile> fList = new ArrayList<BoardFile>();
		List<MultipartFile> mpList = article.getImages();
		
		for(MultipartFile image : mpList){
			if(image != null){
				BoardFile rs = FileUploadUtil.uploadImages(image);
				if(rs.getFileSize() != 0)
					fList.add(rs);
			}
		}
		service.insertData(article, fList);
		return new ModelAndView("redirect:/index.do", "message", service.selectAllData());
	}
	
	@RequestMapping(value="/inner", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView toInner(int id){
		ModelAndView mav = new ModelAndView("inner", "message", service.selectData(new Article(id)));
		mav.addObject("comments", service.selectComments(new Comment(id)));
		
		return mav;
	}
	
	public ModelAndView toInner(int id, String popup){
		ModelAndView mav = new ModelAndView("inner", "message", service.selectData(new Article(id)));
		mav.addObject("comments", service.selectComments(new Comment(id)));
		mav.addObject("popup", popup);
		return mav;
	}
	@RequestMapping(value="/delArticle", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delArticle(int id, String pw){
		String popup;
		if(service.deleteArticle(new Article(id, pw))){
			popup = "게시글이 삭제되었습니다.";
			return toIndex(popup);
		}else{
			popup = "비밀번호가 일치하지 않습니다.";
			return toInner(id, popup);
		}
	}
	@RequestMapping(value="/editArticle", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView editArticle(int id){
		ModelAndView mav = new ModelAndView("edit", "message", service.selectData(new Article(id)));
		return mav;
//		String popup;
//		if(service.editArticle(new Article(id, pw, content))){
//			popup = "게시글이 수정되었습니다.";
//			return toInner(id, popup);
//		}else{
//			popup = "비밀번호가 일치하지 않습니다.";
//			return toInner(id, popup);
//		}
	}
	
	@RequestMapping(value="/addComment", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView addComment(Comment comment){
		service.insertComment(comment);
		return toInner(comment.getId());
	}
	
	@RequestMapping(value="/searchArticle", method=RequestMethod.GET)
	public ModelAndView searchArticle(String key, String type){
		return toIndex(service.searchArticle(key, type));
	}
	
	@RequestMapping(value="/deleteAllData", method={RequestMethod.POST, RequestMethod.GET})
	public void deleteAllData(){
		service.deleteAllData();
	}
}
