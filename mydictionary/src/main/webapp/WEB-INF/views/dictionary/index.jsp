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
						<th></th>
						<th>제목</th>
						<th>요약</th>
						<th>즐겨찾기</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" begin="0" step="1" varStatus="status" var="vo">
						<tr class="board" >
						
							<td style="text-align: left; padding-left: 0px; width:20%;">								
								<img src="${vo.thumbnail }" style="width:100%;" class="thumbnail" />
							</td> 
							<td style="width:20%;  word-break: keep-all;">
									<a	style="text-align: left; padding-left: 0px;" href="${vo.link}" class="link-url" >
									${vo.title}
									</a>
							</td>
							<td style="width:70%;"  >						
								<span class="description">${vo.description }</span>
							</td>
							<td>
								<img src="${pageContext.request.contextPath }/assets/images/emptystar.png" class="bookmark" style="width:20px; height:15px;" 
								 title="즐겨찾기에 추가하기" alt="별모양 북마크 추가버튼" name="emptystar" />	
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
	
	$(".bookmark").click(function(){
		let url = "${pageContext.request.contextPath }/api/bookmark/update";
		let $td = $(this).parent().siblings();
		
		let title  = $td.find(".link-url:first").text().trim();
		let keyword = $("#keyword").val();
		let thumbnail = $td.find(".thumbnail").attr("src");
		let description = $td.find(".description").eq(0).text();
		let link = $td.find(".link-url").attr("href");
		
		let data = {
				"title": title,
				"link": link,
				"keyword": keyword,
				"thumbnail":thumbnail,
				"description": description
		};
		
		//let bookmarkStatus = $(this).attr("name");
		let restType = $(this).attr("name") == "emptystar"?"POST":"DELETE";
		console.log("data.link : "+data.link);
		
		if(restType == "DELETE"){
			url = "${pageContext.request.contextPath }/api/bookmark/update?link="+link;
			data = '';
		}
		
		$.ajax({
			url : encodeURI(url) ,
			asyc : true, // 비동기
			data: data,
			type: restType,
			dataType: "json",
			context: this,
			success : function(response){
				// String을 자바스크리브 객체로 받음
				if (response.result !='success'){
					console.error(response.message);
					console.log("response.result !='success'");
					
					return;
				}

				if(response.data == false){
					console.log("response.data == false");
					return;
				}	
				
				console.log("restType : "+restType);
				
				if ( $(this).attr("name") == "emptystar"){
					console.log("2");
					$(this).attr("src","${pageContext.request.contextPath }/assets/images/fullstar.png" )
											  .attr("title","즐겨찾기에서 삭제하기" )
											  .attr("name","fullstar");
					return;
				}
				
				if ($(this).attr("name") == "fullstar"){
					console.log("1");
					$(this).attr("src","${pageContext.request.contextPath }/assets/images/emptystar.png" )
										     .attr("title","즐겨찾기에 추가하기" )
										 	 .attr("name","emptystar");
					return;
				}
			},
			error : function(xhr, status, e){
				console.log(e);
			}
		}); // ajax
	
		
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