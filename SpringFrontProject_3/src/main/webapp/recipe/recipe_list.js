/*
	$.ajax({
		type:'get',
		url:'',
		data:{},
		success:function(res){},
		error:function(request, status, error){}
	})
*/

let recipeApp=Vue.createApp({
	data(){
		return{
			recipe_list:[],
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
			axios.get('http://localhost:8080/web/recipe/list_vue.do',{
				params:{
					page:this.curpage
				}
			}).then(response=>{
				//결과값 받는 곳 => response.data
				console.log(response.data)
				this.recipe_list=response.data.list
				this.curpage=response.data.curpage
				this.totalpage=response.data.totalpage
				this.startpage=response.data.startpage
				this.endpage=response.data.endpage
			})
		}
	}
}).mount('.container')