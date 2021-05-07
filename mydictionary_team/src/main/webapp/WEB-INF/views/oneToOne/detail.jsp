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
		<script language="javascript">
			//댓글 수정 폼 보여주는 자바스크립트 함수
			function view(str) {
			 var obj = document.getElementById(str);
			
			 if (obj.style.display=="")
			  obj.style.display="none";
			 else
			  obj.style.display="";
			}
		
		</script>
    </head>
    <body style="background:#ffffff; margin-top:3rem;">       
        <header class = "positionHead">
            <table>
                <tr>
                    <td width="600" style="width:40rem"><a href="${pageContext.request.contextPath }"><img id="logo" src="${pageContext.request.contextPath }/assets/images/text-1619752983255.png" style="margin-right:25rem; margin-left:28rem"/></a></td>
                </tr>
                <tr>    
                    <td style="width:250px;font-size:15px;text-align:right; font-family:'Noto Sans KR', sans-serif;">
                    	<c:choose>
							<c:when test="${empty authUser }">
								<a id = "up" href="${pageContext.request.contextPath }/login" title="마이페이지 이동">♬로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a id = "up" href="${pageContext.servletContext.contextPath}/createId" title="로그아웃 하기">♩회원가입</a>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								${authUser.name }님 안녕하세요 :)&nbsp;
								<a id = "up" href="${pageContext.request.contextPath }/user/logout">♪♪로그아웃</a> 
								<a id = "up" href="${pageContext.request.contextPath }/user/update">♪회원정보수정</a> &nbsp;&nbsp;&nbsp;
								
							</c:otherwise>
						</c:choose>                    	
                    </td>
                </tr>
            </table>
            <nav>
	            <ul id="ulStyle" style="margin-left:18rem; width:60rem">
	                <li id="liStyle" title="1:1 문의/건의" style="margin-left: -30px; font-size:20px;"><a href="${pageContext.request.contextPath }/main/introduce">소개</a></li>
	                <li id="liStyle" title="강사/매니저 평가" style="margin-left: 100px; font-size:20px;"><a href="${pageContext.request.contextPath }/bookmark">내 즐겨찾기</a></li>
	                <li id="liStyle" title="소통 게시판" style="margin-left: 100px; font-size:20px;"><a href="${pageContext.request.contextPath }/dictionary">검색</a></li>
	                <li id="liStyle" title="소통 게시판" style="margin-left: 100px; font-size:20px;"><a href="${pageContext.request.contextPath }/board" >자유게시판</a></li>
	                <li id="liStyle" title="소통 게시판" style="margin-left: 100px; font-size:20px;"><a href="${pageContext.request.contextPath }/oneToOne" >1:1 문의</a></li> 
	            </ul>
	        </nav>
        </header>
        <div style="margin-left:33rem; border: 1px solid white; width:937px; background: aliceblue">
            <table style="width:58.5rem;">
                <tr >
                	<td style="font-size:30px; font-weight: bold; color:#3493ff;">1:1 문의/건의</td>
                	<td><input id = "btn" type="button" value="목록" style="margin-left:7rem"onclick="history.back(-1);"></td> 
                </tr>
                <tr><td colspan="2" style="height:30px;">&nbsp;</td></tr>
                <tr><td colspan="2" style="font-weight: bold; font-size:25px; border-bottom:2px solid #2e4361;">${vo.title }</td></tr>
                <tr style="background-color:#f1f7ff; ">
                	<td style="padding:10px 0px 10px 0px; width:45.5rem;">
                		&nbsp;작성자 ID : <span style="font-size:13px;">${vo.writer }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td style="padding:10px 0px 10px 0px;">
                    	작성일 : <span style="font-size:13px; margin-left:1;">${vo.regdate }</span>&nbsp;&nbsp;
                    </td>
                </tr>
                <tr><td colspan="2" >&nbsp;</td></tr>
                <tr name = "contents">
                    <td colspan="2" >
                       	${fn:replace(vo.content, newline, "<br/>") }
                    </td>
                </tr>
                
            </table>

            
            <div style="height:40px;">&nbsp;</div>
            <hr style="border: solid 0.5px gray; width:900px;">      
               
	            <table style="margin:18px; width:900px;">
		            <c:if test="${not empty vo.reply }" >
		                <tr>
		                	<td style="background:#f1f1f1; font-size:13px; padding:10px 0 10px 0;">
		                		작성자: 관리자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		<c:if test="${'admin' == authUser.name }" ><a href="javascript:view('viewcode');" >[댓글 수정]</a></c:if>
		                	</td>
		                </tr>
		                <tr>
			                <td style="padding:10px 0 10px 10px; font-size:15px;">
			                		${vo.reply }
			                </td>
			        </c:if>
		                <c:if test="${empty vo.reply }">
		                	<td style="padding:10px 0 10px 10px; font-size:15px;">
		                		[ 관리자가 답변을 곧 작성할 예정입니다. ]
		                	</td>
		                </c:if>
	                </tr>
	            
	            </table>
	     
	        
	        
            <c:if test="${'admin' == authUser.id }" >
	            <form action="${pageContext.servletContext.contextPath}/oneToOne/reply/${no }" method="post">
		            <table style="margin:30px;">
			                <tr>
				            	<c:if test="${empty vo.reply }">
				                    <td>
				                        <textarea name="reply" cols="90" rows="5" onclick="this.value=''" style="width:48rem;"></textarea>
				                    </td>
				                    <td><input type="submit" value="댓글남기기" id="writeCommentBtn"></td>
				                </c:if>	
		                	</tr>
		                	<tr id="viewcode" style="display:none;">
		                		<c:if test="${not empty vo.reply }">
					                <td>
					                    <textarea  name="reply" cols="90" rows="5" style="width:48rem;" placeholder="댓글을 남겨주세요."></textarea>
					                    
						            </td>
					                <td>
					                	<input type="submit" value="댓글 수정" id="writeCommentBtn">
					                </td>
					            </c:if>
		                	</tr>
		                
		            </table>
		         </form>
			</c:if>
        </div>

        <footer class="positionBody">
            <div id="footer" style="width:70rem; margin-left:25rem; font-weight:bold; font-size:17px">
				<p>(c)opyright 2015, 2016, 2017, 2018, 2019, 2020, 2021</p>
			</div>
        </footer>
    </body>

</html>
