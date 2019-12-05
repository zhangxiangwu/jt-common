package com.jt.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SystemException {

	@ExceptionHandler(RuntimeException.class)
	public SysResult exception(Throwable throwable) {
		
		throwable.printStackTrace();
		log.info(throwable.getMessage());
		return SysResult.fail();
	}
	
}
