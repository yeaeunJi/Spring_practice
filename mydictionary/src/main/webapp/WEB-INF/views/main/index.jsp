<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mydictionary</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile"
						src="${pageContext.request.contextPath }/assets/images/images.jpeg">
					<h2>안녕하세요. 지예은의 mydictionary에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br> 메뉴는 사이트 소개, 네이버 지식백과 검색, 즐겨찾기 기능이 있구요.
						Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트
						입니다.<br>
					</p>
				</div>
			</div>
		</div>
		<div id="wrapper">
			<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		</div>
</body>
</html>