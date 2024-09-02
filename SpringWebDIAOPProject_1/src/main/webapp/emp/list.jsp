<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h3>사원 목록</h3>
		<table border="1" bordercolor="black">
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>급여</th>
			</tr>
			<c:forEach var="vo" items="${list}">
				<tr>
					<th>${vo.empno}</th>
					<th>${vo.ename}</th>
					<th>${vo.job}</th>
					<th>${vo.dbday}</th>
					<th>${vo.sal}</th>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>