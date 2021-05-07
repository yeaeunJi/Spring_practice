<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mydictionary</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"  ></script>

<script>
function deleteBookmark(obj, wordNo, bmkListNo){
	let url = "${pageContext.request.contextPath }/bookmark/delete?wordNo="+wordNo
			+"&bmkListNo="+ $("#selectBookmarkList").children("option:selected").val() +"&keyword="+$("#keyword").val().trim();
	$(location).attr('href',url);
}

function changeBmkList(){
	let bmkListNo = $("#selectBookmarkList").children("option:selected").val();
	if(bmkListNo == "none") return;
	let url = "${pageContext.request.contextPath }/bookmark/searchBmkList";
	url += "?selectCondition="+$("select[name=selectCondition]").children("option:selected").val();
	url += "&bmkListNo="+bmkListNo;
	$(location).attr('href',url);
}

function getBmkList(){
	let url = "${pageContext.request.contextPath }/api/bookmark/getBmkLists";
	$.ajax({
		url : encodeURI(url) ,
		asyc : true, // 비동기
		data: '',
		type: "GET",
		dataType: "json",
		context: this,
		success : function(response){
			
			if (response.result !='success'){
				console.error(response.message);
				return;
			}
			
			if(response.data == false){
				return;
			}	
			
			let list = response.data;
			$("#selectBookmarkList").find(".bookmarkListTitle").remove();
			

			for(var i=0; i <list.length  ; i++ ){
				let newList = ' <option class="bookmarkListTitle"  value="'+list[i].bmkListNo+'"> '+
							list[i].title+'('+list[i].wordCount+') </option>';
				$("#selectBookmarkList").eq(0).append(newList);
			}
			
			$("select[id=selectBookmarkList]").val('${bmkListNo}').prop("selected", true);
			
			
		},
		error : function(xhr, status, e){
			console.error(e);
			$("#bmkTitle").focus();
		}
	}); // ajax
}

$(function(){
	$("select[name='selectCondition']").val("${page.selectCondition}").prop("selected", true);
	
	let a = document.getElementsByClassName("page");
	for(let i = 0; i < a.length; i++){
		let text= a[i].innerHTML;
		if (text == '${page.curPage}'){
			a[i].style.color = "red";
			break;
		}
	}
	
	getBmkList();
	
	$(".bookmark").hover(function(){
		 $(".bookmark").css('cursor','pointer');
	});
	
	
	
	$("#moveBookmarksToOther").click(function(){
		let wordNoList = [];
		$(".check:checked").each(function(){
			wordNoList.push($(this).val());
		});
		
		if(wordNoList.length == 0) return;
		
		let bmkListNo = $("input[class='selectedBmkList']:checked").val();
		let curBmkListNo = $("#selectBookmarkList").children("option:selected").val();
		let data = { "wordNoList" : wordNoList, "bmkListNo" : bmkListNo, "curBmkListNo":curBmkListNo };
		let url = "${pageContext.request.contextPath }/api/bookmark/moveToOther";
		
		$.ajax({
			url : encodeURI(url) ,
			asyc : true, // 비동기
			data: data,
			type: "POST",
			dataType: "json",
			success : function(response){
				if (response.result !='success'){
					console.error(response.message);
					return;
				}

				if(response.data == false){
					console.error("즐겨찾기 이동 실패")
					return;
				}	
				
				location.reload();
				
			},
			error : function(xhr, status, e){
				console.error(e);
				console.error(status);
			}
		}); // ajax
	});
	$("#allCheck").click(function(){
		if($("#allCheck").prop("checked") == true){
			$("input:checkbox[class=check]").prop("checked", true);
			$("#selectBmkList-btn").prop('disabled', false);
		}else {
			$("input:checkbox[class=check]").prop("checked", false);
			$("#selectBmkList-btn").prop('disabled', true);
		}
	}); // allCheck click event
	
	$(".check").click(function(){
		if($(this).prop("checked") == false ){
			$("#allCheck").prop("checked", false);
		}
		
		if ($("input:checkbox[class=check]:checked").length > 0){
		 $("#selectBmkList-btn").prop('disabled', false);
		} else {
			$("#selectBmkList-btn").prop('disabled', true);
		}
		
	}); // row check click event
	

	
	$("#addBmkList-btn").click(function(){
		let title  = $("#bmkTitle").val().trim();
		if(title == '') {
			alert("공백('')은 즐겨찾기 이름으로 사용할 수 없습니다.");
			$("#bmkTitle").focus();
			return;
		}
		
		let url = "${pageContext.request.contextPath }/api/bookmark/insertBmkList?title="+title;
		$.ajax({
			url : encodeURI(url) ,
			asyc : true, // 비동기
			data: '',
			type: "GET",
			dataType: "json",
			context: this,
			success : function(response){
				
				if (response.result !='success'){
					alert(response.message);
					console.error(response.message);
					return;
				}
				
			
				let newList = ' <li class="bookmarkTitle"><input type="radio" name="title" value="'+response.data.bmkListNo+'" checked="checked" class="selectedBmkList"> '+response.data.title+'(0) </li>';
				$("#selectBmkListToMove").find(".bookmarkTitle").last().append(newList);
				$("#bmkTitle").val('');
				
			},
			error : function(xhr, status, e){
				alert(e);
				console.error(e);
				$("#bmkTitle").focus();
			}
		}); // ajax
	
	});
	
	$("#selectBmkList-btn").click(function(){
		let url = "${pageContext.request.contextPath }/api/bookmark/getBmkLists";
		$.ajax({
			url : encodeURI(url) ,
			asyc : true, // 비동기
			data: '',
			type: "GET",
			dataType: "json",
			context: this,
			success : function(response){
				
				if (response.result !='success'){
					alert(response.message);
					console.error(response.message);
					return;
				}
				
				if(response.data == false){
					return;
				}	
				
				let list = response.data;
				$("#selectBmkListToMove").find(".bookmarkTitle").remove();
				
				for(var i=0; i <list.length  ; i++ ){
					//let idx = list.length -1 - i;
					let newList = ' <li class="bookmarkTitle"><input type="radio" name="title" value="'+list[i].bmkListNo+'" style="display:hidden;" class="selectedBmkList"> '+list[i].title+'('+list[i].wordCount+') </li>';
					$("#selectBmkListToMove").children().eq(0).prepend(newList);
				}
				
					
			},
			error : function(xhr, status, e){
				console.error(e);
				$("#bmkTitle").focus();
			}
		}); // ajax
	});
	
	
	
	
});

