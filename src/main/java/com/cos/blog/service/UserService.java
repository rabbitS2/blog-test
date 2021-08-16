package com.cos.blog.service;


import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.Repository.UserRepository;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 ---> IoC를 해준다는 뜻 --> 메모리에 대신 띄워준다는 뜻
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPWD = user.getPwd(); //일반적인 비밀번호
		String encPWD = encoder.encode(rawPWD); //비밀번호 해쉬화
		user.setPwd(encPWD);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User 회원찾기(String name) {

		User user = userRepository.findByName(name).orElseGet(()->{
			return new User(); //회원이 없으면 빈객체를 반환
		});
		
		return user;  
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		//select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화 하기 위해서
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
		User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		// Validate 체크 -> oauth 필드에 값이 없으면 수정 가능
		if(persistence.getOauth() == null || persistence.getOauth().equals("")) {
			String rawPWD = user.getPwd();
			String encPWD = encoder.encode(rawPWD);
			persistence.setPwd(encPWD);
			persistence.setEmail(user.getEmail());
		}
		
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됨
		//영속화된 persistence 객체의 변화가 감지되면 더티체킹 되어 update문을 날려줌
	}
	
	
/*	
	@Transactional(readOnly = true)//select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 ( 정합성 )
	public User 로그인(User user) {
		return userRepository.findByNameAndPwd(user.getName(), user.getPwd());
	}
*/	
	
	
}
