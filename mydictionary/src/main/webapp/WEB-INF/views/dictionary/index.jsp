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
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"  ></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<style>

</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/dictionary/search" method="post">
					<input type="hidden" id="display" name="display" value="${page.showNum}"/>
					<input type="hidden" id="start" name="start" value="${page.start}"/>
					<input type="text" id="keyword" name="keyword" value="${keyword}"> 
					<input type="button"	value="찾기" id="search-btn"/>
					<h6>* 제목과 내용, 요약, 검색 키워드 정보에서 검색합니다.</h6>
				</form>
				<table class="tbl-ex">
					<tr>
						<!--  <th></th>-->
						<th>제목</th>
						<th>요약</th>
						<th>즐겨찾기</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" begin="0" step="1" varStatus="status" var="vo">
						<tr class="board" >
						<!-- 
							<td style="text-align: left; padding-left: 0px; width:20%;">								
									<c:if test="${vo.thumbnail != '' }">
										<img src="${vo.thumbnail }" style="width:100%;" />
									</c:if> 
							</td> -->
							<td style="width:20%;  word-break: keep-all;">
									<a	style="text-align: left; padding-left: 0px;" href="${pageContext.request.contextPath }/dictionary/view?link=${vo.link}" >
									${vo.title}
							</a></td>
							<td style="width:70%;">						
									${vo.description }
							</td>
							<td>
							<img src="${pageContext.request.contextPath }/assets/images/emptystar.png" id="bookmark-btn" style="width:20px; height:15px;" 
							 title="즐겨찾기에 추가하기" alter="별모양 북마크 추가버튼"/>	
							<!-- 	<a href="${pageContext.request.contextPath }/Bookmark/update?link=${vo.link}" style="font-size:20px; width:30px;'">☆</a> -->
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
									href="${pageContext.request.contextPath }/dictionary/mulPageBefore?startPage=${page.startPage}&totalPage=${page.total}&keyword=${keyword}">
										◀◀ </a></li>
							</c:when>
							<c:otherwise>
									<li><a href="#">◀◀ </a></li>
							</c:otherwise>
						</c:choose>
					
					
						<c:choose>
							<c:when test="${page.curPage!=1}">
								<li><a
									href="${pageContext.request.contextPath }/dictionary/onePageBefore?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<c:forEach step="1" begin="${page.startPage}" end="${page.endPage}"  var="pageNum"  varStatus="status">
					<li><a
								href="${pageContext.request.contextPath }/dictionary/movePage?movePage=${pageNum}&keyword=${keyword}">${pageNum}</a></li>
					 
						</c:forEach>
						
						<c:choose>
							<c:when test="${page.curPage!= page.total}">
								<li><a
									href="${pageContext.request.contextPath }/dictionary/onePageNext?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">▶</a></li>
							</c:otherwise>
						</c:choose>


						<c:choose>
							<c:when test="${page.endPage != page.total}">
								<li><a
									href="${pageContext.request.contextPath }/dictionary/mulPageNext?endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">
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
				</div>
			</div>
			 
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>

<script>
window.onload = function() {	
	// curPage a태그의 색이 red 로 바뀜
	let a = document.getElementsByClassName("page");
	for(let i = 0; i < a.length; i++){
		let text= a[i].innerHTML;
		if (text == '${page.curPage}'){
			a[i].style.color = "red";
			break;
		}
	}
	
	$("#bookmark-btn").hover(function(){
		 $("#bookmark-btn").css('cursor','pointer');
	});
	
	$("#bookmark-btn").click(function(){
		//ajax  통신 결과가 success나오면
		//별모양으로 변경
		
	});
	
	$("#search-btn").click(function(){
		let keyword = $("#keyword").val();
		if (keyword.trim() == '') {
			alert("검색할 단어를 입력해주세요.");
			$("#keyword").focus();
			return; 
		} 
		search_form.submit();	
	
	}); // $("search-btn").click
}
</script>
</html>