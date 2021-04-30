<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
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
					<pre><b>[프로젝트명] My Dictionary
					
[기획] wis 

[개발] angela

[소개] 
  Jsp/Servlet 기반 웹 애플리케이션으로 네이버 지식 백과 API를 이용한 단어 검색하고,  
  검색한 단어를 저장할 수 있는 즐겨찾기 기능을 제공합니다.

[기능]
  (1) 네이버 지식 백과(https://terms.naver.com/)에서 제공하는 정보를 open API를 통해 검색
  (2) 즐겨찾기(검색, 추가, 삭제)
  (3) 회원가입, 로그인, 회원정보 수정
  
[사용된 언어/프레임워크/라이브러리/tools]
  (1) 개발 환경 : Windows10, local(on Promise : 모든 것이 한 컴퓨터에 설치된 상태), Chrome 90.0 버전에서 테스트
  (2) Front-end : JQuery, JavaScript, css3, HTML5, Bootstrap
  (3) Back-end : Java 1.8, ApacheTomcat 8.5, Srping4.3, MySql 8.0, mybatis 3.2.2/spring mybatis 1.2.0
  (4) tools : 이클립스, Maven, Github, JUnit(Spring JUnit)
  (5) open API : 네이버 지식 백과 API
</b></pre>
</div>
			</div>
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		</div>
			
</body>
