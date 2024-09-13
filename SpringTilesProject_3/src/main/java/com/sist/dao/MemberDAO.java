package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
/*
 *  스프링에 관리(클래스)
 *  - DAO => @Repository 
 *  - Service => @Service
 *  - Manager => @Component 일반클래스
 *  - Controller => @Controller
 *  - Interceptor => xml <bean>
 *  - AOP => @Component 일반클래스
 *  
 *  메모리할당 제외
 *  interface : 메모리할당이 불가능
 *  ~VO : 클래스 => 데이터형
 *  
 *  클래스
 *  - 데이터형 클래스 => 사용자 정의 데이터형, 변수로 구성
 *                   ~VO, ~DTO, ~Bean
 *                   데이터 여러개를 묶어서 한번에 관리
 *  - 액션 클래스 => 기능을 가지고 있는 클래스, 메소드로 구성 => 스프링이 관리
 */
@Repository //메모리 할당 요청 => 스프링이 관리
public class MemberDAO {
	@Autowired //스프링에서 메모리 할당이 된 경우에만 사용가능
	private MemberMapper mapper;
	
	/*
	@Select("SELECT COUNT(*) FROM project_member "
			+ "WHERE id=#{id}")
	public int memberIdCount(String id);
	*/
	public int memberIdCount(String id) {
		return mapper.memberIdCount(id);
	}
	
	/*
	//PWD 검색
	@Select("SELECT pwd, id, name, admin FROM project_member "
			+ "WHERE id=#{id}")
	public MemberVO memberGetPassword(String id); 
	 */
	public MemberVO memberGetPassword(String id) {
		return mapper.memberGetPassword(id);
	}
}
