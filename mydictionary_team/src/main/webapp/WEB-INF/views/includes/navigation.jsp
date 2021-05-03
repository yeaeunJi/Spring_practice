<!-- 라이브러리 지시자 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }/main/introduce">소개</a></li>
		<li><a href="${pageContext.request.contextPath }/bookmark">내 즐겨찾기</a></li>
		<li><a href="${pageContext.request.contextPath }/dictionary">검색</a></li>
		<li><a href="${pageContext.request.contextPath }/board" >자유게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/oneToOne" >1:1 문의</a></li>
	</ul>
</div>
