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