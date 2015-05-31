package net.myboard.value;

public class BoardFile {
	private int id;
	private String fileName;
	private long fileSize;
	private String contentType;
	private String tempFileName;
	
	public BoardFile(){
		
	}
	
	public BoardFile(int id, String fileName, long fileSize,
			String contentType, String tempFileName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.tempFileName = tempFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getTempFileName() {
		return tempFileName;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardFile [fileName=");
		builder.append(fileName);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append(", tempFileName=");
		builder.append(tempFileName);
		builder.append("]");
		return builder.toString();
	}
}
