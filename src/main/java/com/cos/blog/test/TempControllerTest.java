package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String temHome() {
		System.out.println("tempHome()");
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명 : /index.html
		//풀경로 : src/main/resources/static/index.html
		return "/index.html";
	}
	
	
	@GetMapping("/temp/jsp")
	public String temJsp() {
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		//풀경로 : /WEB-INF/views/test
		System.out.println("temJsp()");
		return "test";
	}
	
	
	
	
	
}
