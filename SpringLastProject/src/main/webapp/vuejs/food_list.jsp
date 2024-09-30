<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="image.js"></script>
<script type="text/javascript" src="page.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:960px;
	}
	.nav-link{
		cursor:pointer;
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
		<div class="row">
			<!-- 목록(이미지) : component => image-card -->
			<!-- 
			<div class="col-md-3" v-for="vo in list">
			    <div class="thumbnail">
			     	 <a href="#">
			        	<img :src="vo.poster" alt="Lights" style="width:230px;height:130px;">
			        	<div class="caption">
			          		<p>{{vo.name}}</p>
			        	</div>
			      	</a>
			    </div>
			</div>
			-->
			<image-card v-bind:list="list"></image-card>
		</div>
		<div style="height:10px;"></div>
		<!-- 페이지 출력 : component => page-card-->
		<div class="row">
			<div class="text-center">
				<!-- <a> <form> : 화면 변경(jsp삭제 => 새로운 jsp) -->
				<!-- 
				<ul class="pagination">
					<li v-if="startpage>1"><a class="nav-link" @click="prev()">&lt;</a></li>
					<li v-for="i in range(startpage, endpage)" :class="i===curpage?'active':''"><a class="nav-link" @click="pageChange(i)">{{i}}</a></li>
					<li v-if="endpage<totalpage"><a class="nav-link" @click="next()">&gt;</a></li>
				</ul>
				 -->
				 <page-card v-bind:page="page"></page-card>
			</div>
		</div>
	</div>
	<script>
		// .js => 재사용 목적
		/*
		const image_card={
				props:['list'],
				template:`<div class="col-md-3" v-for="vo in list">
				    <div class="thumbnail">
				     	 <a href="#">
				        	<img :src="vo.poster" alt="Lights" style="width:230px;height:130px;">
				        	<div class="caption">
				          		<p>{{vo.name}}</p>
				        	</div>
				      	</a>
				    </div>
				</div>`
		}
		const page_card={
				template:`<ul class="pagination">
					<li v-if="$parent.startpage>1"><a class="nav-link" @click="$parent.prev()">&lt;</a></li>
					<li v-for="i in $parent.range(startpage, endpage)" :class="i===$parent.curpage?'active':''"><a class="nav-link" @click="$parent.pageChange(i)">{{i}}</a></li>
					<li v-if="$parent.endpage<$parent.totalpage"><a class="nav-link" @click="$parent.next()">&gt;</a></li>
				</ul>`
		}
		*/
		let listApp=Vue.createApp({
			data(){
				return{
					list:[],
					curpage:1,
					totalpage:0,
					startpage:0,
					endpage:0
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
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
					}).catch(error=>{
						console.log(error.response)
					})
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