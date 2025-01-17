<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 작성</title>

<script type="text/javascript" defer="defer">

	$(document).ready(function() {
		
		$('.btn_file_new').on('click', function(){
			var fileView = '';
			fileView = '<div>';
			fileView += '<input type="file" name="attachFiles">';
			fileView += '<input type="button" class="btn_file_remove form-control" value="삭제">';
			fileView += '</div>';
			
			$('.file_area').append(fileView);
		});
		
		// ;신규 파일 삭제
		$('.file_area').on('click', '.btn_file_remove', function() {
			$(this).parent().remove();
		});

		// 폼(submit) 이벤트
		$('#noticeVo').on('submit', function() {

			if (!/[\w가-힇]+/.test($('input[name="title"]').val())) {
				alert("제목은 최소 한글자 이상 입력");
				return false;
			}
			
			if (!/^[가-힇]+$/.test($('textarea[name="contents"]').val())) {
				alert("내응은 한글만 입력");
				return false;
			}

			return true;
		});
	});
	
</script>
</head>

<body>

	<blockquote>
		<form:form commandName="noticeVo" action="/notice/noticeProc" method="post" enctype="multipart/form-data">
			<table class='table'>
				<tbody>
					<tr>
						<th>게시판타입</th>
						<td>
							<form:select path="boardType" cssClass="form-control">
								<form:option value="B001">공지사항</form:option>
								<form:option value="B002">일반</form:option>
								<form:option value="B003">자료실</form:option>
								<form:option value="B004">질의응답</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><form:input path="title" cssClass="form-control" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><form:textarea path="contents" cssClass="form-control" /></td>
					</tr>
					<tr>
						<th>등록자</th>
						<td><form:input path="regUser" readonly='true' cssClass="form-control" /></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<div class="file_area">
								<div>
									<input type="button" class="btn_file_new form-control" value="추가">
								</div>
								<div>
									<input type="file" name="attachFiles" class="form-control">
									<input type="button" class="btn_file_remove form-control" value="삭제">
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan='2'><form:button type="submit" cssClass="form-control">저~장</form:button></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</blockquote>

</body>
</html>