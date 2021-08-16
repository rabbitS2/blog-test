package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리<html> 태그가 섞여서 디자인 됨

	private int count; //조회수

	// Many = Board, User = One  --> 한명의 유저는 여러개의 게시글을 쓸 수 있다
	// fetch = FetchType.EAGER  --> 게시물을 select하면 user는 기본으로 가져온다는 뜻
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다
	
	
	// mappeBy : 연관관계의 주인이 아님(FK가 아니란 뜻) 때문에 DB에 컬럼 생성 안 함 
	// mappedBy = "board" ---> Reply클래스의 변수명
	// fetch = FetchType.LAZY  --> 게시물을 select하면 reply는 필요하면 가져오겠다는 뜻. 필요 없으면 안 가져옴
	// fetch = FetchType.EAGER --> 게시물을 select하면 reply는 기본으로 가져온다는 뜻
	// 게시물에서 펼치기 버튼을 눌러야 댓글이 나온다면 LAZY를 사용하면됨
	// 그러나 우리는 게시물이 보일때 댓글도 동시에 보이게 만들것이기 때문에 댓글 정보를 기본으로 가져와야함
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;     
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
	
	
}
