package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 *  정리
 *  스프링
 *    - DI : 스프링은 클래스를 관리하는 영역
 *           1) 클래스 한개 : Conponent
 *           2) 클래스 여러개 : Container
 *           => 스프링 = Container (클래스로 제작됨 - 경량 컨테이너)
 *           클래스를 관리하기 위해서는 객체생성/객체소멸 필요 => 객체 생명주기 관리
 *           객체 생성시 멤버변수의 초기화 필요시에 사용
 *           변수 초기화 - setterDI => p:name => setName()
 *                    - 생성자 DI => c:
 *    - AOP : 공통모듈 - 실행시마다 호출하는 기능이 있는 경우 자동호출 가능
 *                     사용자 정의보다 트랜잭션 / 보안 / 로그
 *    - MVC : 웹 - 라이브러리
 *    - 라이브러리 : 수정하지 않고 있는 그대로 사용 => 사용법 / 어떤 기능을 쓸지 / 찾기
 *                ex) ORM(Mybatis)
 *    =================================================
 *    - 프레임 워크 : 기본 동작을 위한 틀이 만들어져 있다
 *                 형식에 맞게 셋팅해서 사용 => xml / 어노테이션
 *                 1) 라이브러리 추가 : pom.xml / gradle
 *    =================================================
 *  MVC
 *   - Model : @Controller / @RestController
 *             사용자의 요청을 받아서 처리 결과를 JSP로 전송하는 역할 => @Controller
 *                                       자바스크립트로 전송 => @RestController
 *             관리 - HandlerMapping -> 해당 메소드 찾기
 *   - View : JSP (HTML)
 *            ViewResolver : JSP를 찾아서 request 전송
 *   - Controller : 사용자 요청을 받는 클래스 - 이미 스프링에서 제공
 *                  DispatcherServlet : 매뉴얼만 제작
 *                  web.xml
 *   - WebApplicationContext : 사용자 정의 클래스를 관리
 *                             클래스를 등록
 *                             application-context.xml
 *                             application-datasource.xml
 *                             application-security.xml
 *                             => 넘겨주는 방법
 *                                <init-param>
									  <param-name>contextConfigLocation</param-name>
									  <param-value>/WEB-INF/config/application-*.xml</param-value> <!-- xml 파일 경로지정 -->
								  </init-param>
	 - 사용자 요청 => DispatcherServlet => HandlerMapping => Model처리 ========>  ViewResolver =================> JSP
	                                  요청처리하는 메소드 찾기           request   p:prefix => 경로     request
	                                  => @GetMapping(URI)                    p:suffix => 확장자
	                                  => @PostMapping(URI)
	                                  => @RequestMapping(URI)							  
 *   - .do
 *     - 요청에 대한 처리
 *       1) 데이터베이스 (MyBatis) - DAO => 테이블 1개
 *                                Service => 관련된 DAO 여러개를 묶어서 처리
 *       2) 외부 API - 날씨, 뉴스, 메일
 *       3) 전송 - redirect : 기존에 있는 메소드를 재호출 => _ok (DML => INSERT/UPDATE/DELETE)
 *                           return "redirect:list.do"
 *                forward : 해당 데이터 전송 (SELECT)
 *                          return "경로/파일명"
 *     - 화면 출력
 *       1) JSP => EL/JSTL
 *       2) 자바스크립트 - Ajax / VueJS / ReactJS
 *          List : []
 *          VO : {}
 *          =========== JSON (jackson) spring-boot에서는 자동처리
 *          일반 데이터형 : 정수/실수/논리/문자
 *          
 *  *기본동작
 *  *xml 설정파일이 뭘 의미하는지
 *  *Model / DAO / Service / JSP
 *  *기본 이론(면접)
 */
//jsp 연동
import java.util.*;
import com.sist.vo.*;
import com.sist.manager.NaverNewsManager;
import com.sist.service.*;
@Controller
public class MainController {
	// 필요한 클래스를 스프링에서 가지고 온다 (객체주소)
	@Autowired
	private RecipeService rService;
	@Autowired
	private FoodService fService;
	@Autowired
	private NaverNewsManager mgr;
	
	// 사용자 요청에 따라 처리
	@GetMapping("main/main.do")
	public String main_main(String fd, Model model) {
		if(fd==null) {
			fd="맛집";
		}
		List<NewsVO> nList=mgr.newsFind(fd);
		
		RecipeVO rvo=rService.recipeMaxHitData();
		List<RecipeVO> rList=rService.recipeHitTop8();
		List<FoodVO> fList=fService.foodHitTop5();
		ChefVO cvo=rService.chefToday();
		model.addAttribute("rvo", rvo);
		model.addAttribute("cvo", cvo);
		model.addAttribute("rList", rList);
		model.addAttribute("fList", fList);
		model.addAttribute("nList", nList);
		return "main";
	}
	
	@GetMapping("chat/chat.do")
	public String chat_chat() {
		return "site/chat/chat";
	}
}
