<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>  <!-- JSON으로 데이터를 전송 / JAVA script로 가져감  -->
		<div class="form-group">
			<label for="name">Username</label> 
			<input type="text" class="form-control" placeholder="Enter Username" id="name">
		</div>
		<div class="form-group">
			<label for="pwd">pwd</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
		<button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>