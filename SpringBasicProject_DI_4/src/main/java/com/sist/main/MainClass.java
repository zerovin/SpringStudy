package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.model.*;
/*
 *  Spring - Web 라이브러리
 *           클래스 관리 => 메모리 관리 용이 (생성부터 소멸까지)
 *                       결합성이 낮은 프로그램 => 다른 클래스에 영향을 미치지 않는다
 *                       ==> 유지보수, 상속이 없다, 상속 내리는 클래스에 맞게 소스 코딩
 *                           스프링에서는 상속 받는 일이 극히 드물다 POJO (2.5부터) => 일반자바
 *                       컨테이너 - 경량 / 클래스화
 *                                툴 형식 => 웹로직 / 웹스퍼어 / 제우스 => 유료
 *                                학교, 은행, 공기업, 대기업 (유지보수) => 스프링
 *           1. 프로젝트에 맞는 클래스 제작
 *           2. 저장 => 클래스와 클래스의 연결 관계 설정 - .xml, annotation
 *           
 *   
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		BoardModel bmodel=(BoardModel)app.getBean("board");
		bmodel.list();
		MemberModel mmodel=(MemberModel)app.getBean("member");
		mmodel.list();
		MainModel mainmodel=(MainModel)app.getBean("main");
		mainmodel.list();
		FoodModel fmodel=(FoodModel)app.getBean("food");
		fmodel.list();
		RecipeModel rmodel=(RecipeModel)app.getBean("recipe");
		rmodel.list();
	}

}
