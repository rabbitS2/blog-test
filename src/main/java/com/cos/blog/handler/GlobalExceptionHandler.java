package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice  //프로젝트의 어디서든 exception이 발생하면 이 페이지로 온다
@RestController
public class GlobalExceptionHandler {

	/*
	//IllegalArgumentException이 발생해면 해당 메소드 실행
	@ExceptionHandler(value=IllegalArgumentException.class)  
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	*/
	
	/*
	//모든 Exception이 발생해면 해당 메소드 실행
	@ExceptionHandler(value=Exception.class)  
	public String handleArgumentException(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	*/
	
	@ExceptionHandler(value=Exception.class)  
	public ResponseDto<String> handleArgumentException(Exception e) {
		
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); //500에러
	}
	
	
	
	
	
	
}
