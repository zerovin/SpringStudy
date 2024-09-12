<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:800px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<form method="post" action="find.do">
				<select name="fd" class="input-sm">
					<option value="all">전체</option>
					<option value="ename">이름</option>
					<option value="hiredate">입사년도</option>
					<option value="job">직위</option>
				</select>
				<input type="text" name="ss" size="20" class="input-sm">
				<button class="btn-sm btn-success">검색</button>
			</form>
		</div>
		<h3>검색 결과</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th class="text-center">사번</th>
					<th class="text-center">이름</th>
					<th class="text-center">직위</th>
					<th class="text-center">입사일</th>
					<th class="text-center">급여</th>
					<th class="text-center">부서번호</th>
				</tr>
				<c:forEach var="vo" items="${list}">
					<tr>
						<td class="text-center">${vo.empno}</td>
						<td class="text-center">${vo.ename}</td>
						<td class="text-center">${vo.job}</td>
						<td class="text-center">${vo.dbday}</td>
						<td class="text-center">${vo.sal}</td>
						<td class="text-center">${vo.deptno}</td>
					</tr>
				</c:forEach>
			</table>	
		</div>
	</div>
</body>
</html>