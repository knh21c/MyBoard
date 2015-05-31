package net.myboard.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import net.myboard.value.BoardFile;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static BoardFile uploadImages(MultipartFile image){
		InputStream stream;
		
		UUID uuid = UUID.randomUUID();
		String tempFileName = uuid.toString();
		try{
			stream = image.getInputStream();
			OutputStream bos = new FileOutputStream("X:/Eclipse Workspace/MyBoard/WebContent/image/" + tempFileName + ".jpg");
			int bytesRead=0;
			byte[] buffer = new byte[8192];
			while((bytesRead = stream.read(buffer, 0, 8192)) != -1){
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		BoardFile result = new BoardFile();
		result.setFileName(image.getOriginalFilename());
		result.setFileSize(image.getSize());
		result.setContentType(image.getContentType());
		result.setTempFileName(tempFileName);
		
		return result;
	}
}
