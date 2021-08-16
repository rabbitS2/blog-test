package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@Data  ----->  getter setter 동시에 만들어짐
//@AllArgsConstructor  ----->   모든 필드를 다 쓰는 생성자
//@RequiredArgsConstructor final  ----->  필드 전용 생성자

@Data
@NoArgsConstructor     // 빈 생성자
public class Member {
	private  int id;
	private  String name;
	private  String pwd;
	private  String email;

	@Builder
	public Member(int id, String name, String pwd, String email) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
	}

// final은 불변성 유지
	
	
	
	
}
