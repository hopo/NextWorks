<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberView</title>

</head>
<body>

	<div class="row">
		<div class="col-sm-12 blog-main">
			<blockquote>
				<table class="table">
					<tr>
						<th>아이디</th>
						<td>${result.memId}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${result.memName}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${result.memPhone}</td>
					</tr>
					<tr>
						<th>가입일</th>
						<td>${result.regDate}</td>
					</tr>
					<tr>
						<th>사용자아이피</th>
						<td>${result.memIp}</td>
					</tr>
					<tr>
						<th>탈퇴여부</th>
						<td>${result.delAt eq 'Y' ? '가입중' : '탈퇴'}</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center;">
							<c:url value="/member/memberList.do?curPage=${param.curPage}" var="memberListUrl" />
							<a href="${memberListUrl}">목록</a>

							<c:url value="/member/memberUpdateForm.do" var="updateUrl">
								<c:param name="memId" value="${param.memId}" />
								<c:param name="curPage" value="${param.curPage}" />
							</c:url>
							<a href="${updateUrl}">수정</a>

							<c:url value="/member/memberDeleteForm.do" var="deleteUrl">
								<c:param name="memId" value="${param.memId}" />
							</c:url>
							<a href="${deleteUrl}">삭제</a>
						</th>
					</tr>
				</table>
			</blockquote>
		</div>
	</div>

</body>
</html>