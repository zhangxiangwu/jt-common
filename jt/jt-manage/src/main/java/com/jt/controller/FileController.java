package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.web.FileService;
import com.jt.vo.ImageVO;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	//http://127.0.0.1:8091/pic/upload?dir=image
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public ImageVO fileTransfer(MultipartFile uploadFile) {
		
		return fileService.upload(uploadFile);
	}
	
}
