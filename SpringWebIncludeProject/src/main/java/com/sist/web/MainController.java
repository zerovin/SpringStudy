package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// HandlerMapping => @Controller : DispatcherServlet 연결
// 사용자 요청을 받아 데이터베이스 연동 => JSP로 결과값 전송 (Model)
// 전체 프로그램의 조립기 (Main)
/*
 *  요청 - 요청분리/구분
 *        - @RequestMapping = POST/GET 동시에 처리
 *                            ex)검색 (검색<form>-Post/페이징<a>-Get)
 *                            Spring 6에서는 RequestMapping 사용불가
 *          - @GetMapping
 *          - @PostMapping
 *      - 요청하는 데이터
 *        - 사용자가 보내준 데이터
 *          ex) 상세보기 => 번호
 *              로그인 => id, pwd
 *              검색 => 검색어
 *              목록 => 페이지
 *          =====> getParameter() => 매개변수
 *      - 요청결과 전송 - Model 전송객체 이용
 *      
 *  JSP => 요청<a>, <form> => .do
 *   |
 *  Controller => @GetMapping(".do")
 *   |
 *  Mapper => SQL문장
 *   |
 *  DAO => SQL구현
 *   |
 *  Controller => DAO 호출 => Model에 담기
 *   |
 *  JSP => 출력
 */

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;
@Controller
// Mapper => DAO => Service => Model - 결합성이 낮게, 유지보수가 편리(사이트운영유지하면서 유지보수)
// Controller 집중
public class MainController {
	@Autowired
	private RecipeService rService; // DAO 여러개 동시 처리 => BI (관련된 기능 통합)

	@GetMapping("main/main.do")
	public String main_main(String page, Model model, HttpServletRequest request) {
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
		List<RecipeVO> list=rService.recipeListData(map);
		int count=rService.recipeRowCount();
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
		
		//쿠키 출력 HttpServletRequest request 매개변수 필요
		Cookie[] cookies=request.getCookies();
		//쿠키 담는 List
		List<RecipeVO> cList=new ArrayList<RecipeVO>();
		
		if(cookies!=null) {
			//최신부터 담는다
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("recipe_")) {
					String no=cookies[i].getValue();
					RecipeVO vo=rService.recipeCookieInfoData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
