package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.Repository.BoardRepository;
import com.cos.blog.Repository.ReplyRepository;
import com.cos.blog.Repository.UserRepository;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;

import lombok.RequiredArgsConstructor;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 ---> IoC를 해준다는 뜻 --> 메모리에 대신 띄워준다는 뜻
@Service
@RequiredArgsConstructor
public class BoardService {

	/*
	 * @Autowired private BoardRepository boardRepository;
	 * 
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Autowired private ReplyRepository replyRepository;
	 */
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
//	public BoardService(BoardRepository bRepo, ReplyRepository rRepo) {
//		this.boardRepository = bRepo;
//		this.replyRepository = rRepo;
//	}

	
	@Transactional
	public void 글쓰기(Board board, User user) { // title , content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}                 
	
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다.");	
		});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		System.out.println("글삭제하기:" + id);
		boardRepository.deleteById(id);
	}
	
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패:아이디를 찾을 수 없습니다.");	
		}); //영속화 완료
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
		// commit이 된다는 뜻
	}
	
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
		replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
	}
	
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
/*	
	@Transactional(readOnly = true)//select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 ( 정합성 )
	public User 로그인(User user) {
		return userRepository.findByNameAndPwd(user.getName(), user.getPwd());
	}
*/	
	
	
}
