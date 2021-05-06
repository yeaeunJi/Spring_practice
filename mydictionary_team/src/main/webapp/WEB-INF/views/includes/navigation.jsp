<!-- 라이브러리 지시자 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
	#ulStyle{                
	    padding: 10px;    
	    width: 1300px;    
	    height: 70px;    
	}
	#liStyle{
	    font-family: 'Noto Sans KR', sans-serif;
	    font-weight: bold;
	    list-style:none;
	    float: left;
	    padding:0px;
	}           

</style>	
	
<div id="navigation" style="width:70rem; margin-left: 23rem;">
	<ul id="ulStyle">
		<li id="liStyle" style="margin-right:-4rem;"><a href="${pageContext.request.contextPath }/main/introduce">소개</a></li>
		<li id="liStyle" style="margin-right:1rem;"><a href="${pageContext.request.contextPath }/bookmark">내 즐겨찾기</a></li>
		<li id="liStyle" style="margin-right:-3rem;"><a href="${pageContext.request.contextPath }/dictionary">검색</a></li>
		<li id="liStyle"><a href="${pageContext.request.contextPath }/board" >자유게시판</a></li>
		<li id="liStyle"><a href="${pageContext.request.contextPath }/oneToOne" >1:1 문의</a></li>
	</ul>
</div>
