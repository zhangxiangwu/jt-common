package com.jt.testfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileTranstion {

	@RequestMapping("/file")
	@ResponseBody
	public String fileTranstion(MultipartFile fileImage) throws Exception {
		
		String fileName = fileImage.getOriginalFilename();
		
		File dir = new File("D:/jt-image");
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File path = new File(dir+"/"+fileName);
		
		fileImage.transferTo(path);
		
		return "文件上传成功！！！";
	}
	
	public static void main(String[] args) throws Exception {
		
	  File file = new File("D:/file");
	  
	  if(!file.exists()) {
		  file.mkdirs();
	  }
		
	  File path = new File(file+"/"+"ccc.txt");
	  
	 FileOutputStream os = new FileOutputStream(path);
	 
	 byte[] a = new byte[10];
	    a[0] = 'a';
	    a[1] = 'b';
	 os.write(a);
	 os.write(12456);
	 os.close();
	 
	   System.out.println("文件上传成功！！！");
		
	}
	
}





