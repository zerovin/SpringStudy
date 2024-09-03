package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
//예외처리를 공통으로 사용 => Controller에서 특별한 경우가 아니면 예외처리를 하지 않는다 
//RestController 제외, RestController는 RestControllerAdvice
@ControllerAdvice
public class ControllerCommonsException {
	
}
