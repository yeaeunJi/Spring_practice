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
<style>

</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/bookmark/search" method="post">
					<input type="text" id="keyword" name="keyword" value="${keyword}"> 
					<input type="submit"	value="찾기">
					<h6>* 제목과 내용, 요약, 검색 키워드 정보에서 검색합니다.</h6>
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th></th>
						<th>검색키워드</th>
						<th>요약</th>
						<th>등록일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" begin="0" step="1" varStatus="status" var="vo">
						<tr class="board">
							<td>${page.totalCount - (page.curPage-1)*page.showNum-status.index}</td>
							<td style="width:20%;  word-break: keep-all;">
									<p	style="text-align: left; padding-left: 0px;" class="link-url" >
									${vo.title}
									</p>
							</td>
							<td style="text-align: left; padding-left: 0px; width:20%;">								
								<img src="${vo.thumbnail }" style="width:100%;" class="thumbnail" />
							</td> 
							<td>${vo.keyword }</td>
							<td style="width:70%;"  >	<a href="${vo.link }" class="description">${vo.description }</a>					
							</td>
							<td>${vo.regDate }</td>
							<td class="test">
								<img src="${pageContext.request.contextPath }/assets/images/fullstar.png" class="bookmark" style="width:20px; height:15px;" 
								 title="즐겨찾기에서 삭제하기" alt="별모양 북마크 추가버튼" name="fullstar" onclick="deleteBookmark(this, ${vo.wordNo})" />	
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
									href="${pageContext.request.contextPath }/bookmark/mulPageBefore?startPage=${page.startPage}&totalPage=${page.total}&keyword=${keyword}">
										◀◀ </a></li>
							</c:when>
							<c:otherwise>
									<li><a href="#">◀◀ </a></li>
							</c:otherwise>
						</c:choose>
					
					
						<c:choose>
							<c:when test="${page.curPage!=1}">
								<li><a
									href="${pageContext.request.contextPath }/bookmark/onePageBefore?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<c:forEach step="1" begin="${page.startPage}" end="${page.endPage}"  var="pageNum"  varStatus="status">
					<li><a
								href="${pageContext.request.contextPath }/bookmark/movePage?movePage=${pageNum}&keyword=${keyword}">${pageNum}</a></li>
					 
						</c:forEach>
						
						<c:choose>
							<c:when test="${page.curPage!= page.total}">
								<li><a
									href="${pageContext.request.contextPath }/bookmark/onePageNext?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">▶</a></li>
							</c:otherwise>
						</c:choose>


						<c:choose>
							<c:when test="${page.endPage != page.total}">
								<li><a
									href="${pageContext.request.contextPath }/bookmark/mulPageNext?endPage=${page.endPage}&totalPage=${page.total}&keyword=${keyword}">
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
<!-- 
				<div class="history">
				<h3 style="display:inline-block;"> ** 최근 방문한 게시글 ** </h3>
				<button onclick="removeHistory()">히스토리 지우기</button> 
				<ul  id="historyList" >			
				</ul>
				</div>-->
			</div>
			 
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>

<script>
function deleteBookmark(obj, wordNo){
	let url = "${pageContext.request.contextPath }/api/bookmark/delete";
	$.ajax({
		url : encodeURI(url) ,
		asyc : true, // 비동기
		data: {"wordNo":wordNo},
		type: "POST",
		dataType: "json",
		context: obj,
		success : function(response){
			// String을 자바스크리브 객체로 받음
			if (response.result !='success'){
				console.error(response.message);
				return;
			}

			if(response.data == false){
				return;
			}	
			
			$(obj).parent().parent().remove();
		},
		error : function(xhr, status, e){
			console.log(e);
		}
	}); // ajax
}

window.onload = function() {	
	let a = document.getElementsByClassName("page");
	for(let i = 0; i < a.length; i++){
		let text= a[i].innerHTML;
		if (text == '${page.curPage}'){
			a[i].style.color = "red";
			break;
		}
	}
	
	$(".bookmark").hover(function(){
		 $(".bookmark").css('cursor','pointer');
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