package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

//	@GetMapping({"","/"})
//	public String index(@AuthenticationPrincipal PrincipalDetail principal) { //컨트롤러에서 세션을 어떻게 찾는지?
//		// /WEB-INF/views/index.jsp
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
//		return "index";
//	} 
	
	//컨트롤러에서 세션을 어떻게 찾는지?
	// 위에 코드로 실행하면 메인페이지로 갈때 로그인이 되어있어야함. 로그인 안된상태로 메인페이지 넘어가면 500에러
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) { 
		// /WEB-INF/views/index.jsp
		model.addAttribute("boards", boardService.글목록(pageable)); //model은 JSP에서는 request라고 보면됨
		return "index"; //viewResolver 작동
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	
}
