package com.jt.service.impl;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.web.FileService;
import com.jt.vo.ImageVO;

@Service
public class FileServiceImpl implements FileService {

	//真实路径
	private String localDirPath  = "D:/jt-image/";
    //虚拟路径
	private String urlDirPath = "http://image.jt.com/";
	
	@Override
	public ImageVO upload(MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		//1.获取图片名称
		String fileName = uploadFile.getOriginalFilename();
		
		fileName = fileName.toLowerCase();
		
		//2.校验正则表达式
		
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			return ImageVO.fail();
		}
		
		//3.校验图片属性
		
		  try {
			  BufferedImage bImage = ImageIO.read(uploadFile.getInputStream());
			  
			  int width = bImage.getWidth();
			  int height = bImage.getHeight();
			  if(width == 0 || height ==0) {
					return ImageVO.fail();
				}
			  
	     //4.创建文件路径
		   String datePath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
		   
		   String dirFilePath = localDirPath + datePath;

		   File dirFile = new File(dirFilePath);
		   
		   if(!dirFile.exists()) {
			   dirFile.mkdirs();
		   }
		   
		   //5.动态生成文件名称 uuid+文件后缀
			  
		   String uuid = UUID.randomUUID().toString();
		   String fileType = fileName.substring(fileName.lastIndexOf("."));
		   
		   String realFileName = uuid + fileType;
		   String realFilePath = dirFilePath + realFileName;
		   uploadFile.transferTo(new File(realFilePath));
		   
		   //6.实现数据回显
		   String url = urlDirPath + datePath + realFileName;
		   //System.out.println(url);
			ImageVO imageVO = new ImageVO(0, url, width, height);
			return imageVO;
		   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		return null;
	}

}




























