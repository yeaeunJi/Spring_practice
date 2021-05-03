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
        <link href="${pageContext.servletContext.contextPath}/assets/css/oneToOneIndex.css" type = "text/css" rel = "stylesheet">
        
    </head>
    <body style="background:#edf1f8; margin-top:3rem;">       
        <header class = "positionHead">
            <table>
                <tr>
                    <td colspan="2" style="width:300px;height:100px;font-size:60px;font-family: 'Jua', sans-serif"><a title="메인페이지로 가기" href="${pageContext.servletContext.contextPath}" style="text-decoration:none;">Bit민원24</a></td>
    
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
        </header>
        <div style="font-size:30px; font-weight: bold; margin-left:29rem;">1:1 문의/건의</div>
        <div style="margin-bottom:10px; margin-left:1380px;">
            <input id = "btn" type="button" value="글쓰기" onclick="location.href='${pageContext.servletContext.contextPath}/oneToOne/writeForm'">
         </div>

         <section class = "positionBody">
            <form name='my_form'>
            <table style="margin-left:40px; border-collapse: collapse;">
            	<thead>
	                <tr>
	                    <th style="width:50px; padding:10px 17px 10px 17px; border-top:1px solid black; border-bottom:1px solid #d3d3d3;">번호</th>
	                    <th style="width:100px; padding:10px 200px 10px 315px; border-top:1px solid black; border-bottom:1px solid #d3d3d3;">제목</th>
	                    <th style="width:100px; padding:10px 17px 10px 17px; border-top:1px solid black; border-bottom:1px solid #d3d3d3;">작성자</th>
	                    <th style="width:100px; padding:10px 17px 10px 17px; border-top:1px solid black; border-bottom:1px solid #d3d3d3;">작성일</th>
	                    <th style="width:50px; padding:10px 17px 10px 17px; border-top:1px solid black; border-bottom:1px solid #d3d3d3;"></th>
	                </tr>
                </thead>
                <tbody>
                	<c:set var="count" value="${fn:length(list) }" />
                	<c:forEach items="${list }" var="vo" varStatus="status">
		                <tr style="border-bottom:1px solid #d3d3d3;">
		                    <td style="text-align:center; padding-left:-5px; width:68px">${vo.no }</td>
		                    <td><a id = "defaultUp" href="postForm.html">${vo.title }</a></td>
		                    <td style="text-align:center;">${vo.writer}</td>
		                    <td style="text-align:center;">${vo.regdate }</td>
		                    <td style="text-align:center;"><input id = "btn" type="button" value="수정" onclick="location.href='${pageContext.servletContext.contextPath}/oneToOne/modify'" style="background-color: red;"></td>
		                </tr>  
	                </c:forEach>
                </tbody>             
            </table>
        </form>
        </section>
       <form name="search" method="post" action="${pageContext.servletContext.contextPath}/oneToOne/search">
	       <table style="margin-left:67rem; margin: top 0.5em;">
	            <tr>
	                <td>                	
		                <select name="searchOption" style="width:100px; height:30px ">
		                    <option value="titleContent">제목+본문</option>
		                    <option value="title">제목만</option>
		                </select>
	                </td>
	                <td><input class="searchInput" type="text" placeholder="  검색" name="keyword" style="height:23px;"></td>
	                <td><input type="submit" id='btn' value ="검색" style="height:30px;"></td>
	            </tr>
	            <tr></tr>
	        </table>
        </form>

        <!-- pager 추가 -->

				<!-- 현재 page와 시작 page, 마지막 page 값을 구해서 각각의 변수에 담았다. -->
				<!-- ****************************************************** -->
				<c:set var="page" value="${(empty p)?1:p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.') }" />
				<!-- ****************************************************** -->
				<div class="pager">
					<ul>
						<li>
						<c:choose>
							<c:when test="${startNum > 1}">
								<a href="${pageContext.request.contextPath }/oneToOne/search/${startNum-1}" >◀</a>
							</c:when>
							<c:otherwise>
								<span onclick="alert('이전 페이지가 없습니다.');">◀</span>
							</c:otherwise>
						</c:choose>
						</li>
						<c:forEach var="i" begin="0" end="4">
							<li>
								<a href="${pageContext.request.contextPath }/oneToOne/search/${startNum+i}">${startNum+i}</a>
							</li>
						</c:forEach>
						<li>
							<c:choose>
								<c:when test="${startNum+4<lastNum}">
									<a href="${pageContext.request.contextPath }/oneToOne/search/${startNum+i}">▶</a>
								</c:when>
								<c:otherwise>
									<span onclick="alert('다음 페이지가 없습니다.');">▶</span>
								</c:otherwise>
							</c:choose>
						</li>
						
					</ul>
				</div>


				<!-- pager 추가 -->
				
       
       
        <footer class="positionBody">
            <hr>
            <p style="font-size:15px;color:gray;">
                <span style="margin-left:6%;">상호: Bit민원24&nbsp;&nbsp;&nbsp;대표: 이갑성&nbsp;&nbsp;&nbsp;주소: 서울특별시 서초구 서초대로74길33 비트빌3층&nbsp;&nbsp;&nbsp;전화번호: 010-1234-5678&nbsp;&nbsp;&nbsp;&nbsp;대표메일: kabsung3@naver.com<p>
                <span style="margin-left:31%; color:gray;">CORYRIGHT DOCKINGJOB 2021 ALL RIGHTS RESESRVED</span>
            </p>
        </footer>
    </body>
</html>
