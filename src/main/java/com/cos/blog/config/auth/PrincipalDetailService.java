package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.Repository.UserRepository;
import com.cos.blog.model.User;

@Service // Bean등록
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// 스프링이 로그인 요청을 가로챌 때, name, pwd 변수 2개를 가로챔
	// pwd 부분처리는 알아서 함
	// name이 DB에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		System.out.println("===================================");
		System.out.println(name);
		System.out.println("===================================");
		
		User principal = userRepository.findByName(name).orElseThrow(() -> {
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + name);
		});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보가 저장이 됨. 
	}
}
