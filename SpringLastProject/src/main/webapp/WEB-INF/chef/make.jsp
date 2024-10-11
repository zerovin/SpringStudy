<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>
<script type="text/javascript">
//버전 충돌 방지
let $j371=jQuery.noConflict();
</script>
<style type="text/css">
.page-link:hover,
.nav-link:hover{
   cursor: pointer;
}
</style>
</head>
<body>
<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>레시피 목록</h2>
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
                            <li class="breadcrumb-item active" aria-current="page">셰프 목록</li>
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
            	<div class="col-12">
            		<table class="table">
            			<tr>
            				<td>
            					<table class="table" v-for="vo in chef_list">
            						<tr>
            							<td width="20%" class="text-center" rowspan="2">
            								<img :src="vo.poster" style="width:180px;height:150px;" class="nav-link" @click="detail(vo.chef)">
            							</td>
            							<td cospan="4">
            								<h3 style="color:orange;" class="nav-link" @click="detail(vo.chef)">{{vo.chef}}</h3>
            							</td>
            						</tr>
            						<tr>
            							<td class="text-center"><img src="../img/icon/m1.png">{{vo.mem_cont1}}</td>
            							<td class="text-center"><img src="../img/icon/m2.png">{{vo.mem_cont3}}</td>
            							<td class="text-center"><img src="../img/icon/m3.png">{{vo.mem_cont7}}</td>
            							<td class="text-center"><img src="../img/icon/m4.png">{{vo.mem_cont2}}</td>
            						</tr>
            					</table>
            				</td>
            			</tr>
            		</table>
            	</div>
                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                                <li class="page-item" v-if="startpage>1">
                                    <a class="page-link" @click="prev()"><i class="fa fa-angle-double-left" aria-hidden="true"></i> 이전</a>
                                </li>
                                
                                <li :class="i===curpage?'page-item active':'page-item'" v-for="i in range(startpage,endpage)">
                                    <a class="page-link" @click="pageChange(i)">{{i}}</a>
                                </li>
                     
                                <li class="page-item" v-if="endpage<totalpage">
                                    <a class="page-link" @click="next()">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>{{curpage}} page / {{totalpage}} pages</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div id="dialog" title="레시피" v-show="isShow">
        	<make_dialog v-bind:r_list="recipe_list"></make_dialog>
        </div>
    </section>
    <script>
    const makeRecipe={
    		props:['r_list'],
    		template:`
    			<div class="row">
	    			<div class="col-md-3" v-for="vo in r_list">
					    <div class="thumbnail">
						    <a href="#">
							    <img :src="vo.poster" style="width:100%">
							    <div class="caption">
							        <p class="a">{{vo.title}}</p>
							    </div>
						    </a>
					    </div>
					</div>
    			</div>
    		`
    }
    let chefApp=Vue.createApp({
		data(){
			return{
				chef_list:[],
				curpage:1,
				totalpage:0,
				startpage:0,
				endpage:0,
				recipe_list:[],
				isShow:false
			}
		},
		mounted(){
			this.dataRecv()
		},
		methods:{
			detail(chef){
				this.isShow=true
				this.chef=chef
				axios.get('../chef/recipe_make.do',{
					params:{
						chef:this.chef		
					}
				}).then(response=>{
					console.log(response.data)
					this.recipe_list=response.data
					$j371('#dialog').dialog({
						autoOpen:false,
						modal:true,
						width:700,
						height:600
					}).dialog("open")
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
			},
			dataRecv(){
				axios.get("../chef/list_vue.do",{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.chef_list=response.data.list
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.startpage=response.data.startpage
					this.endpage=response.data.endpage
				}).catch(error=>{
					console.log(error.response)
				})
			}
		},
		components:{
			"make_dialog":makeRecipe
		}
	}).mount("#listApp")
    </script>
</body>
</html>