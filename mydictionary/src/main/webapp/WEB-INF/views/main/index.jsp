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
						src="${pageContext.request.contextPath }/assets/images/images.jpeg" style="width:40%; height:40%; margin-left:350px;">
					<h2 style="margin-left:300px;">안녕하세요. 지예은의 mydictionary에 오신 것을 환영합니다.</h2>
					<pre><b>[프로젝트명] My Dictionary
					
[소개] 
	  Jsp/Servlet 기반 웹 애플리케이션으로 네이버 지식 백과 API를 이용한 단어 검색하고,  
      검색한 단어를 저장할 수 있는 즐겨찾기 기능을 제공합니다.

[사용된 언어/프레임워크/라이브러리/tools]
  (1) 개발 환경 : Windows10, local(on Promise : 모든 것이 한 컴퓨터에 설치된 상태), Chrome 90.0 버전에서 테스트
  (2) Front-end : Jquery, Javascript, css3, HTML5, Bootstap
  (3) Back-end : Java 1.8, Apache Tomcat 8.5, Srping4.3, Mysql 8.0
  (4) tools : 이클립스 IDE, Maven, Github;

[기능]
  (1) 네이버 지식 백과 검색
  (2) 즐겨찾기(검색, 추가, 삭제)
  (3) 회원가입, 로그인, 회원정보 수정</b></pre>
				</div>
			</div>
		</div>
		<div id="wrapper">
			<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		</div>
		</div>
</body>
