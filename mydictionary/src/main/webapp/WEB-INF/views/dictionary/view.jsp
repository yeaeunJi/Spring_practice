<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mydictionary</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<c:if test="${vo.link != ''}">
					<tr>
						<td class="label"><img src="${vo.link}" name="thumbnail"/></td>
					</tr>
					</c:if>
					<tr>
						<td class="label">요약</td>
						<td> ${vo.description } </td>
					</tr>
					<c:if test="${!empty authUser }">
					<tr>
						<td>즐겨찾기 추가 : <input type="button" value="☆" id="bookmark-btn"></td> 
					</tr>  
					</c:if>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.contents, newline, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/dictionary">글목록</a>
					
				</div>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>