<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	.nav-link{
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
		<h1 class="text-center">레시피 목록</h1>
		<div class="row">
			<image-card></image-card>
		</div>
		<div style="height:10px;"></div>
		<div class="row">
			<page-card></page-card>
		</div>
	</div>
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
						this.totalpage=response.data.tatalpage
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
					this.curpage=this.endpage+1
					this.dataRecv()
				},
				pageChange(page){
					this.curpage=page
					this.dataRecv()
				},
				range(start, end){
					let pages=[]
					let leng=end-start
					for(let i=0;i<=leng;i++){
						pages[i]=start
						start++
					}
					return pages
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