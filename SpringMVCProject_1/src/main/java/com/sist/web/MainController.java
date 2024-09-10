package com.sist.web;
/*           .do                       매개변수
 *  브라우저 ======== DispatcherServlet ======== Model (Model관리자 : HandlerMapping)
 *                      Controller             처리
 *                                              |
 *                                JSP 관리 => JSP를 찾아서 request 전송 : ViewResolver
 *                                              |
 *                                           JSP로 전송 (View)
 *                                           
 *  1. DispatcherServlet : 사용자의 요청을 받아서 처리할 데이터를 넘겨주는 역할
 *  2. HandlerMapping : DispatcherServlet으로부터 받은 데이터를 받아서 처리 => request에 담아서 전송
 *                      - Model, @Controller : DispatcherServlet 위임을 받아서 처리
 *                      - 기능 수행하는 메소드 찾기
 *                        구분자 => @GetMapping / @PostMapping / @RequestMapping
 *  3. ViewResolver : request를 받아서 JSP를 찾고 전송
 *                    - JSP의 경로명 지정 : prefix=""
 *                    - 확장자(ASPX) : suffix="확장자" => 스프링은 자바 전옹X
 *  4. View (JSP) : 화면출력 (개발자 담당)
 *  
 *  개발자 담당
 *  - Model (@Controller) => DAO, Service, VO, Manager
 *  - JSP
 *  - 웹, 지능형 웹(AI) : 화면 출력에 대한 속도 (Front) => Angular, Vue, React(Ajax를 대체하는 Front)
 *  - 스프링
 *    - DI : 개발자가 만드는 클래스 / 라이브러리 클래스를 제어
 *           클래스 - 멤버변수에 대한 초기화
 *                  명시적 초기화 - 개발자
 *                    ex) class A{int a=10}
 *                  생성자 - DI
 *                    ex) c:
 *                  setter - DI
 *                    ex) p:
 *    - AOP : 공통적으로 사용하는 기능을 자동으로 호출가능 => 공통 모듈
 *            메소드 위치
 *            반복 => AOP이용 자동 호출
 *            핵심 => 코딩
 *            ===> MyBatis
 *              ex) public void dbInsert(){
 *                     try{
 *                        getConnection(); => Before
 *                        conn.setAutoCommint(False); => Around
 *                        insert => 핵심코딩
 *                        insert => 핵심코딩
 *                        conn.commit(); => Around
 *                     }catch(Exception ex){
 *                        ex.printStackTrace(); => after-throwing
 *                        conn.rollback();
 *                     }finally{
 *                        conn.setAutoCommit(true); => after
 *                        disConnection();
 *                     }
 *                     return; => 정상수행 => after-returning
 *                  }
 *    - MVC : 분리해서 작업
 *            Front - view단
 *            Back - Model - server단
 *                   DAO - db단
 *    - Interceptor
 *      사용요청 ======== DispatcherServlet ========= HandlerMapping
 *                                                       | ===> 인터셉트 => 자동로그인
 *                                                   개발자 Model
 *                                                       | ===> 인터셉트 
 *                                                  ViewResolver
 *                                                       | ===> 인터셉트 => 로그인 여부 확인
 *                                                   개발자 JSP
 *    ================================================================== SpringFramework
 *    - security : 보안
 *    - batch : 스케줄러
 *    - data : 분석
 *    
 *  1.라이브러리 추가 : pom.xml : maven / gradle : properties파일, boot에서 주로 사용
 *  2.버전 변경 : 기본 1.6 => 1.8이상
 *  3.web.xml 변경
 *  4.패키지 => 클래스
 *  5.메모리 할당 요청 => application.xml
 *  6.클래스마다 어노테이션 올리기
 *  7.DB연동
 *  8.JSP에서 화면 출력
 *  실무 => 기본 셋팅 외 4,6,7,8 위주
 */
public class MainController {

}
