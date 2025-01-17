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
<title>mberListForm</title>

<script type="text/javascript" src="<c:url value='/js/comBiz.js'/>"></script>
<script type="text/javascript">

	function fn_DisplayRs(rs) {
		$('#viewItem').empty();
		
		$.each(rs, function(key, value){
			console.log(value)
			
			var rs = '';
			rs += '<tr>';
			rs += '<th>' + key + '</th>';
			rs += '<th><img src="/attach/download/"' + value.memId + 'width="100px"></th>';
			rs += '<th><a href="#">' + value.memId + '</a></th>';
			rs += '<th>' + value.memName + '</th>';
			rs += '<th>' + value.memPhone + '</th>';
			rs += '<th>' + value.memEmail + '</th>';
			rs += '<th>' + value.regDate + '</th>';
			rs += '<th>' + value.delAt + '</th>';
			rs += '</tr>';
				
			$('#viewItem').append(rs);
		});
	}	



	// ;jQuery를 이용하여 버튼 컨트롤(ajax를 방식)	
	$(document).ready(function() {
		
		$frm = $("#memberSearchVo");

		// ;;서브밋 버튼 클릭시
		$("button[type=submit]", $frm).click(function(e) {
			e.preventDefault();
			$ ('input[name=curPage]', $frm).val(1);

			$.ajax({
				url			: '/mber/memberListProc',
				method		: "POST",
				dataType	: 'json',
				data		: $('#memberSearchVo').serialize(),
				success		: function(data, status, xhr) {

					console.log("@@@", data);
					//alert("@@@" + data.message);
					//alert("@@@" + JSON.stringify(data.list));
					//alert("@@@" + JSON.stringify(data.search));
					
					fn_DisplayRs(data.list);
					fn_paginagtion(data.search);
					
				},
				error		: function(jqXhr, textStatus, errorMessage) {
					console.log(jqXhr);
					console.log(textStatus);
					console.log(errorMessage);
				}
			});
		});
	});

</script>


</head>

<body>
	<form:form commandName="memberSearchVo" method="post">
		<form:hidden path="curPage" />
		<form:hidden path="screenSize" />

		<table class="table">
			<tr>
				<th>조회 조건(search): 타입</th>
				<td>
					<form:select path="searchType" cssClass="form-control">
						<form:option value="mem_id">아이디</form:option>
						<form:option value="mem_name">이름</form:option>
						<form:option value="mem_email">이메일</form:option>
						<form:option value="mem_phone">폰번호</form:option>
					</form:select>
				</td>
				<td><form:input path="searchText" cssClass="form-control" /></td>
				<td><form:button type="submit" cssClass="form-control">SEARCH</form:button></td>
			</tr>
		</table>
	</form:form>

	<table class="table">
		<thead>
			<tr>
				<th>순번</th>
				<th>사진</th>
				<th>이름</th>
				<th>폰번호</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>탈퇴여부</th>
			</tr>
		</thead>
		
		<tbody id="viewItem"></tbody>
	</table>



	<nav>
		<ul class='pagination pagination-lg'>
	
			<!-- // ;;Previous button -->
			<c:if test="${memberSearchVo.startPage ne 1}">
				<li><a href='#' aria-label='Previous' data-curpage='${memberSearchVo.startPage - 1}' class='prev goPage'> <span aria-hidden='true'>Prev</span>
				</a></li>
			</c:if>
	
			<c:if test="${memberSearchVo.startPage eq 1}">
				<li><a href='#' aria-label='Previous'> <span aria-hidden='true'>Prev</span>
				</a></li>
			</c:if>
	
			<!-- // ;;Page numbering -->
			<c:forEach var="i" begin="${memberSearchVo.startPage}" end="${memberSearchVo.endPage}">
				<c:choose>
					<c:when test="${i eq memberSearchVo.curPage}">
						<li class='activate'><a href='#'>${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class='activate'><a href='#' data-curpage='${i}' class='goPage'>${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<!-- // ;;Next button-->
			<c:if test="${memberSearchVo.endPage lt memberSearchVo.totalPageCount}">
				<li>
					<a href='#' aria-label='Next' data-curpage='${memberSearchVo.endPage + 1}' class='next goPage'>
						<span aria-hidden='true'>Next</span>
					</a>
				</li>
			</c:if>
	
			<c:if test="${memberSearchVo.endPage ge memberSearchVo.totalPageCount}">
				<li><a href='#' aria-label='Next'> <span aria-hidden='true'>Next</span>
				</a></li>
			</c:if>
	
		</ul>
	</nav>
</body>
</html>