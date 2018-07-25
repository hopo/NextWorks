<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- html 생성시 화이트스페이스 제거 -->
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/gugu.dan?flag=0" var="gugu" />

<div class="blog-masthead">
	<div class="container">
		<nav class="blog-nav">
			<a class="blog-nav-item active" href="/">Home</a>
			<a class="blog-nav-item" href="/test.jsp">테스트</a>
			<a class="blog-nav-item" href="${gugu}">구구단</a>
			<a class="blog-nav-item" href="/boardList.oka">보드리스트</a>
			<span class="dropdown">
				<a class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
					MEMBER<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/member/memberList.do">리스트</a></li>
					<li><a href="/member/memberInsertForm.do">등록</a></li>
					<li><a href="#drop3">drop3</a></li>
					<li><a href="#drop4">drop4</a></li>
				</ul>
			</span>
		</nav>
	</div>
</div>
