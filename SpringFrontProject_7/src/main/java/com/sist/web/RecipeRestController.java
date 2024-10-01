package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class RecipeRestController {
	@Autowired
	private RecipeDAO rDao;
	
	@GetMapping(value="recipe/list_vue.do", produces="text/plain;charset=UTF-8")
	public String recipe_list(int page) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list=rDao.recipeListData(map);
		int totalpage=rDao.recipeTotalPage();
		
		final int BLOCK=10;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value="recipe/detail_vue.do",produces="text/plain;charset=UTF-8")
	public String recipe_detail(int no) throws Exception{
		RecipeDetailVO vo=rDao.recipeDetailData(no);
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		String foodmake=vo.getFoodmake();
		String[] fm=foodmake.split("\n");
		
		for(String s:fm) {
			StringTokenizer st=new StringTokenizer(s, "^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		Map map=new HashMap();
		map.put("vo", vo);
		map.put("mList", mList);
		map.put("iList", iList);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
}
