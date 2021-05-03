<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mydictionary</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"  ></script>
<script>
$(function(){
	$("#update-btn").click(function(){
		if($("#name").val().trim() != "" && $("#password").val().trim() != ""){
			$("#join-form").submit();
			return;
		}
		
		alert("이름이나 비밀번호에 공백(\"\")은 사용하실 수 없습니다.\n다시 입력해주세요.");
		return;
	});
})
</script>
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp"/>
		<c:import url ="/WEB-INF/views/includes/navigation.jsp"/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/update">
				<input type="hidden" name="userNo" value="${userVo.userNo}"/>				
					<label class="block-label" for="name">이름 : <input id="name" name="name" type="text" value="${ userVo.name}" style="margin-left:30px;"/> </label>
					<label class="block-label" for="id">아이디 : ${userVo.id} </label>
					<label class="block-label">비밀번호 : <input name="password" type="password" value="" id="password"></label>
					<input type="button" id="update-btn" value="수정">
				</form>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>