</script>
<style>
input[name='title'] : checked{
	font-weight:  bold;
	background-color : gray;

}

</style>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<div id="content" style="margin-left: 20rem; margin-top: 1rem;">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/bookmark/search" method="post">
					<select name="selectCondition">
						<option value="total" selected="selected">전체</option>
						<option value="keyword">검색키워드</option>
						<option value="contents">제목+요약</option>
					</select>
					<input type="text" id="keyword" name="keyword" value="${page.keyword}" placeholder="(ꐦ ◣‸◢) 공부!!! "> 
					<input type="submit"	value="찾기"> <br /> <br />
					<h3>* 즐겨찾기 카테고리 전체에서 검색합니다.</h3>
				</form>
				
					<select id="selectBookmarkList" onchange="changeBmkList()"  style="width:200px; float:right; margin-left : 10px;"  >
						<option value='-1' id="allBmkList">즐겨찾기 카테고리 전체</option>
					</select>
				 
					<div class="btn-group" style="padding-left:800px; display:inline-block; float:left;">
					  <button class="btn btn-secondary btn-sm dropdown-toggle" id="selectBmkList-btn"
					  type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" disabled="disabled">
					    이동
					  </button>
					<p style="text-align:right;">총 검색 수 : <span id="totalRow">${page.totalRow}</span>건</p>
					  <ul class="dropdown-menu" style="width:200px; background-color:white; float: right;" id="selectBmkListToMove">
					    <li><input type="text" id="bmkTitle" value="" style="margin-left:10px; width:130px; " placeholder="새 즐겨찾기"/>
							<input type="button" value="추가" id="addBmkList-btn" style=" width:40px; height:30px; font-size:10pt; padding:0; margin-right:10px;"/></li>
					    <li><hr class="dropdown-divider"></li>
						<li><input class="dropdown-item" type="button" value="해당 카테고리로 옮기기" id='moveBookmarksToOther'  
							style="width:180px; height:30px; font-size:10pt; padding:0; text-align: center; margin-left:10px;  margin-right:20px;"/></li>
					  </ul>
					</div>
					
				
				<table class="tbl-ex">
					<tr>
						<th>번호 <input type="checkbox" class="checkMove" id='allCheck'/></th>
						<th>사진</th>
						<th>제목</th>
						<th>요약</th>
						<th>검색키워드</th>
						<th>카테고리</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" begin="0" step="1" varStatus="status" var="vo">
						<tr class="board">
							
							<td style="width:100px; text-align:center;">${page.totalRow - (page.curPage-1)*page.showNum-status.index}
													<input type="checkbox" class="check"  value="${vo.wordNo }" />
							</td>
							
							<td style="text-align: left; padding-left: 0px; width:110px;;">								
								<img src="${vo.thumbnail }" style="width:100px;" class="thumbnail" />
							</td> 
							<td style=" width:200px;  word-break: keep-all;">
									<p	style="text-align: left; padding-left: 0px; " class="link-url" >
									${vo.title}
									</p>
							</td>
							<td style="width:400px;"  >	<a href="${vo.link }" class="description" target="_blank">${vo.description }</a>					
							</td>
							<td style="width:100px;">${vo.keyword }</td>
							<td  style="width:150px;" >${vo.bmkListName }</td>
							<td class="test">
								
								<img src="${pageContext.request.contextPath }/assets/images/fullstar.png" class="bookmark" style="width:30px; height:30px;" 
								 title="즐겨찾기에서 삭제하기" alt="별모양 북마크 추가버튼" name="fullstar" onclick="deleteBookmark(this, ${vo.wordNo})" />	
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
									href="${pageContext.request.contextPath }/bookmark/onePagePrev?selectCondition=${page.selectCondition}&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}&bmkListNo=${bmkListNo}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<c:forEach step="1" begin="${page.startPage}" end="${page.endPage}"  var="pageNum"  varStatus="status">
					<li><a	href="${pageContext.request.contextPath }/bookmark/selectPage?selectCondition=${page.selectCondition}&selectPage=${pageNum}&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}&bmkListNo=${bmkListNo}" class="page">${pageNum}</a></li>
					 
						</c:forEach>
						
						<c:choose>
							<c:when test="${page.curPage!= page.totalPage}">
								<li>
								<a href="${pageContext.request.contextPath }/bookmark/onePageNext?selectCondition=${page.selectCondition}&curPage=${page.curPage}&startPage=${page.startPage}&endPage=${page.endPage}&totalRow=${page.totalRow}&totalPage=${page.totalPage}&keyword=${page.keyword}&bmkListNo=${bmkListNo}">▶</a>
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
</html>