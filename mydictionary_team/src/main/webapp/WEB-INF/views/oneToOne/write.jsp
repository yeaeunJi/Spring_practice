<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset='utf-8'>
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap&subset=korean" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap&subset=korean" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/oneToOneWrite.css" type = "text/css" rel = "stylesheet">
  
    <script src="${pageContext.servletContext.contextPath}/assets/ckeditor/ckeditor.js"></script>
    <script>
        function complete(){
            
            alert('글이 등록되었습니다.');
            
        }

    </script>

    <title> 1:1 문의/건의 </title>
</head>
<body style="background:#edf1f8; margin-top:3rem;">
    <header class = "positionHead">
        <table>
            <tr>
                <td colspan="2" style="width:300px;height:100px;font-size:60px;font-family: 'Jua', sans-serif"><a title="메인페이지로 가기" href="${pageContext.servletContext.contextPath }" style="text-decoration:none;">Bit민원24</a></td>

                <td width="600" style="font-size:30px;font-family: 'Jua', sans-serif">만사소통 모두에 의한, 모두를 위한, 모든 소통</td>
                <td style="width:250px;font-size:15px;text-align:right; height:80px;font-family:'Noto Sans KR', sans-serif;">
                    	<c:choose>
							<c:when test="${empty authUser }">
								<a id = "up" href="${pageContext.request.contextPath }/login" title="마이페이지 이동">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a id = "up" href="${pageContext.servletContext.contextPath}/createId" title="로그아웃 하기">회원가입</a>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<a id = "up" href="${pageContext.request.contextPath }/user/update">회원정보수정</a> &nbsp;&nbsp;&nbsp;
								<a id = "up" href="${pageContext.request.contextPath }/user/logout">로그아웃</a> <br/><br/>
								${authUser.name }님 안녕하세요 ^^;
							</c:otherwise>
						</c:choose>                    	
                    </td>
            </tr>
        </table>
	<nav>
	    <ul id="ulStyle" style="margin-left:27rem;">
	        <li id="liStyle" title="1:1 문의/건의" style="margin-left: -30px;"><a href="${pageContext.servletContext.contextPath}/oneToOne">1:1 문의/건의</a></li>
	        <li id="liStyle" title="강사/매니저 평가" style="margin-left: 100px;"><a href="${pageContext.servletContext.contextPath}/staffEval">강사/매니저 평가</a></li>
	        <!-- <li id="liStyle" title="소통 게시판" style="margin-left: 100px;"><a href="${pageContext.servletContext.contextPath}/community">소통 게시판</a></li> -->
	    </ul>
	</nav>
    <div class="body" style="margin-left:20px;">
        <div class="body_border">
            <div class="form_div">
                <h3 id="h3"> <span class="font_dohyun"> 1:1 문의/건의 </span><img src="${pageContext.servletContext.contextPath}/assets/img/pencil.png" width="25" height="25"> </h3>
                <hr>
    <form action="${pageContext.request.contextPath }/oneToOne/write" method="post" name="sub1">
                <div class="title_line">
                <label><span class="font_jua" style="margin-right:7.5px;"> 제목 </span><input type="text" placeholder="제목을 입력하세요" id="title_text" name="title"></label>
                </div>
                <div class="main_line">
                    <label>
                        <div id="main_text_word"><span class="font_jua">본문</span></div>
                        <div id="main_text"> <textarea name="content" id="editor1"></textarea>
                            <script> CKEDITOR.replace('editor1'); </script>
                        </div>
                    </label>
                </div>
                <div class="file_line">                
                </div>
                <hr>
                <div class="final_line">
                    <input type="submit" id="write_button" name="write" value="글쓰기" onclick="location.href = '${pageContext.request.contextPath }/oneToOne/write'">
                    <input type="button" id="list_button" name="cancel" value="글목록">
                </div>
    </form>
            </div>
        </div>
    </div>
</body>
</html>
