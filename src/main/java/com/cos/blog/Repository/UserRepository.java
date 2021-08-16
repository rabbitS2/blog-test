package com.cos.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO
//자동으로 bean등록 
//@Repository 생략가능
//해당 JpaRepository는 User테이블이 관리하는 repository임 / User테이블의 PK는 Integer타입 
public interface UserRepository extends JpaRepository<User, Integer>{

	// JAP Naming 쿼리 (findBy라고 적으면 자동으로 select구문을 생성)
	// select * from user where name=?
	// User findByNameAndPwd(String name, String pwd);

	// @Query(value="select * from user where name=? and pwd=?",nativeQuery = true)
	// User login(String name, String pwd);

	//select * from user where name=?;
	Optional<User> findByName(String name);


}
