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
		width:960px;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="20" class="input-sm" v-model="fd" ref="fd">
			<input type="button" class="btn-sm btn-danger" value="검색" @click="find()">
		</div>
	</div>
	<script>
		/*
			new Vue({
				el:'제어하는 태그 영역', => mount('')
				data:{}
			})
		
			M : 데이터 설졍 => data()
			V : v-if, v-model, v-on:click, v-for, v-show
			    디렉티브
			VM : mounted(){}, methods:{}, components:{}, filter:{}
			
			input제어 => ref 속성 이용
			
			서버 연결 후 데이터 읽기 
			=> axios.get() => @GetMapping => SELECT
			   axios.post() => @PostMapping => INSERT
			   ========================================= 웹에서는 보통 get/post 두개사용 RestFul
			   axios.put() => @PutMapping => UPDATE
			   axios.delete() => @DeleteMapping => DELETE
		*/
		let app=Vue.createApp({
			data(){
				return{
					fd:''		
				}
			},
			methods:{
				// id => ref 속성 이용
				// this.$refs.ref명.이벤트(focus,value...) => inpurt 태그 제어
				// e.target.value => React
				// $('#fd').val() => jquery
				// submit 버든은 사용하지 않는다 => 자체에서 CRUD 처리
				find(){
					let fds=this.$refs.fd.value
					if(fds===""){
						alert("검색어를 입력하세요")
						this.$refs.fd.focus()
						return
					}
					alert("검색어 : "+this.fd)
				}	
			}
		}).mount('.container')
	</script>
</body>
</html>