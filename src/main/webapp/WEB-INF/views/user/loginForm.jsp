<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="name">Username</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter Username" id="name">
		</div>
		<div class="form-group">
			<label for="pwd">pwd</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> 
			<input class="form-check-input" name="remember" type="checkbox"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=8b3b23ddd27b2420c925345260a1bc91&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_btn.png"></a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>