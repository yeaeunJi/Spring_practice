<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
			<div id="content">
				<div id="site-introduction">
						<h2 >안녕하세요. mydictionary에 오신 것을 환영합니다.</h2>
					<img id="profile"
						src="${pageContext.request.contextPath }/assets/images/images.jpeg">
					</div>
			</div>
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		</div>
</body>
