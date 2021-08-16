<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>아영이 프젝</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- SummerNote -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- SummerNote -->

</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">Cos</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
		
<%-- 		<c:choose>
			<c:when test="${empty sessionScope.principal}">   <!-- empty : null이거나 비어있다는 뜻 -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/loginForm">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/form">회원정보</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
				</ul>
			</c:otherwise>
		</c:choose> --%>
		<c:choose>
			<c:when test="${empty principal}">   <!-- empty : null이거나 비어있다는 뜻 -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					<!-- spring 시큐리어티에서는 /logout이 로그아웃하는 dafault 주소임 -->
				</ul>
			</c:otherwise>
		</c:choose> 
		
		</div>
	</nav>
	<br/>