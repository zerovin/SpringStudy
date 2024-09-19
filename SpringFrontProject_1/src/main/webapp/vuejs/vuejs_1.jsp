<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	VueJS
	- 웹 화면을 출력하기 위한 자바스크립트 라이브러리 = 프레임워크
	- 컴포넌트 기반 : 기능별 처리 가능 => .vue, cdn방식(ajax처럼 JSP안에서 처리)
	               단독으로 처리 / JSP나 HTML안에서 출력 가능(cdn)
	- 라우터 : 화면 변경, 상태 관리(데이터를 저장해서 관리 vuex, store)
	- 배우기 쉽다
	- 성능이 좋다, 속도가 빠르다
	- 라이브러리가 가볍다
	- Angular JS - 양방향 통신
	  React JS - 가상돔
	  ================= 장점을 이용해서 만든 라이브러리
	  
	mount : 가상돔에 저장
	VueJS
	1. 생명주기***
	   callback => 자동호출
	   beforeCreate() => Vue객체 생성 전
	   created() => Vue객체 생성
	   beforeMount() => 가상돔에 태그를 올리기 전
	***mounted() => 가상돔에 태그 전송 => $(function(){}), window.onload=function(){}
	                리액트 - componentDidMount() / useEffect()
	                서버에서 출력에 필요한 데이터를 읽어온다
	   beforeUpdate() => 데이터 수정 전
	***updated() => 데이터 수정 완료
	   beforeDestory() => 화면 변경 전
	   destroyed() => 화면 변경
	   ex) $.ajax({
	       
	       }) 
	   	  let app=Vue.createApp({
	         data(){} => 멤버변수 => 화면에 출력할 데이터 모음
	         mounted(){} => 멤버변수의 초기화 => 서버에서 데이터 읽기
	         methods:{
	            사용자 정의 함수
	         }
	         components:등록
	       }).mount(app)
	2. 디렉티브*** : 태그 제어
	               v-for : 태그 안의 속성
	                       ex) <tr v-for=""></tr> => 반복문
	               v-if ~ v-else
	               v-if ~ v-elseif ~ v-else => 다중 조건문
	               v-text / v-html => text() / html()
	               v-on:click / v-on:change => @click / @change로 변경
	               v-model : 양방향
	               v-bind : 이미지 출력
	3. 서버연동
	4. 출력형식***
	   {{출력데이터}} => {react}, {{vue}}, ${}
	   => EL/JSTML은 사용하지 않는다
	5. 양방향***
	6. 화면 변경
 --%>
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
<!-- 
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#msg').keyup(function(){
		let msg=$('#msg').val()
		$('#print').text(msg)
	})
})
</script>
-->
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="30" class="input-sm" v-model="msg">
		</div>
		<div class="row">{{msg}}</div>
	</div>
	<script>
		let app=Vue.createApp({
			// 출력에 필요한 변수 설정
			/*
				정수(숫자)
				 - no:0
				문자열 
				 - msg:''
				객체 => VO
				 - board:{}
			    목록 => List
			     - board_list:[]
			    논리형
			     - bCheck:false
			*/
			data(){
				return{
					msg:''
				}
			},
			beforeCreate(){
				console.log("beforeCreate() call")
			},
			created(){
				console.log("create() call")
			},
			beforeMount(){
				console.log("beforeMount() call")
			},
			mounted(){
				console.log("***mounted() call => 화면출력완료:onload()")
			},
			beforeUpdate(){
				console.log("beforeUpdate() call")
			},
			updated(){
				console.log("updated() call => data에 설정된 변수값이 변경이 된 경우")
			},
			beforeDestory(){
				console.log("beforeDestory() call")
			},
			destroyed(){
				console.log("destoryed() call")
			}
		}).mount('.container')
		// mount : 제어 영역을 지정
	</script>
</body>
</html>