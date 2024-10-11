package com.sist.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;
// 다른 언어와 연동 => 자바스크립트로 데이터 전송 / Kotlin => JSON 전송
/*
 *  @GetMapping
 *  @PostMapping
 *  @PutMapping
 *  @DeleteMapping
 *  @RequestMappig => 가급적 사용X, Spring6에서 사라짐
 *  
 *  Spring 5 - 보안강조, request/response 지양, xml->자바로 변환
 */
@RestController
public class ChefRestController {
	@Autowired //스프링에서 생성된 rService의 주소값 주입
	private RecipeService rService;
	
	@GetMapping(value="chef/list_vue.do", produces="text/plain;charset=UTF-8")
	public String chef_list(int page) throws Exception{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<ChefVO> list=rService.chefListData(map);
		int totalpage=rService.chefTotalPage();
		
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
}
