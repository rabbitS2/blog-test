package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
//ORM -> JAVA(다른언어)Object를 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 자동으로 테이블 생성
// @DynamicInsert // insert할 때 값이 null인 필드 제외
public class User {

	@Id // PK 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; // 오라클 : 시퀀스 / mySQL : auto_increment

	@Column(nullable = false, length = 200, unique = true)
	private String name;

	@Column(nullable = false, length = 100) // not null, 해쉬(비밀번호 암호화) 때문에 길이 넉넉하게
	private String pwd;

	@Column(nullable = false, length = 50)
	private String email;

//  클래스 위에 @DynamicInsert를 썼을 경우 ColumnDefault("'user'")사용
//	@ColumnDefault("'user'") // 'user'로 써야 문자로 인식됨
//	private String role; // Enum을 쓰는게 좋다 // admin, user, manager
	
	//DB는 RoleType이 없기 때문에 어노테이션을 붙여줌
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다 // admin, user, manager
	
	
	private String oauth; //kakao, google  --> 로그인 타입

	@CreationTimestamp // 시간이 자동 입력 
	private Timestamp createDate;
	 

}
