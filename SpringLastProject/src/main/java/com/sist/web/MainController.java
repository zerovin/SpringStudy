package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//jsp 연동
@Controller
@RequestMapping("main/")
public class MainController {
	// 필요한 클래스를 스프링에서 가지고 온다 (객체주소)
	// 사용자 요청에 따라 처리
	@GetMapping("main.do")
	public String main_main() {
		return "main";
	}
}
