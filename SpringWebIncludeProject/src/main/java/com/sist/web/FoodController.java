package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.service.FoodService;
import com.sist.vo.*;
@Controller
public class FoodController {
	@Autowired
	private FoodService fService;
	
	@GetMapping("food/list.do")
	public String food_list(String page, Model model, HttpServletRequest request) {
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
		List<FoodVO> food_list=fService.foodListData(map);
		
		int count=fService.foodRowCount();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		model.addAttribute("food_list", food_list);
		model.addAttribute("count", count);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		
		Cookie[] cookies=request.getCookies();
		List<FoodVO> cookie_list=new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String fno=cookies[i].getValue();
					FoodVO vo=fService.foodCookieData(Integer.parseInt(fno));
					cookie_list.add(vo);
				}
			}
		}
		model.addAttribute("cookie_list", cookie_list);
		model.addAttribute("cookie_size", cookie_list.size());
		
		model.addAttribute("main_jsp", "../food/list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra) {
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		ra.addAttribute("fno", fno);
		return "redirect:detail.do";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo=fService.foodDetailData(fno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../food/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("food/cookie_all.do")
	public String food_cookie_all(HttpServletRequest request, Model model) {
		Cookie[] cookies=request.getCookies();
		List<FoodVO> cookie_list=new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String fno=cookies[i].getValue();
					FoodVO vo=fService.foodCookieData(Integer.parseInt(fno));
					cookie_list.add(vo);
				}
			}
		}
		model.addAttribute("cookie_list", cookie_list);
		model.addAttribute("cookie_size", cookie_list.size());
		
		model.addAttribute("main_jsp", "../food/cookie_all.jsp");
		return "main/main";
	}
	
	@GetMapping("food/cookie_delete.do")
	public String food_cookie_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().startsWith("food_")) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		return "redirect:../food/list.do";
	}
}
