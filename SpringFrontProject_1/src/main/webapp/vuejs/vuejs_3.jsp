<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		width:500px;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">{{title}}</h3>
		<div class="row">
			<!-- 
			<table class="table">
				<tr>
					<td width="20%">사번</td>
					<td width="80%" v-text="sawon.sabun"></td>
				</tr>
				<tr>
					<td width="20%">이름</td>
					<td width="80%">{{sawon.name}}</td>
				</tr>
				<tr>
					<td width="20%">부서명</td>
					<td width="80%">{{sawon.dept}}</td>
				</tr>
				<tr>
					<td width="20%">직위</td>
					<td width="80%">{{sawon.job}}</td>
				</tr>
				<tr>
					<td width="20%">근무지</td>
					<td width="80%">{{sawon.loc}}</td>
				</tr>
				<tr>
					<td width="20%">연봉</td>
					<td width="80%">{{sawon.pay}}</td>
				</tr>
			</table>
			-->
			<table class="table">
				<thead>
					<tr>
						<th class="text-center">사번</th>
						<th class="text-center">이름</th>
						<th class="text-center">부서명</th>
						<th class="text-center">직위</th>
						<th class="text-center">근무지</th>
						<th class="text-center">연봉</th>	
					</tr>
				</thead>
				<tbody>
					<tr v-for="vo in sawon" v-on:click="detail_data(vo)">
						<td class="text-center">{{vo.sabun}}</td>
						<td class="text-center">{{vo.name}}</td>
						<td class="text-center">{{vo.dept}}</td>
						<td class="text-center">{{vo.job}}</td>
						<td class="text-center">{{vo.loc}}</td>
						<td class="text-center">{{vo.pay}}</td>	
					</tr>
				</tbody>
			</table>
		</div>
		<%-- jquery : show()/hide() = vue : v-show="" --%>
		<div class="row" v-show="isShow">
			<ul>
				<li>사번 : {{detail.sabun}}</li>
				<li>이름 : {{detail.name}}</li>
				<li>부서명 : {{detail.dept}}</li>
				<li>직위 : {{detail.job}}</li>
				<li>근무지 : {{detail.loc}}</li>
				<li>연봉 : {{detail.pay}}</li>
			</ul>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					title:'사원목록',
					sawon:[{"sabun":1,"name":"홍길동1","dept":"개발부","job":"대리","loc":"서울","pay":3000},
						{"sabun":2,"name":"홍길동2","dept":"개발부","job":"대리","loc":"서울","pay":3000},
						{"sabun":3,"name":"홍길동3","dept":"개발부","job":"대리","loc":"서울","pay":3000},
						{"sabun":4,"name":"홍길동4","dept":"개발부","job":"대리","loc":"서울","pay":3000},
						{"sabun":5,"name":"홍길동5","dept":"개발부","job":"대리","loc":"서울","pay":3000}],
					isShow:false,
					detail:{}
				}
			},
			methods:{
				detail_data(s){
					//methods 안에 설정된 변수는 지역변수
					//this=data()
					//data()에 설정된 변수는 멤버변수 => 변수 사용시 this.~
					this.detail=s;
					this.isShow=true
					//this가 없는 경우에는 일반 지역변수 => data()의 변수와 관계가 없게 됨
				}
			}
		}).mount('.container') //HTML의 영역제어
		/*
			제어문
			 v-for : 반복문
			 v-if / v-show : 조건문
			 v-on:이벤트명="함수명"
			      click="함수명"
			      change="함수명"
			      keydown/keyup="함수명"
			      mousedown/mouseup="함수명"
			 v-model : 양방향 => 입력값을 받아서 출력
			 
			 단점 : 오라클 연동 불가 => 스프링 서버 이용
		*/
	</script>
</body>
</html>