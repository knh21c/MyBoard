package net.myboard.value;

public class Comment {
	private int id;
	private String comment;
	
	public Comment(){
		
	}
	
	public Comment(int id) {
		super();
		this.id = id;
	}

	public Comment(int id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
