package com.sist.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// main.do => login(자동로그인)
/*                     Front Controller : 요청받기, 결과값 전송
 *     main.do  ============= DispatcherServlet ================ HandlerMapping(Model찾기)
 *  사이트 시작 요청        => 기능처리에 대한 위임 @Controller            Model 찾기 전 
 *                                                             ==> preHandel() Mapping 찾기전에 처리할 수 있는 인터셉트 
 *                                                         어노테이션 @GetMapping @PostMapping @RequestMapping
 *                                                              => return "main/main"
 *                                                             ==> postHandle() ViewResolver 전 처리, 자동로그인(쿠키)
 *                                                              => ViewResolver (JSP찾기 => request)
 *                                                              => request
 *                                                             ==> after Completion() 정상수행 후 JSP출력 전 처리, 권한별로 처리
 *                                                              => JSP
 */
public class MainInterceptor extends HandlerInterceptorAdapter{
	//GetMapping, PostMapping, RequestMapping 찾기 전 수행문장이 있는 경우
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("========== preHandle() Call ==========");
		return super.preHandle(request, response, handler);
	}

	// Mapping 수행 후 return "main/main" => ViewResolver로 전송하기 전, 권한처리/보안/자동로그인/ID저장
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("========= postHandle() Call ==========");
		super.postHandle(request, response, handler, modelAndView);
	}

	// ViewResolver가 JSP로 request 전송 전
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("======= afterCompletion() Call =======");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
