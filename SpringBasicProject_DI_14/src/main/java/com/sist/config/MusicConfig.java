package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//스프링 라이브러리 연결 => XML, Annotation
//프레임워크는 기본 틀 제작 => 틀에 맞게 소스 코딩, 코딩은 단순
/*
 * 기능처리 클래스 : 데이터베이스 연동(DAO), Jsoup(Component), Model
 * @Component
 * @Repository
 * @Service
 * ==================
 * @Controller
 * @RestController
 * @ControllerAdvice
 * ================== 웹
 * @configuration
 * ==> 클래스 위에 설정된 경우 메모리 할당 (객체 생성)
 *     선택적 메모리 할당
 *     
 * 일반 스프링
 *   - DI : 클래스 설정 (등록)
 *   - AOP : 공통 모듈 설정 => CommonsModel => 자동호출 (콜백)
 *     AOP와 인터셉트 차이점   
 *   - MVC => ORM(MyBatis)
 *   --------------------
 *   - Security
 *   - Batch
 *   - Cloud
 *   - AI
 */

//클래스 등록 파일
//application-context.xml
@Configuration
@ComponentScan(basePackages = {"com.sist.*"})
//<context:component-scan base-package="com.sist.*"/>
public class MusicConfig {

}
