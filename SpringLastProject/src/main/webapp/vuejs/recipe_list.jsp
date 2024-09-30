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
	.nav-link{
		cursor:pointer;
	}
	p{
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="image.js"></script>
<script src="page.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<poster-card v-bind:list="list"></poster-card>
		</div>
		<div style="height:10px"></div>
		<div class="row">
			<div class="text-center">
				<page-card v-bind:page="page"></page-card>
			</div>
		</div>
	</div>
	<script src="vue_list.js"></script>
	<!-- 
	<script>
		let recipeApp=Vue.createApp({
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
					axios.get('../recipe/list_vue.do',{
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
				prev(){
					this.curpage=this.startpage-1
					this.dataRecv()
				},
				next(){
					this.curpage=this.endpage-1
					this.dataRecv()
				},
				pageChange(page){
					this.curpage=page
					this.dataRecv()
				},
				range(start, end){
					let arr=[]
					let len=end-start
					for(let i=0;i<len;i++){
						arr[i]=start
						start++
					}
					return arr
				}
			},
			components:{
				'poster-card':image_card,
				'page-card':page_card
			}
		}).mount('.container')
	</script>
	-->
</body>
</html>