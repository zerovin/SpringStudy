<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row{
		margin:0px auto;
		width:960px;
	}
	.a{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list}">
				<div class="col-md-3">
				    <div class="thumbnail">
				      <a href="detail.do?fno=${vo.fno}">
				        <img src="http://menupan.com${vo.poster}" title="${vo.address}" style="width:230px;height:150px;">
				        <div class="caption">
				          <p class="a">${vo.name}</p>
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
						<li><a href="list.do?page=${startpage-1}">&lt;</a></li>
					</c:if>
					<c:forEach var="i" begin="${startpage}" end="${endpage}">
						<li ${i==curpage?"class=active":""}><a href="list.do?page=${i}">${i}</a></li>
					</c:forEach>
					<c:if test="${endpage<totalpage}">
						<li><a href="list.do?page=${endpage+1}">&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>