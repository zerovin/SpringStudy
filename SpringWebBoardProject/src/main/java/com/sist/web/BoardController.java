package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller //모델클래스 => HandlerMapping에서 처리 , HandlerMapping은 Controller/RestController만 찾음
public class BoardController {
	//필요한 객체를 스프링에서 얻어온다
	@Autowired
	private BoardDAO dao; //new 주면 안됩니당
	
	//가급적 RequestMapping(GET+POST) 말고 GetMapping / PostMapping 사용권장
	@GetMapping("board/list.do")
	/*
	 *  메소드 규칙
	 *  - 리턴형
	 *    - String : 파일 지정, redirect
	 *    - void : 파일 다운로드, Ajax, vueJS => JSON 전송
	 *  - 매개변수
	 *    - getParameter() => 변수, VO단위, List단위, 배열단위
	 *    - 내장객체 HttpServletReqeust, HttpServletResponse, HttpSession, Model model, RedirectAttributes
	 *             순서 상관없
	 *  - 메소드명 : 개발자 재량
	 *  
	 *  한글 변환 - web.xml에 등록
	 *            setCharacterEncoding을 사용하지 않는다	
	 */
	public String board_list(String page, Model model) {
		//매개변수 - 사용자로부터 받는 값, Model(결과값 전송 객체)
		//String page=request.getParameter("page") => dispatcherServlet이 매개변수로 받아줌
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<BoardVO> list=dao.boardListData(start, end);
		
		//총페이지
		int count=dao.boardRowCount();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		count=count-((rowSize*curpage)-rowSize);
		
		//출력에 필요한 데이터를 list.jsp로 전송
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		/*
		 *  public void addAttribute(String key, Object obj){
		 *  	request.setAttribute(key, obj);
		 *  } 
		 */
		return "board/list"; //.jsp붙이면 application-context.xml에서 붙인 .jsp에 붙어 .jsp가 되서 404오류
	}
	
	// 게시판 입력창 요청
	@GetMapping("board/insert.do") // GET/POST 안맞으면 405 오류, 매개변수오류 400
	public String board_insert() {
		return "board/insert";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) { //커맨드 객체 => VO단위로 값을 받는 경우
		// dao연동
		dao.boardInsert(vo);
		// 비밀번호 암호화
		return "redirect:list.do";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		// 데이터 읽기 상세보기
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		//데이터베이스 연동
		BoardVO vo=dao.boardUpdateData(no);
		//결과값 전송
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	/*
	@PostMapping("board/update_ok.do")
	@ResponseBody //자바스크립트나 JSON 전송시 사용 => @RestController
	public String board_update_ok(BoardVO vo, Model model) {
		String js="";
		//데이터베이스 연동
		boolean bCheck=dao.boardUpdate(vo);
		//결과값 전송
		//model.addAttribute("bCheck", bCheck);
		//model.addAttribute("no", vo.getNo());
		
		//이동화면이 두가지 이전(자바스크립트)/상세보기
		//@Controller => 무조건 return은 파일명이나 .do
		//자바스크립트 전송 => 실행 @ResponseBody 사용 / @RestController클래스 생성
		//@ResponseBody => 한글이 깨진다 => 영분으로 전송
		
		if(bCheck==true) {
			js="<script>"
				+ "location.href=\"detail.do?no="+vo.getNo()+"\";"
				+ "</script>";
		}else {
			js="<script>"
				+ "alert(\"Password Fail\");"
				+ "history.back();"
				+ "</script>";
		}
		//return "board/update_ok";
		return js;
	}
	*/
	
	@GetMapping("board/delete.do")
	// 405 - 허용되지 않는 메소드 GET/POST
	// 400 - 잘못된 요청(Bad Request) 매개변수 데이터형 오류
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
	
	/*
	@PostMapping("board/delete_ok.do")
	@ResponseBody
	//getParameter() => 스프링(DispatcherServlet)
	public String board_delete_ok(int no, String pwd) {
		String js="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck==true) {
			js="<script>"
				+ "location.href=\"list.do\";"
				+ "</script>";
		}else {
			js="<script>"
				+ "alert(\"Password Fail\");"
				+ "history.back();"
				+ "</script>";
		}
		return js;
	}
	*/
}