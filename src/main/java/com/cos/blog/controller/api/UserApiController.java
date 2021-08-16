package com.cos.blog.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController:save호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
	
/*	  직접 세션에 접근하는 방식 --> 현재는 불가능
 
	@PutMapping("/user")                                         
	public ResponseDto<Integer> update(@RequestBody User user, 
			@AuthenticationPrincipal PrincipalDetail principal,
			HttpSession session) { //RequestBody로 해야 JSON 데이터를 받을 수 있음
		// RequestBody 안적으면 JSON 데이터가 아니라 key=value 데이터를 x-www-form-urlencoded로 받음
		
		System.out.println("UserApiController:save호출됨");       
		userService.회원수정(user);
		//여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음
		//하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줘야함
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
*/	
	@PutMapping("/user")                                         
	public ResponseDto<Integer> update(@RequestBody User user) { //RequestBody로 해야 JSON 데이터를 받을 수 있음
		// RequestBody 안적으면 JSON 데이터가 아니라 key=value 데이터를 x-www-form-urlencoded로 받음
		
		System.out.println("UserApiController:save호출됨");       
		userService.회원수정(user);
		//여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음
		//하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줘야함
		
		//세션등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
	
	
	
	
//	@Autowired
//	private HttpSession session;
	
/*
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController:save호출됨");
		//실제로 DB에 insert를 하고 아래에서 return이 되면 됨
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
*/
/*	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("UserApiController:login호출됨");
		User principal =userService.로그인(user);  //principal 접근주체
		System.out.println(principal);
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
		
	}
*/
/*		
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController:login호출됨");
		User principal =userService.로그인(user);  //principal 접근주체
		System.out.println(principal);
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
		
	}
*/	
	
	
}
