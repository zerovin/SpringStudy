package com.sist.main;
/*
 *  라이브러리 VS 프레임워크
 *  
 *  라이브러리 - 자주 사용되는 기능을 모아서 미리 구현해 둔 클래스의 집합
 *            자유롭게 사용가능
 *            자바, Jsoup
 *  프레임워크 - 개발에 필요한 기능을 미리 미리구현해 둔 클래스의 집합
 *            기본틀이 만들어져 있다
 *            XML, Annotation
 *          - 대표적인 프레임 워크
 *            1. MyBatis
 *            2. Ajax, Vue, React => 이미 사용법이 만들어져 있다
 *            3. Spring / Spring-Boot
 *          - 장점
 *            - 기본 틀(형식)이 만들어져 있기 때문에 표준화
 *              같은 형식으로 제작 : 한번 익숙해지면 사용이 편리 => 유지보수시 교육없이 투입가능
 *            - 개발 기간 단축
 *            - 연결관계 단순
 *          - 단점
 *            - 기능이 많기 때문에 전체 사용이 어렵다
 *              Spring-Boot
 *              Spring Framework
 *              Spring Security
 *              Spring Batch
 *              Spring Data
 *              Spring Cloud
 *            - 무거움, 실행속도가 느리다
 *            - 학습해야되는 라이브러리가 많이 존재
 *          - 자바, JSP, DB 통합
 *  Spring에서 사용되는 기능 준비
 *  1. Database
 *     - JDBC
 *     - ORM(Object Relation Mapper) : 관계형 데이터베이스 - MyBatis / JPA / Hibernate
 *  2. Web - MVC => Controller가 이미 제작
 *  3. Core
 *     - Container : 클래스를 모아서 관리 => 스프링은 클래스 관리자(생성/소멸, 객체의 생명주기 관리) => 컨테이너
 *                   개발자가 클래스 등록, 관리 => VO클래스 제외, VO는 사용자정의 데이터형이기 떄문에 관리대상에서 제외
 *                   - 스프링은 형식에 맞게 사용(지정된 형식)
 *                      - 클래스 등록
 *                        - XML을 이용 => Spring 4, Spring 5 자바 이용
 *                            <bean id="aa" class="com.sist.main.AA">
 *                            map.put("aa", new AA())
 *                            AA a=map.get("aa")
 *                            =======================> 실무에서 많이 사용
 *                            @Bean("aa")
 *                            public AA aa(){
 *                              return new AA()
 *                            }
 *                        - Annotation 이용
 *                            @Component("a")
 *                            class A{
 *                            
 *                            }
 *                     - XML/Annotation을 읽어서 Container에 저장
 *                                       | => Spring
 *                                사용자 클래스에서 연결
 *                   1. Container의 종류
 *                      - 클래스의 메모리 할당 (객체 생성)
 *                      - 객체 찾기 => getBean("id")
 *                      - 객체 소멸
 *     - DI : Setter / Constructor / Method
 *            스프링을 통해서 멤버변수 초기화
 *            모든 클래스에 적용
 *            초기화
 *            - setter DI
 *            - 생성자 DI
 *            - method DI => 객체 생성시 init-method
 *                           객체 소멸시 destroy-method
 *     - AOP : 공통모듈 (공통적으로 사용하는 기능을 모아서 자동 호출)
 *             Transaction / Security
 *     - ORM : 데이터베이스 연동 - MyBatis
 *     - MVC : WEB
 *     
 *                                    BeanFactory : Core => DI (객체 생성/소멸/초기화)
 *                                         |
 *                                 ApplicationContext : Core / AOP
 *                                         |=== WebApplicationContext : Core / AOP / MVC
 *                       -------------------------------------
 *                       |                                   |
 *      ApplicationConfigApplicationContext    GenericXmlApplicationContext
 *             Core / AOP / Annotation               Core / AOP / CLOSE
 *      
 *      1. 일반 스프링 => ApplicationContext
 *      2. 웹 => WebApplicationContext
 *      3. 어노테이션 => AnnotationConfigApplicationContext
 *      =====> 클래스 등록 => 필요시 등록된 클래스를 찾아서 사용 => 필요없는 경우 소멸(System.gc())
 *             
 *      class A{
 *      
 *      }
 *      class B{
 *      
 *      }
 *      class C{
 *      
 *      }
 *                                     컨테이너
 *                               ------------------
 *                                  id     class
 *                               -------------------
 *      <bean id="a" class="A">      a     new A()
 *      <bean id="b" class="B">      b     new B()
 *      <bean id="c" class="C">      c     new C()
 *                           ↓↓↓↓↓↓↓
 *                  A aa = 컨테이너.getBean("a") a=아이디명
 *                  => System.gc() => 메모리 해제
 *      생명주기 
 *      1. class 읽기
 *      2. 클래스 메모리 할당
 *      3. setter를 이용해서 초기화
 *      =====> Spring
 *      4. 개발자 사용 - 등록 클래스 찾기
 *      =====> 개발자
 *      5. 사용 후 클래스 메모리 해제
 *      =====> Spring
 *      
 *      class Board{
 *         public void insert(){}
 *         public void list(){}
 *         public void detail(){}
 *         public void update(){}
 *         public void delete(){}
 *      }
 *      
 *      
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//객체생성
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. 컨테이너에 XML파싱 요청
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		// 2. 필요한 객체를 요청
		Board b=(Board)app.getBean("board");
		System.out.println("b = "+b);
		b.list();
		b.insert();
		Board b1=app.getBean("board", Board.class); //제네릭 => 자동 형변환
		System.out.println("b1 = "+b1);
		// 3. 필요에 따라 메소드 호출 후 사용
		b1.list();
		b1.insert();
	}

}
