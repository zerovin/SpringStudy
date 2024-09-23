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
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:800px;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">자료실</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-danger">등록</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<thead>
					<tr>
						<th width="10%" class="text-center">번호</th>
						<th width="45%" class="text-center">제목</th>
						<th width="15%" class="text-center">이름</th>
						<th width="20%" class="text-center">작성일</th>
						<th width="10%" class="text-center">조회수</th>
					</tr>
				</thead>
				<%-- 디렉티브
				 	v-for
				 	v-if ~ v-else
				 	v-on:click = @click
				 	v-on:change = @change
				 	v-on:keydown = @keydown
				 	v-model : 사용자 입력과 Vue의 멤버변수 연결
				 	v-bind : 생략가능, Vue의 변수를 속성에 설정 =>  <img v-bind:src="">, <img :src="">
				 	
				 	<태그>{{출력할 변수}}</태그>
				 	react <태그>{출력할 변수}</태그>
				 	파이썬 {{}} => vue와 충돌시 vue 형식 변경 [[]]
				 	
				 	ref : id/class
				 --%>
				<tbody>
					<tr v-for="">
						<td width="10%" class="text-center"></td>
						<td width="45%"></td>
						<td width="15%" class="text-center"></td>
						<td width="20%" class="text-center"></td>
						<td width="10%" class="text-center"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" class="text-center">
							<input type="button" value="이전" class="btn-sm btn-success">
							{{curpage}} page / {{totalpage}} pages
							<input type="button" value="다음" class="btn-sm btn-info">
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<script>
		let dataApp=Vue.createApp({
			data(){
				return{
					data_list:[]	
				}	
			},
			//생명주기 => onload => 브라우저 출력 전에 서버로부터 데이터를 받는 경우
			mounted(){
				
			},
			// 멤버변수의 값이 변경된 경우 => Component 제작
			updated(){
				
			},
			// 사용자 정의 메소드 => 이벤트
			methods:{
				
			}
			// components / filter / computed
		}).mount('.container')
	</script>
</body>
</html>