package com.sist.main;
/*
 *  maven => ant
 *  - 라이브러리 관리 : pom.xml
 *  - 프로젝트 관리 => 버전 관리
 *  - 배포 관리 => 자동으로 war(웹)
 *    war => 원격 AWS운영체제로 전송 => tomcat
 *    AWS 운영체제(리눅스)를 빌려서 사용, 각자 IP 따로 생성
 *    
 *  스프링 - DI*** / AOP(개념파악) / MVC-인터셉트/ MyBatis(ORM)-인터셉트,Transaction => security / websocket
 *  
 *  DI
 *  - 클래스 등록
 *    - <bean id="" class=""> 한개의 클래스 등록
 *    **<context:component-scan base-package=""> 여러개 클래스 동시에 메모리 할당
 *      반드시 메모리 할당할 클래스를 구분
 *      class 위에 어노테이션을 이용해서 구분
 *      => 클래스 유형별로 구분
 *         일반 클래스 : @Component - MainClass, ~Manager
 *         데이터베이스 연동 : @Repository - ~DAO
 *         관련된 DAO 통합 : @Service
 *         일반 JSP 제어 : @Controller forward/redirect - Model
 *         자바스크립트, 코틀린 연동 : @RestController JSON, RestFul - Model
 *                               RestFul - 다른 언어와 연동, CRUD, 
 *                                          GET : SELECT
 *                                          POST : INSERT
 *                                          PUT : UPDATE
 *                                          DELETE : DELETE
 *         통합 예외처리 : @ControllerAdvice
 *    - XML없이 사용
 *      @Bean => 자바로만 코딩하는 방식
 *      
 *  **스프링은 컨테이너 - 클래스를 모아서 관리(객체 생성 ~ 객체 소멸, 객체의 생명주기 담당)
 *                    객체 생성 - 멤버변수 초기화 => DI
 *                    멤버변수 초기화 - 1.setter : setXxx()
 *                                    ex) p:name => setName()
 *                                  2.생성자 이용
 *                                    ex) e:name => 생성자(String name)
 *                                - 사용자정의 클래스에서는 사용빈도 낮음
 *                                  데이터베이스 연동시 오라클 정보를 시작과 동시에 전송
 *    MVC는 스프링의 일부 라이브러리
 *    
 *  개발자가 문제 제기
 *  1) 언어통합 - 한가지 언어로 사용
 *              => XML + Java => Java
 *  2) 보안유지가 어렵다 - XML은 코드가 노출됨
 *                     => Java - 컴파일.class
 *  ===> 스프링 5버전에서 변경, 이전버전도 호환가능
 *       스프링 6은 호환X javax => jakarata
 *       
 *  MyBatis : @Select("") 매개변수 반드시 1개 => 최근 여러개 사용가능 
 *            복잡한 SQL문장(동적쿼리) => XML문장을 어노테이션으로 
 *            PROCEDURE / TRIGGER => 고급 SQL
 *  
 */
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO2 dao=(EmpDAO2)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getSal());
		}
	}

}
