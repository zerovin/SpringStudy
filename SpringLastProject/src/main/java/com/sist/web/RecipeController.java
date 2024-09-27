package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //DispatcherServlet
public class RecipeController {
	// return = "폴더명/파일명"
	// .do => 라우터 route
	@GetMapping("recipe/list.do")
	public String recipe_list() {
		return "recipe/list";
	}
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no, Model model) {
		model.addAttribute("no", no);
		return "recipe/detail";
	}
}
