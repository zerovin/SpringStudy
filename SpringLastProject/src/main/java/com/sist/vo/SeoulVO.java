package com.sist.vo;

import lombok.Data;

/*
NO      NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(200)  
POSTER  NOT NULL VARCHAR2(500)  
MSG     NOT NULL VARCHAR2(4000) 
ADDRESS NOT NULL VARCHAR2(300)

	  Spring
	  1.MVC
	  2.DI : 클래스 설계 => 메모리할당
	                       @Component
	                       @Repository
	                       @Service
	                       @Controller / @RestController
	                     자동주입
	                       @Autowired
	                       @Resource - 1.8이전
	  3.AOP : Transaction / Security / Commons
	  4.ORM => MyBatis ===> JPA
	  오라클 => MySQL
	  
	  MVC => CRUD 게시판, 댓글
	         목록 페이징, 상세
	  
	  Spring
	  1) DI / AOP
	  2) Transaction
	  3) MVC구조
	  4) Session Vs Cookie
	  5) DAO vs Service
	  
	  Java
	  1) 객체지향(캡슐화/은닉화, 상속/포함, 오버라이딩/오버로딩/)
	  2) Collection
	  3) 추상클래스 vs 인터페이스
	  4) 쓰레드 (동기화/비동기화)
	  
	  HTML/JSP/CSS
	  1) GET/POST
	  2) Session vs Cookie
	  3) JSTL/EL
	  4) MVC구조 model1 vs model2
	  
	  JavaScript
	  1) 클로저
	  2) ES6 장점, 변경사항 let/const/var
	  
	  Oracle
	  1) JOIN/SUBQUERY
	  2) 프로시저 vs 트리거 
	  
	  포트폴리오 내용
	  
	  스프링 : 객체생성~객체소멸
	         => 기본 모든 클래스를 관리
	            VO => 데이터형 (사용자)
	            Mapper, Service => 인터페이스, 연결용, 낮은 의존성
	         => 재사용 기법 : 싱글턴
	         => @Autowired : 스프링에서 메모리 할당된 클래스 안에서만 찾아서 주소 대입
	  @Component => 싱글턴
	  @Scope("prototype") => 요청시마다 새롭게 메모리 할당
	  class A{}
	  
	  VO
	  DTO
	  ====> 데이터를 모아서 전송
	  ENITY - 데이터베이스 컬럼과 연동
	  
	  Spring => VO => MyBatis
	  Spring-Boot=> Entity => JPA
 */
@Data
public class SeoulVO {
	private int no;
	private String title, poster, msg, address;
}
