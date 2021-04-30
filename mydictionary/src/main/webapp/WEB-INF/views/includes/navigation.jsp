<!-- 라이브러리 지시자 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }/main/introduce">소개</a></li>
		<li><a href="${pageContext.request.contextPath }/bookmark">북마크</a></li>
		<li><a href="${pageContext.request.contextPath }/dictionary">네이버 지식백과 검색</a></li>
	</ul>
</div>
