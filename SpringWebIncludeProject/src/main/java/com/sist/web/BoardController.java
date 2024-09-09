package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sist.commons.CommonsPage;
import com.sist.service.*;
import com.sist.vo.*;

//조립 => Service + VO + DAO => 결과값 추출 => JSP로 전송
@Controller //DispatcherServlet과 연결
/*
 * MVC 순서
 *  1. 요청받기 => DispatcherServlet
 *  2. Model 조회 => HandlerMapping
 *  2-1. Model 메소드 호출 => HandlerMapping
 *  3. JSP 찾기 => ViewResolver
 *  3-1. JSP로 request 전송 => ViewResolver
 *  4. JSP에 request 받아서 화면출력 
 *  5. 브라우저에서 읽기
 *  
 *  Model =========> Service =========> DAO (유지보수) ========> 오라클
 *        <=========         <=========             <========
 *  * 결합성이 낮은 프로그램 : 수정시 다른 클래스에 영향이 없게 만든다 (***POJO 독립적인 클래스) - 스프링의 기조
 *  
 *   => 스프링 : Model - 화면제어 : @Controller
 *                     데이터제어 : @RestController
 *                     @GetMapping / @PostMapping
 *                     리턴형 : void / String
 *                     매개변수 : getParameter를 대체
 *             JSP - 화면유지 : 자바스크립트 (ajax, vue, react)
 *                   다른 화면으로 변경 : <a>, <form>
 *             DAO - Mapper(SQL)
 *             VO, Service
 */
public class BoardController {
	//객체를 이용해서 @Autowired를 사용해서 주소값 받을시 속도가 늦음
	//스프링이 private 무시하고 주소값 공유/노출 => 캡슐화 깨짐
	//가급적 생성자 이용해서 @Autowired 사용
	private BoardService bService;
	
	@Autowired
	public BoardController(BoardService bService) {
		this.bService=bService;
	}
	
	@GetMapping("board/list.do") //사용자가 게시판 목록을 요청했다면 => 조건문
	//어노테이션 = if문 => 스프링에서 찾아서 처리
	public String board_list(String page, Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<ReplyBoardVO> list=bService.boardListData(start, end);
		int count=bService.boardRowCount();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		count=count-((curpage*rowSize)-rowSize);
		model.addAttribute("list",list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("count", count);
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("type", "관리자가 삭제한 게시물입니다");
		
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert(Model model) {	
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(ReplyBoardVO vo) {
		bService.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	
	
	/*
	 *  매개변수 : 순서상관X
	 *           사용자가 요청한 값 - String / int / String[] / VO
	 *           라이브러리 클래스(내장객체) - HttpServletRequest - Cookie 읽기
	 *                                  HttpServletResponse - Cookie 저장, 다운로드
	 *                                  HttpSession
	 *                                  Model - 데이터 전송 객체
	 *                                  RedirectAttribute - 화면변경
	 *                                  보안클래스 - 보안
	 *  리턴형 : String - request를 전송 : forward => "경로명/JSP명"
	 *                  이전 화면으로 이동 : sendRedirect => "redirect:~.do", request전송X, 화면변경
	 *         void - ajax, 다운로드, 스케줄러(task)
	 *  메소드명 : 개발자가 설정 => @GetMapping("URL주소")
	 *  
	 *  ex) detail.do?no=10
	 *        String no => Integer.parseInt(no) 모든 데이터는 String으로 받을 수 있다
	 *        int no
	 *      데이터가 많은 경우 
	 *        VO***
	 *        List => 파일 멀티업로드
	 *        String[] => checkbox
	 *  오류
	 *  - 404 : 파일이 없는 경우, 경로확인, 파일 존재여부
	 *  - 500 : 소스 오류, SQL 문장 ex)null일 때 String 메소드 이용
	 *  - 400 : Bad Request => 매개변수의 데이터형이 다른 경우
	 *  - 405 : Get - @GetMapping / Post(<form>,ajax, axios) - @PostMapping 안 맞을 경우
	 *  - 403 : 접근 거부, 권한부여 => security
	 *  - 412 : UTF-8 => 한글 변환 코드가 틀린 경우
	 *  
	 */
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		ReplyBoardVO vo=bService.boardDetailData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/detail.jsp");
		//request.setAttribute()
		/*
		 *  request/response 사용하면 안되는 이유
		 *  - 사용자 정보(IP, 컴퓨터에 대한 정보)를 가지고 있다 => 정보 노출 가능
		 *  - 스프링 5 => 보안강조 => request, response 사용빈도 거의 없음 (Cookie 이용시 사용 @Cookie 있긴함)
		 *                        xml을 사용하지 않는다 => 자바 설정파일이 생성 
		 */
		return "main/main"; // forward => request 전송 => 클래스화 => Model => model.addAttribute()
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		ReplyBoardVO vo=bService.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/update.jsp");
		return "main/main";
	}
	
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/reply.jsp");
		return "main/main";
	}
	
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int pno, ReplyBoardVO vo) {
		bService.boardReplyInsert(pno, vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		return "main/main";
	}
}
