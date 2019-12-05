package com.jt.service.web;

import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVO;

public interface FileService {

	ImageVO upload(MultipartFile uploadFile);

}
