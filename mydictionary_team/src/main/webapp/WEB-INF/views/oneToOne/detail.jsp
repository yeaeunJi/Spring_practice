<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            Bit민원24
        </title>
        <link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Jua&display=swap&subset=korean" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap&subset=korean" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/oneToOneDetail.css" type = "text/css" rel = "stylesheet">

    </head>
    <body style="background:#edf1f8; margin-top:3rem;">
        <header class = "positionHead">
            <table>
                <tr>
                    <td colspan="2" style="width:300px;height:100px;font-size:60px;font-family: 'Jua', sans-serif"><a title="메인페이지로 가기" href="index.html" style="text-decoration:none;">Bit민원24</a></td>
    
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
        <div style="margin-left:140px; border: 1px solid white; width:937px; background: white">
            <table>
                <tr><td style="font-size:30px; font-weight: bold; color:#3493ff;">1:1 문의/건의</td></tr>
                <tr><td style="height:30px;">&nbsp;</td></tr>
                <tr><td style="font-weight: bold; font-size:25px;">${vo.title }</td></tr>
                <tr><td style="border-top:2px solid #2e4361; background:#f1f7ff; padding:10px 0px 10px 0px;">&nbsp;닉네임 : <span style="font-size:13px;">${vo.writer }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;작성일 : <span style="font-size:13px; margin-left:1;">${vo.regdate }</span>&nbsp;&nbsp;</td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr name = "contents">
                    <td>
                       	${fn:replace(vo.content, newline, "<br/>") }
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td></tr>
            </table>

            
            <div style="height:40px;">&nbsp;</div>
            <hr style="border: solid 0.5px gray; width:900px;">         
            
            <table style="margin:18px; width:900px;">
                <tr><td style="background:#f1f1f1; font-size:13px; padding:10px 0 10px 0;">지나가던웹개발자</td></tr>
                <tr><td style="padding:10px 0 10px 10px; font-size:15px;">토비의 스프링</td></tr>
              
            </table>
            
            <table style="margin:30px;">
                <tr>
                    <td>
                        <textarea cols="90" rows="5" onclick="this.value=''" style="width:48rem;">댓글을 남겨주세요.</textarea>
                    </td>
                    <td><input type="submit" value="댓글남기기" id="writeCommentBtn"></td>
                </tr>
            </table>

        </div>

        <footer class="positionBody" >
            <hr>
            <p style="font-size:15px;color:gray;">
                <span style="margin-left:6%;">상호: Bit민원24&nbsp;&nbsp;&nbsp;대표: 이갑성&nbsp;&nbsp;&nbsp;주소: 서울특별시 서초구 서초대로74길33 비트빌3층&nbsp;&nbsp;&nbsp;전화번호: 010-1234-5678&nbsp;&nbsp;&nbsp;&nbsp;대표메일: kabsung3@naver.com<p>
                <span style="margin-left:31%; color:gray;">CORYRIGHT DOCKINGJOB 2021 ALL RIGHTS RESESRVED</span>
            </p>
        </footer>
    </body>

</html>
