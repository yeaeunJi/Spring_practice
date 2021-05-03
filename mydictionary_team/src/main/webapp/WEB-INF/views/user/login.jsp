<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mydictionary</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="${pageContext.request.contextPath }/user/auth">
					<h3>Login</h3>
					<label				
						class="block-label" for="id">아이디</label> <input id="id"
						name="id" type="text" value=""> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">
					<c:if test="${ param.result  == 'fail'}">
					<p>로그인에 실패 했습니다.</p>
					</c:if>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>