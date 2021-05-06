<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title> <
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/common.css">
	

<script>

	//목록으로 이동 이벤트
	$(document)
			.on(
					'click',
					'#btnList',
					function() {
						location.href = "${pageContext.request.contextPath}/board/getBoardList";
					});

	//수정 버튼 클릭 이벤트
	$(document).on('click', '#btnUpdate', function() {
		var url = "${pageContext.request.contextPath}/board/editForm";
		url = url + "?bid=" + ${boardContent.bid};
		url = url + "&mode=edit";
		location.href = url;
	});

	//삭제 버튼 클릭 이벤트
	$(document).on('click', '#btnDelete', function() {
		var url = "${pageContext.request.contextPath}/board/deleteBoard";
		url = url + "?bid=" + ${boardContent.bid};
		location.href = url;
	});

	//댓글 리스트
	$(document).ready(function() {

		showReplyList();

	});

	function showReplyList() {
		var url = "${pageContext.request.contextPath}/restBoard/getReplyList";

		var paramData = {
			"bid" : "${boardContent.bid}"
		};

		$.ajax({

					type : 'POST',

					url : url,

					data : paramData,

					dataType : 'json',

					success : function(result) {
						var htmls = "";
						
						if (result.data.length < 1) {
							htmls = "등록된 댓글이 없습니다.";

						} else {

							$(result.data)
									.each(
											function() {

												htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';

												htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';

												htmls += '<title>Placeholder</title>';

												htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';

												htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';

												htmls += '</svg>';

												htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';

												htmls += '<span class="d-block">';

												htmls += '<strong class="text-gray-dark">'
														+ this.reg_id
														+ '</strong>';

												htmls += '<span style="padding-left: 7px; font-size: 9pt">';

												htmls += '<a href="javascript:void(0)" onclick="fn_editReply('
														+ this.rid
														+ ', \''
														+ this.reg_id
														+ '\', \''
														+ this.content
														+ '\' )" style="padding-right:5px">수정</a>';

												htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply('
														+ this.rid
														+ ')" >삭제</a>';

												htmls += '</span>';

												htmls += '</span>';

												htmls += this.content;

												htmls += '</p>';

												htmls += '</div>';

											}); //each end

						}

						$("#replyList").html(htmls);

					}, // Ajax success end
					error : function(xhr, status, e) {
						console.error(status + ", " + e);

					}
				}); // Ajax end
	}
	
	//댓글 쓰기
	$(document).on('click', '#btnReplySave', function(){

		var replyContent = $('#content').val();

		var replyReg_id = $('#reg_id').val();



		var paramData = JSON.stringify({"content": replyContent

				, "reg_id": replyReg_id

				, "bid":'${boardContent.bid}'

		});

		

		var headers = {"Content-Type" : "application/json"

				, "X-HTTP-Method-Override" : "POST"};

		

		
	$.ajax({

			url : "${pageContext.request.contextPath}/restBoard/saveReply"
			
			,
			headers : headers

			,
			data : paramData

			,
			type : 'POST'

			,
			dataType : 'text'

			,
			success : function(result) {
				
				showReplyList();

				$('#content').val('');

				$('#reg_id').val('');

			}

			,
			error : function(xhr, status, e) {
				console.error(status + ", " + e);

			}

		});
	});
	
	//댓글 수정
	function fn_editReply(rid, reg_id, content) {

		var htmls = "";

		htmls += '<div class="media text-muted pt-3" id="rid' + rid + '">';

		htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';

		htmls += '<title>Placeholder</title>';

		htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';

		htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';

		htmls += '</svg>';

		htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';

		htmls += '<span class="d-block">';

		htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';

		htmls += '<span style="padding-left: 7px; font-size: 9pt">';

		htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rid
				+ ', \'' + reg_id + '\')" style="padding-right:5px">저장</a>';

		htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';

		htmls += '</span>';

		htmls += '</span>';

		htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';

		htmls += content;

		htmls += '</textarea>';

		htmls += '</p>';

		htmls += '</div>';

		$('#rid' + rid).replaceWith(htmls);

		$('#rid' + rid + ' #editContent').focus();
	}
	
	//댓글 수정후 저장
	function fn_updateReply(rid, reg_id) {

		var replyEditContent = $('#editContent').val();

		var paramData = JSON.stringify({
			"content" : replyEditContent

			,
			"rid" : rid

		});

		var headers = {
			"Content-Type" : "application/json"

			,
			"X-HTTP-Method-Override" : "POST"
		};

		$.ajax({

			url : "${pageContext.request.contextPath}/restBoard/updateReply"

			,
			headers : headers

			,
			data : paramData

			,
			type : 'POST'

			,
			dataType : 'text'

			,
			success : function(result) {

				console.log(result.data);

				showReplyList();

			}

			,
			error : function(error) {

				console.log("에러 : " + error);

			}

		});

	}
	
	//댓글 삭제
	
	function fn_deleteReply(rid) {

		var paramData = {
			"rid" : rid
		};

		$.ajax({
			url : "${pageContext.request.contextPath}/restBoard/deleteReply",
			data : paramData,
			type : 'POST',
			dataType : 'text',
			success : function(result) {
				
				showReplyList();

			}

			,
			error : function(error) {
				console.log("에러 : " + error);

			}

		});
		location.reload();
	}
</script>

</head>
<body>
	<article>
		<div class="container" role="main">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
			<c:import url="/WEB-INF/views/includes/navigation.jsp" />
			<h2>게시글</h2>
			<div class="bg-white rounded shadow-sm">
				<div class="board_title">
					<c:out value="${boardContent.title}" />
				</div>
				<div class="board_info_box">
					<span class="board_author"><c:out
							value="${boardContent.reg_id}" />,</span><span class="board_date"><c:out
							value="${boardContent.reg_dt}" /></span>
				</div>
				<div class="board_content">${boardContent.content}</div>
				<div class="board_tag">
					TAG :
					<c:out value="${boardContent.tag}" />
				</div>
			</div>
			<div style="margin-top: 20px">
				<c:if test="${authUser.id == boardContent.reg_id}">
					<div>
						<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
						<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
						<p>
					</div>
				</c:if>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
			<!-- Reply Form {s} -->
			<div class="my-3 p-3 bg-white rounded shadow-sm"
				style="padding-top: 10px">
				<form:form name="form" id="form" role="form"
					modelAttribute="replyVO" method="post">
					<form:hidden path="bid" id="bid" />
					<div class="row">
						<div class="col-sm-10">
							<form:textarea path="content" id="content" class="form-control"
								rows="3" placeholder="댓글을 입력해 주세요"></form:textarea>
						</div>
						<div class="col-sm-2">
							<form:input path="reg_id" class="form-control" id="reg_id"
								value="${authUser.id }" readonly = "true"></form:input>
							<button type="button" class="btn btn-sm btn-primary"
								id="btnReplySave" style="width: 100%; margin-top: 10px">
								저 장</button>
						</div>
					</div>
				</form:form>
			</div>
			<!-- Reply Form {e} -->
			<!-- Reply List {s}-->
			<div class="my-3 p-3 bg-white rounded shadow-sm"
				style="padding-top: 10px">
				<h6 class="border-bottom pb-2 mb-0">Reply list</h6>
				<div id="replyList"></div>
			</div>
			<!-- Reply List {e}-->
			<c:import url="/WEB-INF/views/includes/footer.jsp" />
		</div>
	</article>
</body>
</html>