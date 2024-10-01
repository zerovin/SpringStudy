<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	기술면접 기본
	
	자바 : 변수 (데이터형), 연산자, 제어문, 배열
	      객체지향프로그램(캡슐화, 다형성-오버라이딩 / 클래스, 객체 / 인터페이스, 추상클래스 / 라이브러리-java.lang, java.util, java.io)
	오라클 : DML(INSERT, UPDATE, DELETE), DQL(SELECT-JOIN,SUBQUERY)
	JSP : 지시자(taglib, page-contentType~), 내장객체(request, response, session)
	      cookie, EL, JSTL, MVC
	Spring : DI, AOP, MVC, ORM(MyBatis)
	================================================= Back-End
	Jquery(Ajax), HTML, CSS => Front-End
	Vue, React, Redux, React-Query => 우대사항
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="image.js"></script>
<script src="page.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:960px;
	}
	.nav_link{
		cursor:pointer
	}
	p{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">맛집 목록</h1>
		<div class="row">
			<image-card></image-card>
		</div>
		<div style="height:10px;"></div>
		<div class="row">
			<page-card></page-card>
		</div>
	</div>
	<script>
		let listApp=Vue.createApp({
			//Model => 데이터 관리 => Vue/React 상태관리
			data(){
				return{
					// 데이터 값이 변경되면 HTML에 출력한 데이터가 변경
					list:[],
					curpage:1,
					totalpage:0,
					startpage:0,
					endpage:0
				}
			},
			//data에 있는 변수의 값을 변경 => 초기화
			mounted(){
				// window.onload => $(function(){}) => componentDidMount()
				// react => useEffect()
				this.dataRecv()
			},
			//사용자 요청에 따라 변경 => 사용자 요청 => 이벤트
			//@click="함수명", v-on:click="함수명"
			//@change="함수명" @mouseover="함수명" @keyup="" @keydown=""
			//@keydown.enter="" @keydown.space="" @keydown.up=""
			methods:{
				//공통으로 사용하는 함수
				dataRecv(){
					axios.get('../food/list_vue.do',{
						params:{
							page:this.curpage							
						}
					}).then(response=>{
						console.log(response.data)
						this.list=response.data.list
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.startpage=response.data.startpage
						this.endpage=response.data.endpage
						/*
							response={config:{},data:{curpage:1,totalpage:10,list:[{},{},{}...]}}
							Vue/React => 값이 변경이 되면 자동으로 HTML 변경
						*/
					}).catch(error=>{
						console.log(error.response)
					})
				},
				prev(){
					this.curpage=this.startpage-1
					this.dataRecv()
				},
				next(){
					this.curpage=this.endpage+1
					this.dataRecv()
				},
				pageChange(page){
					this.curpage=page
					this.dataRecv()
				},
				range(start, end){
					let arr=[]
					let len=end-start
					for(let i=0;i<=len;i++){
						arr[i]=start
						start++
					}
					return arr
				}
			},
			components:{
				'image-card':image_card,
				'page-card':page_card
			}
		}).mount('.container')
	</script>
</body>
</html>