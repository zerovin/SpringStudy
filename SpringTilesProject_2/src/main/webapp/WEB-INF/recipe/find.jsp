<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
	1. MyBatis : 데이터베이스 연동
	2. tiles : <jsp:include>
 --%>
</head>
<body>
	<div class="row">
		<form method="POST" action="../recipe/find.do">
			<input type="text" class="input-sm" size="20" name="fd" value="${fd}">
			<button class="btn btn-sm btn-primary">검색</button>	
		</form>
	</div>
	<div style="height:10px;"></div>
	<div class="row">
		<c:forEach var="vo" items="${list}">
			<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../recipe/detail.do?no=${vo.no}">
			        <img src="${vo.poster}" title="${vo.title}" style="width:230px;height:150px;">
			        <div class="caption">
			          <p>${vo.chef}</p>
			        </div>
			      </a>
			    </div>
			</div>
		</c:forEach>
	</div>
	<div style="height:10px;"></div>
	<div class="row">
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${startpage>1}">
					<li><a href="../recipe/find.do?fd=${fd}&page=${startpage-1}">&lt;</a></li>
				</c:if>
				<c:forEach var="i" begin="${startpage}" end="${endpage}">
					<li ${i==curpage?"class=active":""}><a href="../recipe/find.do?fd=${fd}&page=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${endpage<totalpage}">
					<li><a href="../recipe/find.do?fd=${fd}&page=${endpage+1}">&gt;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>