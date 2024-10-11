package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChefController {
	/*
	 *   리턴형
	 *   - String : 화면 변경 => forward / redirect
	 *   - void : download 처리
	 *    
	 *   매개변수
	 *   - 일반 데이터형, VO, List, 배열, 내장객체
	 *   
	 *   내장객체
	 *   - HttpServletRequest => Cookie 읽기
	 *   - HttpServletResponse => Cookie 전송
	 *   - HttpSession => 로그인
	 *   - RedirectAttributes => sendRedirect에 데이터 전송
	 *   - Model => 전송 객체 => forward 전송
	 */
	@GetMapping("chef/list.do")
	public String chef_lsit() {
		return "chef/make";
	}
}
