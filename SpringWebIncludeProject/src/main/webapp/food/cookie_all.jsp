<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.a{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>
</head>
<body>
	<div class="row">
		<c:if test="${cookie_size==0}]">
			<h3 class="text-center">방문한 레시피가 없습니다</h3>
		</c:if>
		<c:if test="${cookie_size>0}">
			<c:forEach var="vo" items="${cookie_list}">
				<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../food/detail_before.do?no=${vo.fno}">
			        <img src="http://menupan.com${vo.poster}" title="${vo.address}" style="width:230px;height:150px;">
			        <div class="caption">
			          <p class="a">${vo.name}</p>
			        </div>
			      </a>
			   </div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<div style="height:10px"></div>
	<div class="row">
		<div class="text-right">
			<a href="../food/cookie_delete.do" class="btn btn-sm btn-danger">삭제</a>
		</div>
	</div>
</body>
</html>