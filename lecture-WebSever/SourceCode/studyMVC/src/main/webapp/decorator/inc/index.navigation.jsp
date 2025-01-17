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

			<span class="dropdown">
				<a class="dropdown-toggle blog-nav-item" role="button" data-toggle="dropdown" aria-expanded="false">
					${loginInfo ne null ? loginInfo.memId : 'LOGIN'}
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<c:choose>
						<c:when test="${loginInfo == null}">
							<li><a href="<c:url value="/session/loginForm"/>">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value="/session/logout"/>">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</span>
			
			<span>|</span>

			<a class="blog-nav-item active" href="/">Home</a>
			<a class="blog-nav-item" href="/test.jsp">테스트</a>
			<a class="blog-nav-item" href="${gugu}">구구단</a>
			<a class="blog-nav-item" href="/boardList.oka">서브릿보드</a>

			<span class="dropdown blog-nav-item">
				<a class="dropdown-toggle blog-nav-item" role="button" data-toggle="dropdown" aria-expanded="false">
					멤버
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="/member/memberList.do">리스트</a></li>
					<li><a href="/member/memberInsertForm.do">등록</a></li>
					<li><a href="#drop3">drop3</a></li>
					<li><a href="#drop4">drop4</a></li>
				</ul>
			</span>

			<span class="dropdown blog-nav-item">
				<a class="dropdown-toggle blog-nav-item" role="button" data-toggle="dropdown" aria-expanded="false">공지사항<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
						<li>
							<a href="<c:url value="/notice/noticeList"/>">리스트</a>
						</li>
						<c:if test="${loginInfo != null}">
							<li><a href="<c:url value="/notice/noticeForm"/>">글작성(로그인맨만)</a></li>
						</c:if>
				</ul>
			</span>

			<span class="dropdown blog-nav-item">
				<a class="dropdown-toggle blog-nav-item" role="button" data-toggle="dropdown" aria-expanded="false">파일<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
						<li>
							<a href="<c:url value="/attach/attachForm"/>">첨부파일</a>
						</li>
						<li>
							<a href="<c:url value="/attach/testAttach"/>">다운로드</a>
						</li>
				</ul>
			</span>


		</nav>
	</div>
</div>
