package net.myboard.value;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Article {
	private int id;
	private String pw;
	private String subject;
	private String author;
	private Date date;
	private String strDate;
	private int hit;
	private String content;
	private MultipartFile image0;
	private MultipartFile image1;
	private MultipartFile image2;
	private MultipartFile image3;
	private MultipartFile image4;
	
	public Article(){
		
	}
	
	public Article(int id) {
		super();
		this.id = id;
	}
	
	public Article(int id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	public Article(int id, String pw, String content) {
		super();
		this.id = id;
		this.pw = pw;
		this.content = content;
	}

	public Article(String subject, String author, Date date, int hit,
			String content) {
		this.subject = subject;
		this.author = author;
		this.date = date;
		this.hit = hit;
		this.content = content;
	}
	
	public Article(int id, String pw, String subject, String author, Date date,
			String strDate, int hit, String content, MultipartFile image0, MultipartFile image1,
			MultipartFile image2, MultipartFile image3, MultipartFile image4) {
		super();
		this.id = id;
		this.pw = pw;
		this.subject = subject;
		this.author = author;
		this.date = date;
		this.strDate = strDate;
		this.hit = hit;
		this.content = content;
		this.image0 = image0;
		this.image1 = image1;
		this.image2 = image2;
	}

	public Article(int id, String pw, String subject, String author,
			String strDate, int hit, String content) {
		this.id = id;
		this.pw = pw;
		this.subject = subject;
		this.author = author;
		this.strDate = strDate;
		this.hit = hit;
		this.content = content;
	}

	public Article(int id, String pw, String subject, String author, Date date,
			String strDate, int hit, String content) {
		this.id = id;
		this.pw = pw;
		this.subject = subject;
		this.author = author;
		this.date = date;
		this.strDate = strDate;
		this.hit = hit;
		this.content = content;
	}

	public String getPw(){
		return pw;
	}
	public void setPw(String pw){
		this.pw = pw;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStrDate(){
		return strDate;
	}
	public void setStrDate(String strDate){
		this.strDate = strDate;
	}
	
	public MultipartFile getImage0() {
		return image0;
	}

	public void setImage0(MultipartFile image0) {
		this.image0 = image0;
	}

	public MultipartFile getImage1() {
		return image1;
	}

	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}

	public MultipartFile getImage2() {
		return image2;
	}

	public void setImage2(MultipartFile image2) {
		this.image2 = image2;
	}

	public MultipartFile getImage3() {
		return image3;
	}

	public void setImage3(MultipartFile image3) {
		this.image3 = image3;
	}

	public MultipartFile getImage4() {
		return image4;
	}

	public void setImage4(MultipartFile image4) {
		this.image4 = image4;
	}
	
	public List<MultipartFile> getImages(){
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		list.add(image0); list.add(image1); list.add(image2); list.add(image3); list.add(image4);
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [id=");
		builder.append(id);
		builder.append(", pw=");
		builder.append(pw);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", author=");
		builder.append(author);
		builder.append(", date=");
		builder.append(date);
		builder.append(", strDate=");
		builder.append(strDate);
		builder.append(", hit=");
		builder.append(hit);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
	
}
