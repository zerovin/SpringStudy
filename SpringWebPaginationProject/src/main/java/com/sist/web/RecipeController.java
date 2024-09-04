package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *  JSP ==> RecipeController
 *  오라클 ==> RecipeDAO 
 */
@Controller
//JSP로 값 전송 => DispatcherServlet과 연결
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	
	//매칭 => 사용자 전송 URL => 메소드 호출
	@RequestMapping("recipe/list.do")
	public String recipe_list(String page, Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<RecipeVO> list=dao.recipeListData(map);
		int count=dao.recipeRowCount();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("count", count);
		return "recipe/list";
	}
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no, Model model) {
		//데이터베이스 연결 => 상세보기
		
		//상세보기 페이지에 출력할 데이터 전송 => Model
		return "recipe/detail";
	}
}
