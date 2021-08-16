package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration   //빈등록(IoC관리)
@EnableWebSecurity  //시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//시큐리티 내장 함수
	@Bean  //IoC가 됨
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 비밀번호를 가로채기하는데 
	//해당 비밀번호가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	//같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교할 수 있음 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //crsf 토큰 비활성화(테스트시 걸어두는게 좋음) --> csrf토큰이 없을 경우 막아버리기`때문에 비활성화
			.authorizeRequests()
				.antMatchers("/", "/auth/**","/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll()  //위의 주소로 들어오면 모든 유저의 접근을 허용
				.anyRequest() 
				.authenticated()   //위의 주소 외에는 인증받은 유저만 접근 가능
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") //위의 주소 외에는 인증받지 않는 유저는 로그인 화면이 불러와짐
				.loginProcessingUrl("/auth/loginProc")//로그인 화면에서 로그인 이벤트가 발생하면
				                                 //스프링 시큐어리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해줌
				.defaultSuccessUrl("/");
//				.failureUrl("/fail");
	}
}
