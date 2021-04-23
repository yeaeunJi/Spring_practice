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
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"  ></script>
<script>
$(function(){ // 화면 load가 끝날 때 실행되는 이벤트 함수라고 생각하면 됨...?
	
	$("#email-check").change(function(){ // email 입력 변경 이벤트
		$("#img-check").hide();
		$("#btn-check").show();
	});
	
	
	$("#btn-check").click(function(){ // 클릭 이벤트 함수 등록 : 비동기
		let email = $("#email-check").val();
		if (email == '') {
			return; // 이메일 입력하지 않고 버튼을 누르면 통신x
		}
		
		let url = "${pageContext.request.contextPath}/api/user/existemail?email="+email;
		
		$.ajax({
			url : url,
			asyc : true, // 비동기
			data: '',
			dataType: "json",
			success : function(response){
				// String을 자바스크리브 객체로 받음
				if (response.result !='success'){
					console.error(response.message);
					return;
				}

				if (response.data == true) {
					alert("이미 존재하는 이메일입니다. 다른 이메일은 사용해주세요.");
					$("#email-check").val('').focus();
					return;
				} 
				
				alert("사용가능한 email입니다.");
				$("#password").focus();
				$("#img-check").show();
				$("#btn-check").hide();
			},
		error : function(xhr, status, e){
				console.log(e);
		}
			
		});
	
	});
})

</script>
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="join" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email-check" name="email" type="text" value="">
					<img  id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png"  style="width:16px; display:none"   >
					<input type="button" value="id 중복체크" id="btn-check">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="" id="password">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url ="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>