package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	@GetMapping("member/login.do")
	public String member_login() { //자동로그인
		return "member/login";
	}
	
}
