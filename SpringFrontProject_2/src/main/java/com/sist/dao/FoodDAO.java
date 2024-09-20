package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
/*
 *  1. 스프링 어노테이션
 *     - 메모리 할당 => 스프링에 클래스 관리(생성~소멸) 요청
 *                   필요시에 주소값 얻어 사용가능
 *       구분 - DAO => @Repository : 저장소, 데이터베이스 연결
 *             SERVICE => @Service : BI (DAO 여러개 통합)
 *             MODEL => @Controller : 화면제어
 *                      @RestController : 자바스크립트, 코틀린 등 다른 언어와 연결, JSON
 *             Exception => @ControllerAdvice : 공통 예외, 모든 모델 클래스의 예외처리를 한번에 처리
 *                          @RestcontrollerAdvice
 *             일반 클래스 OpenAPI => @Component
 *     - 모든 클래스를 메모리 할당 요청하지는 않는다
 *       - ~VO : 사용자 정의 데이터형, 메모리 할당X
 *       - 인터페이스 : 메모리 할당X
 *     - 공통으로 사용되는 기능을 모아서 관리 => 공통모듈 AOP
 *       - @Aspect
 *     - 자바로 환경 설정
 *       - @Bean
 *     - React 연결 : port가 다름 => 서버연결을 할 수 없다 => 포트 허용
 *     - 보안 / 스케줄러
 *      
 */
@Repository
public class FoodDAO {
	@Autowired //스프링에서 구현된 객체 주소를 받는다 => 자동주입
	private FoodMapper mapper; 
	
	/*
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	//여러개의 매개변수 => @Param, Map, VO
	*/
	public List<FoodVO> foodListData(int start, int end){
		return mapper.foodListData(start, end);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house")
	public int foodTotalPage(); 
	 */
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	/*
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindListData(Map map);
	*/
	public List<FoodVO> foodFindListData(Map map){
		return mapper.foodFindListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindTotalPage(Map map);
	 */
	public int foodFindTotalPage(Map map) {
		return mapper.foodFindTotalPage(map);
	}
}
