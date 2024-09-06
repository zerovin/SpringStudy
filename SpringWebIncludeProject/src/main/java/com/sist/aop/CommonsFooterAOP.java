package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

/*
 *  1.스프링 = 프레임워크
 *  
 *  라이브러리 - 자주 사용되는 기능들을 모아서 미리 구현한 클래스의 집합
 *            개발자마다 다르게 구현 가능
 *            레고
 *  프레임워크 - 미리 구현된 클래스의 집합
 *            기본 틀이 만들어져 있다 => 기본 틀에 맞게 구현
 *              ex) Controller => @GetMapping, @PostMapping
 *            개발자마다 형식 동일하게 구현
 *            조립식 ex)메인보드
 *            개발자의 수준과 실력에 상관없이 일정 수준의 개발 가능
 *            초보자도 많은 시간을 투자할 필요없이 유지보수 가능
 *            많은 기능을 가지고 있다 => 학습하기에 시간이 걸린다
 *            프로그램이 무겁다 => 속도 저하
 *            무료 => 개발 시간 단축
 *            MVC는 이미 제작 => 기능만 구현
 *            
 *  스프링의 기본 기능
 *  - DI : 클래스를 모아서 관리하는 목적, 객체 생명주기 관리
 *         메모리 할당 (new 대체) => 생성 - 필요한 경우 멤버변수의 초기화 가능
 *                                     1. setter DI - p:
 *                                     2. 생성자 DI - c:
 *                               소멸 
 *         관리하는 클래스 : VO(사용자 정의 데이터형), 인터페이스(생성불가능) 제외
 *         클래스 한개에 대한 관리 요청 : <bean id="" class=""/>
 *         패키지 단위로 관리 : <component-scan base-package="패키지명">
 *                         => 선택, 클래스마다 구분
 *                            @Componenet : 일반 클래스 => MainClass, Jsoup
 *                            @Repository : DAO
 *                            @Service : DAO 여러개 등록 => BI
 *                            @Controller : Model(요청처리) => 화면제어 
 *                            @RestController : Model(요청처리) => 데이터 전송 Vue, Ajax
 *                            @ControllerAdvice : 공통 예외처리
 *  - AOP : 프로그램 개발시 발생하는 소스 중복 제거, 공통기능 적용 => 자동화 처리
 *          어떤 메소드(PointCut)에 어떤 위치(JoinPoint)에 기능을 수행할지 지정
 *          public void aaa(){} => before
 *          public void bbb(){} => after-throwing
 *          public void ccc(){} => after
 *          public void ddd(){} => after-returning
 *          
 *          public void display(){
 *             try{
 *                aaa() => before
 *             }catch(Exception e){
 *                bbb() => after-throwing
 *             }finally{
 *                ccc() => after
 *             }
 *             return  ddd() => after-returning
 *          }
 *  - MVC    
 *       브라우저 요청(URL주소 이용 .do)              @Get/PostMapping에서 해당 URL 찾기    return값 받아 JSP찾기      화면출력
 *    클라이언트 ============== DispatcherServlet ========== HandlerMapping ============ ViewResolver ======== JSP 
 *                                                               |
 *                                                             요청처리
 *                                                      @Controller => Model
 *                                                      오라클에서 데이터 읽어 옴
 *                                                      1) 요청값 받기 request / 매개변수(권장)
 *                                                      2) DB연동
 *                                                         서비스 계층 === 데이터 액세스 계층
 *                                                      3) 결과값 전송
 *                                                         
 *                                                      
 *  - 데이터베이스 : ORM (MyBatis)
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;
@Aspect //공통모듈(반복제거), 메모리할당은 아님
@Component //메모리 할당
public class CommonsFooterAOP {
	@Autowired
	private RecipeService rService;
	
	//수행되는 위치 => finally{메소드 수행} 오류와 상관없이 무조건 수행
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void commonsFooterData() {
		List<FoodVO> foodList=rService.foodTop5Data();
		List<RecipeVO> recipeList=rService.recipeTop5Data();
		
		//전송 => request
		//Controller가 아닌 곳에서는 request 매개변수로 받기 불가
		//Controller가 아닌 곳에서 현재 사용하는 request를 받아오는 방법 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("foodList", foodList);
		request.setAttribute("recipeList", recipeList);
		
	}
	
}
