<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
<style>
.tbl-ex tr:nth-child(odd) {
background-color : gray;
}

.tbl-ex tr:nth-child(even) {
background-color: #777799;;
}
</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>						
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" begin="0" step="1" varStatus="status"
						var="vo">
						<tr class="board">
							<!-- padding-left:\${(vo.depth-1)*20}px -->
							<td>${page.totalCount - (page.curPage-1)*page.showNum-status.index}</td>
							<td style="text-align: left; padding-left: 0px;"><a
								style="text-align: left; padding-left: 0px;"
								href="${pageContext.request.contextPath }/board2?a=view&no=${vo.no}&keyword=${keyword}" onclick="setHistory(this)">
									<c:if test="${vo.depth >= 1 }">
										<c:forEach begin="1" end="${vo.depth}" step="1">
											<span>&nbsp;</span>
										</c:forEach>
										<img
											src="${pageContext.request.contextPath}/assets/images/reply.png" />
									</c:if> <c:choose>
										<c:when test="${fn:length(vo.title) <= 10}">
									 		${vo.title}
									 	</c:when>
										<c:otherwise>
									 		${fn:substring(vo.title,0,10)}...
									 	</c:otherwise>
									</c:choose>
							</a></td>
							<td>${vo.writer }</td>
							<td>${vo.regDate }</td>
							<td>
							<c:if test="${!empty authUser  && authUser.no == vo.userNo}">
								<a
									href="${pageContext.request.contextPath }/board2?a=delete&no=${vo.no}&keyword=${keyword}"
									class="del">삭제</a>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<c:choose>
							<c:when test="${page.startPage!=1}">
								<li><a
									href="${pageContext.request.contextPath }/board2?a=mulPageBefore&startPage=${page.startPage}&totalPage=${page.total}&keyword=${keyword}">
										◀◀ </a></li>
							</c:when>
							<c:otherwise>
									<li><a href="#">◀◀ </a></li>
							</c:otherwise>
						</c:choose>
					
					
						<c:choose>
							<c:when test="${page.curPage!=1}">
								<li><a
									href="${pageContext.request.contextPath }/board2?a=onePageBefore&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<c:forEach step="1" begin="${page.startPage}" end="${page.endPage}"  var="pageNum"  varStatus="status">
					<li><a class="page"
								href="${pageContext.request.contextPath }/board2?a=movePage&movePage=${pageNum}&keyword=${keyword}">${pageNum}</a></li>
					 
						</c:forEach>
						
						<c:choose>
							<c:when test="${page.curPage!= page.total}">
								<li><a
									href="${pageContext.request.contextPath }/board2?a=onePageNext&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">▶</a></li>
							</c:otherwise>
						</c:choose>


						<c:choose>
							<c:when test="${page.endPage != page.total}">
								<li><a
									href="${pageContext.request.contextPath }/board2?a=mulPageNext&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">
										▶▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">
										▶▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<div class="bottom">
					<c:if test="${!empty authUser }">
						<a href='${pageContext.request.contextPath }/board2?a=writeform'
							id="new-book">글쓰기</a>
					</c:if>
				</div>

				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
<script>
window.onload = function() {
	let msg = '${msg}';
	if (msg != ''){
		alert(msg);
	}
	
	// curPage a태그의 색이 red 로 바뀜
	let a = document.getElementsByClassName("page");
	for(let i = 0; i < a.length; i++){
		let text= a[i].innerHTML;
		if (text == '${page.curPage}'){
			a[i].style.color = "red";
			break;
		}
	}
}
</script>

</html>