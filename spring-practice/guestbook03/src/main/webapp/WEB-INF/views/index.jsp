<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/add" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="contents" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>


	<c:forEach items="${list }" var="vo" varStatus="status">
		<table width=510 border=1>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>등록일</th>
			</tr>
			<tr>
				<td align=center>[${ fn:length(list) - status.index}]</td>
				<!-- no을 그대로 뿌리는 것이 아니라 조회된 코멘트 수 기준 -->
				<td>${vo.name}</td>
				<td>${vo.regDate}</td>
				<td align=center><a
					href="${pageContext.request.contextPath }/deleteform?no=${vo.no}">삭제</a></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan=4>${fn:replace(vo.contents, newline,"<br>") }</td>
				<!-- 개행\n이 유지된 채로 나오도록 -->
			</tr>
		</table>
	</c:forEach>

</body>
</html>