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
        <div style="font-size:30px; font-weight: bold; margin-left:29rem;">1:1 문의/건의</div>
        <div style="heigth:r]"></div>
        <c:if test="${'admin' != authUser.id }" >
	        <div style="margin-bottom:10px; margin-left:1380px;">
	            <input id = "btn" type="button" value="글쓰기" onclick="location.href='${pageContext.servletContext.contextPath}/oneToOne/writeForm'">
	         </div>
		</c:if>
		
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
		                    <td><a id = "defaultUp" href="${pageContext.request.contextPath }/oneToOne/detail/${vo.no }">${vo.title }</a></td>
		                    <td style="text-align:center;">${vo.writer}</td>
		                    <td style="text-align:center;">${vo.regdate }</td>
		                    <c:if test="${vo.writer == authUser.id }" >
		                    	<td style="text-align:center;"><input id = "btn" type="button" value="수정" onclick="location.href='${pageContext.servletContext.contextPath}/oneToOne/modifyForm/${vo.no}'" style="background-color: red;"></td>
		                    </c:if>
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
				<c:set var="page" value="${(empty page)?1:page}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.') }" />
				<c:set var="end" value="${totalCnt/10 > 4 ? 4:totalCnt/10 }" />  
				<!-- ****************************************************** -->
				<div class="pager">
					<ul>
						<li>
						<c:choose>
							<c:when test="${startNum > 1}">
								<a href="${pageContext.request.contextPath }/oneToOne/${startNum-1}" >◀</a>
							</c:when>
							<c:otherwise>
								<span onclick="alert('이전 페이지가 없습니다.');">◀</span>
							</c:otherwise>
						</c:choose>
						</li>
						<c:forEach var="i" begin="0" end="${end }">
							<li>
								<a href="${pageContext.request.contextPath }/oneToOne/${startNum+i}">${startNum+i}</a>
							</li>
						</c:forEach>
						<li>
							<c:choose>
								<c:when test="${startNum+4<lastNum}">
									<a href="${pageContext.request.contextPath }/oneToOne/${startNum+i}">▶</a>
								</c:when>
								<c:otherwise>
									<span onclick="alert('다음 페이지가 없습니다.');">▶</span>
								</c:otherwise>
							</c:choose>
						</li>
						
					</ul>
				</div>


				<!-- pager 추가 -->
				
       <br />
       
        <footer class="positionBody">
            <div id="footer" style="width:70rem; margin-left:25rem; font-weight:bold; font-size:17px">
				<p>(c)opyright 2015, 2016, 2017, 2018, 2019, 2020, 2021</p>
			</div>
        </footer>
    </body>
</html>
