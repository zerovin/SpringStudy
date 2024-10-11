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
	<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>자유 게시판</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">자유 게시판</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" id="listApp">
        <div class="container">
            <div class="row">
            	<c:if test="${sessionScope.userId!=null}">
	            	<table class="table">
	            		<tr>
	            			<td>
	            				<a href="../freeboard/insert.do" class="btn btn-sm btn-primary">새글</a>
	            			</td>
	            		</tr>
	            	</table>
            	</c:if>
            	<table class="table">
            		<tr>
            			<th class="text-center" width="10%">번호</th>
            			<th class="text-center" width="45%">제목</th>
            			<th class="text-center" width="15%">이름</th>
            			<th class="text-center" width="20%">작성일</th>
            			<th class="text-center" width="10%">조회수</th>
            		</tr>
            		<tr v-for="vo in board_list">
            			<td class="text-center" width="10%">{{vo.no}}</td>
            			<td width="45%">
            				<a :href="'../freeboard/detail.do?no='+vo.no">{{vo.subject}}</a>
            				<sup v-if="today===vo.dbday"><img src="../img/icon/new.gif"></sup>
            			</td>
            			<td class="text-center" width="15%">{{vo.name}}</td>
            			<td class="text-center" width="20%">{{vo.dbday}}</td>
            			<td class="text-center" width="10%">{{vo.hit}}</td>
            		</tr>
            		<tr>
            			<td colspan="5" class="text-center">
            				<button class="btn-sm btn-danger" v-on:click="prev()">이전</button>
            				{{curpage}} page / {{totalpage}} pages
            				<button class="btn-sm btn-danger" @click="next()">다음</button>
            			</td>
            		</tr>
            	</table>
            </div>
        </div>
	</section>
	<script>
		// M 데이터 / V HTML / VM 수정 => MVVM패턴
		let listApp=Vue.createApp({
			// Model => 데이터 상태(변경) 관리
			// List[] vo{} 
			data(){
				return{
					board_list:[],
					curpage:1,
					totalpage:0,
					count:0,
					today:''
				}
			},
			// VM => ViewModel => 데이터 값을 변경
			mounted(){ //window.onload
				this.dataRecv()
			},
			methods:{ // 사용자 요청에 따라 데이터 변경
				prev(){
					this.curpage=this.curpage>1?this.curpage-1:this.curpage
					this.dataRecv()
				},
				next(){
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
					this.dataRecv()
				},
				//1.공통으로 적용되는 기능 설정 => 목록 읽기
				dataRecv(){
					axios.get('../freeboard/list_vue.do',{
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.board_list=response.data.list
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
						this.count=response.data.count
						this.today=response.data.today
					}).catch(error=>{
						console.log(error.response)
					})
				}
				//2.CRUD
			}
		}).mount('#listApp')
	</script>
</body>
</html>