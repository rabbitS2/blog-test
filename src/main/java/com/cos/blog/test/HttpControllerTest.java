package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";

// Member.java에서 @AllArgsConstructor 사용할때
//	@GetMapping("/http/lombox")
//	public String lomboxTest() {
//		Member m= new Member(1, "kim", "1234", "email");
//		System.out.println(TAG+"getter:"+m.getId());
//		m.setId(5000);
//		System.out.println(TAG+"getter:"+m.getId());
//		return "lombox test 완료";
//	}
	// Member.java에서 @builder 사용할때
	@GetMapping("/http/lombox")
	public String lomboxTest() {
		Member m = Member.builder().name("ss").pwd("123").email("df@df.com").build(); //순서는 상관 없음
		System.out.println(TAG+"getter:"+m.getName()); 
		m.setName("ccccccc");
		System.out.println(TAG+"getter:"+m.getName());
		return "lombox test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다
	//http://localhost:8080/http/get (select)
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String name) {
//		return "get 요청: " + id + ","+ name;
//	}
	//http://localhost:8080/http/get?id=1&name=ssar&pwd=1234&email=ssar@ssar.com
//	@GetMapping("/http/get")
//	public String getTest(Member m) {
//		
//		return "get 요청: " + m.getId() + ","+ m.getName() + "," + m.getPwd()+ "," + m.getEmail();
//	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get 요청: " + m.getId() + ","+ m.getName() + "," + m.getPwd()+ "," + m.getEmail();
	}
	
//----------------------------------------------------------------------------------
	
	//http://localhost:8080/http/post (insert)
//	@PostMapping("/http/post")
//	public String postTest(Member m) {
//		return "post 요청: "+ m.getId() + ","+ m.getName() + "," + m.getPwd()+ "," + m.getEmail();
//	}
	
	// text/plain
//	@PostMapping("/http/post") 
//	public String postTest(@RequestBody String text) {
//		return "post 요청: "+ text;  
//	}
	
	// application/json
	@PostMapping("/http/post") 
	public String postTest(@RequestBody Member m) {
		return "post 요청: "+ m.getId() + ","+ m.getName() + "," + m.getPwd()+ "," + m.getEmail(); 
	}
	
//----------------------------------------------------------------------------------	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청: "+ m.getId() + ","+ m.getName() + "," + m.getPwd()+ "," + m.getEmail();
	}
//----------------------------------------------------------------------------------
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
