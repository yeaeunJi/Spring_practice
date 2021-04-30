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
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"	action="${pageContext.request.contextPath }/dictionary/search" method="post">
					<input type="hidden" id="showNum" name="showNum" value="${page.showNum}"/>
					<input type="hidden" id="startPage" name="startPage" value="${page.startPage}"/>
					<input type="hidden" id="startRow" name="startRow" value="1"/>
					<input type="text" id="keyword" name="keyword" value="${page.keyword}" placeholder=" ༼๑⁰⊖⁰๑༽ 오늘도 화이팅!" >
					<input type="button" value="찾기" id="search-btn" />
					<h3 style="clear:both;">* 네이버  지식백과에서 검색합니다. 검색어를 입력해주세요.</h3>
				</form>
				<p style="text-align: right;">총 검색 결과 : ${page.totalRow}건</p>
					<table class="tbl-ex">
						<tr>
							<th style="width:100px;">번호</th>
							<th style="width:110px;">사진</th>
							<th style="width:200px;">제목</th>
							<th style="width:600px;" >요약</th>
							<th style="width:30px;"></th>
						</tr>
						<c:forEach items="${list}" begin="0" step="1"  varStatus="status" var="vo">
							<tr class="board" >
								<td style="width:100px;">${page.totalRow - (page.curPage-1)*page.showNum-status.index}</td>
								<td style="text-align: center; padding-left: 0px; width:110px;">								
									<img src="${vo.thumbnail }" style="width:100px;" class="thumbnail"  />
								</td> 
								<td style="width:200px;  word-break: keep-all; ">
										<a	style="text-align: left; padding-left: 0px;" href="${vo.link}" class="link-url" target="_blank" >
										${vo.title}
										</a>
								</td>
								<td style="width:600px;"  >						
									<span class="description">${vo.description }</span>
								</td>
								<td  >
									<c:choose>
										<c:when test="${vo.bookmarkFlag == true}">
											<img src="${pageContext.request.contextPath }/assets/images/fullstar.png" class="bookmark"style="width:30px; height:30px;"
											 title="즐겨찾기에서 삭제하기" alt="별모양 북마크 삭제버튼" name="fullstar" />	
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath }/assets/images/emptystar.png" class="bookmark" style="width:30px; height:30px;"
											 title="즐겨찾기에 추가하기" alt="별모양 북마크 추가버튼" name="emptystar" />	
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
			
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${page.curPage!=1}">
								<li><a
									href="${pageContext.request.contextPath }/dictionary/search/onePagePrev?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<c:forEach step="1" begin="${page.startPage}" end="${page.endPage}"  var="pageNum"  varStatus="status">
					<li><a	href="${pageContext.request.contextPath }/dictionary/search/selectPage?selectPage=${pageNum}&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}" class="page">${pageNum}</a></li>
					 
						</c:forEach>
						
						<c:choose>
							<c:when test="${page.curPage!= page.totalPage}">
								<li>
								<a href="${pageContext.request.contextPath }/dictionary/search/onePageNext?curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}">▶</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
								<a href="#">▶</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
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
		let url = "${pageContext.request.contextPath }/api/bookmark/insert";
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
		
		let bookmarkStatus = $(this).attr("name");
		if(bookmarkStatus == "fullstar"){
			url = "${pageContext.request.contextPath }/api/bookmark/delete";
			data = {
					"link": link,
			};
		}
		$.ajax({
			url : encodeURI(url) ,
			asyc : true, // 비동기
			data: data,
			type: "POST",
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
				
				if ( $(this).attr("name") == "emptystar"){
					$(this).attr("src","${pageContext.request.contextPath }/assets/images/fullstar.png" )
											  .attr("title","즐겨찾기에서 삭제하기" )
											  .attr("name","fullstar");
					return;
				}
				
				if ($(this).attr("name") == "fullstar"){
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