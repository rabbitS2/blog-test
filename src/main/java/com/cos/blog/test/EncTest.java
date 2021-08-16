package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void 해쉬암호화() {
		String encPWD = new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234해쉬:" + encPWD);
	}
}
