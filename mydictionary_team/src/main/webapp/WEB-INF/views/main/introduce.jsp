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
					<pre><b>[프로젝트명] My Dictionary
					
[기획/개발] 김현우, 지예은, 이갑성

[용도] 데이터 검색, 단어장, 출처, 즐겨찾기, 커뮤니티

[소개] 
  Mydictionary는 네이버 지식 백과사전 검색 및 자신의 즐겨찾기를 만들 수 있는 사이트입니다.
  Jsp/Servlet 기반 웹 애플리케이션으로 네이버 지식 백과 API를 이용한 단어 검색하고,  
  검색한 단어를 저장할 수 있는 즐겨찾기 기능을 제공합니다.
  + 2021.05.03 이후 1:1문의 기능, 자유 게시판 기능이 추가되었습니다. 
  
  ※ 추후 추가하고 싶은 기능 
  자신이 만든 즐겨찾기를 excel파일로 export하여 논문 출처에 사용 및 타인과 공유할 수 있는 기능, 
  자신이 만든 즐겨찾기를 게시판 등을 통해 공유하는 기능
  즐겨찾기 평가를 통해 많은 사용자들이 찾는 즐겨찾기 랭킹 제공 기능	
  
[현재 구현된 기능]
	1. 네이버 지식 백과 API 연동(검색)  
	2. 즐겨찾기(DB 저장, 검색, 추가/삭제)  
	3. 회원가입, 로그인	
	4. 자유 게시판(회원만 사용가능)
	5. 1:1 문의(회원만 사용 가능, 관리자만 답장 가능)
	
	※ 즐겨찾기, 네이버 검색, 자유 게시판 작성, 1:1문의는 로그인 후에 사용 가능합니다.  
  
[사용된 언어/프레임워크/라이브러리/tools]
  (1) 개발 환경 : Windows10, local(on Promise : 모든 것이 한 컴퓨터에 설치된 상태), Chrome 90.0 버전에서 테스트
  (2) Front-end : JQuery, JavaScript, css3, HTML5, Bootstrap
  (3) Back-end : Java 1.8, ApacheTomcat 8.5, Srping4.3, MySql 8.0, mybatis 3.2.2/spring mybatis 1.2.0
  </b></pre>
    <img src="${pageContext.request.contextPath }/assets/images/ERD.png" title="mysql erd"  style="padding-left : 300px;"/>
    <p>***** 위에 작성된 ERD는 추후 수정 예정 *****  </p>
  <pre><b>
  (4) tools : 이클립스, Maven, Github, JUnit(Spring JUnit)
 	※ Github url : <a href="https://github.com/yeaeunJi/Spring_practice/tree/master/mydictionary">https://github.com/yeaeunJi/Spring_practice/tree/master/mydictionary_team</a> 
  (5) open API : 네이버 지식 백과 API</b>
</pre>
</div>
			</div>
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		</div>
			
</body>
