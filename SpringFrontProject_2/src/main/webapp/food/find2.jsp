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
		width:960px;
	}
	p{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	.link{
		cursor:pointer;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<%--
		일반 스프링 - JSP 제어
		화면 이동 - <a>, <form>
		VueJS - 이벤트, button
	 --%>
	 <div class="container">
	 	<div class="row">
	 		<input type="text" size="20" class="input-sm" ref="address" v-model="address" @keydown.enter="find()">
	 		<input type="button" value="검색" class="btn-sm btn-success" @click="find()">
	 	</div>
	 	<div style="height:10px;"></div>
	 	<div class="row">
	 		<div class="col-md-3" v-for="vo in find_list">
			    <div class="thumbnail">
			      <a href="">
			        <img :src="'http://menupan.com'+vo.poster" :title="vo.address" style="width:230px;height:150px;">
			        <div class="caption">
			          <p class="a">{{vo.name}}</p>
			        </div>
			      </a>
			   </div>
			</div>
	 	</div>
	 	<div style="height:10px;"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startpage>1"><a class="link" @click="prev()">&lt;</a></li>
					<li v-for="i in range(startpage, endpage)" :class="i===curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
					<li v-if="endpage<totalpage"><a class="link" @click="next()">&gt;</a></li>
				</ul>
			</div>
		</div>
	 </div>
	 <script>
	 	let findApp=Vue.createApp({
	 		data(){
	 			return{
	 				find_list:[],
	 				curpage:1,
	 				totalpage:0,
	 				startpage:0,
	 				endpage:0,
	 				address:'마포'
	 			}
	 		},
	 		mounted(){
	 			this.dataRecv()
	 		},
	 		methods:{
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
				},
	 			find(){
	 				if(this.address===""){
	 					alert("검색어를 입력하세요")
	 					this.$refs.address.focus()
	 					return
	 				}
	 				this.curpage=1
	 				this.dataRecv()
	 			},	
	 			dataRecv(){
	 				axios.get("http://localhost:8080/web/food/find_vue.do",{
	 					params:{
	 						page:this.curpage,
	 						address:this.address
	 					}
	 				}).then(response=>{
	 					this.find_list=response.data.list
		 				this.curpage=response.data.curpage
		 				this.totalpage=response.data.totalpage
		 				this.startpage=response.data.startpage
		 				this.endpage=response.data.endpage
		 				this.address=response.data.address
	 				})
	 			}
	 		}
	 	}).mount('.container')
	 </script>
</body>
</html>