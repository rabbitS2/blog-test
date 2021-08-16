package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.Repository.UserRepository;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;


// @RestController : html파일이 아닌 Data를 리턴해주는 Controller
@RestController
public class DummyControllerTest {
	
	//@RestController읽어서 DummyControllerTest에 띄워줄때 @Autowired가 같이 메모리에 뜸
	//의존성주입(DI)
	@Autowired
	private UserRepository userRepository;
	
	
	// 회원 삭제
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다. 해당 id가 없습니다.";
		}
		
		return "삭제되었습니다";
	}
	
	
	// 회원 정보 수정
	// 주소가 같아도 Mapping 방식이 다르기 때문에 괜찮음
	@Transactional   // ----> save를 저장하지 않아도 update됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		// json 데이터를 요청하면 MessageConverter의 Jackson라이브러리가 JAVA Object로 변환해서 받음
		System.out.println(id);
		System.out.println(requestUser.getPwd());
		System.out.println(requestUser.getEmail());
		
		/*
		 Update할때는 save 안 씀
		 자바는 파라미터에 함수를 넣을 수 없음
		 save 함수는 id를 전달하지 않으면 insert
		 save 함수는 id를 전달하면 해당 id에 대한 데이터가 있을 경우 update
		 save 함수는 id를 전달하는 해당 id에 대한 데이터가 없으면 insert
		*/
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정이 실패했습니다.");
		});
		
		user.setPwd(requestUser.getPwd());
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
		
		// 더티 체킹: 변경 감지 후 DB 수정
		return null;
	}
	
	
	// 모든 회원 정보 select
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 페이징
	// 한페이지당 2건의 회원데이터를 리턴 받음
	// direction=Sort.Direction.DESC ---> order by하는 구문
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	// 특정 회원 정보 select
/*	
	//{id} 주소로 파라메터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user가 null일 경우 처리하기 위해서 orElseGet
		// orElseGet은 null일때 자동으로 객체를 생성해서 User에 insert함
		// orElseGet은 Supplier타입인 new Supplier<> 익명객체를 만들어준다
		// Supplier는 interface(추상메서드)로 사용하기위해서는 익명클래스로 만들어야함
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				return new User();
			}
		});
		return user;
	}
	

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//람다식 --> 익명으로 처리 가능 (Supplier타입을 써야한다는 걸 몰라도 사용가능)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다");
		});
	}
	
*/	
	//{id} 주소로 파라메터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// orElseThrow
		// IllegalArgumentException ---> 잘못된 인수가 들어왔을 때 처리
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id " + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		// 스프링부트는 MessageConverter가 응답시에 자동으로 작동됨. 
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우제어 던져줌
		return user;
	}

	
	// 회원등록
	// http://localhost:8000/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println( user.getId() + "," + user.getName() + "," + user.getPwd() + "," + user.getEmail()+ "," + user.getRole()+ "," + user.getCreateDate());
	
		user.setRole(RoleType.USER);
		userRepository.save(user); //받아온 user 정보를 UserRepository에 저장하겠다는 뜻
		return "회원가입이 완료되었습니다";
	}
}
