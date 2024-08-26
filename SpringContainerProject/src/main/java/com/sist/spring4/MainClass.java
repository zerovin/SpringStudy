package com.sist.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  1. XML 사용법 - 등록
 *  2. 구분 - 어노테이션
 *  =================== 사용법
 *  3. 기능
 *     - DI
 *     - AOP
 *     - MVC
 *     - Transaction
 *     - Security
 *     - WebScoket
 *     - Betch : 스케줄러 
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application.xml");
		Sawon sa=(Sawon)app.getBean("sa");
		sa.setSabun(5);
		sa.setName("강감찬");
		sa.setDept("총무부");
		//System.out.println(sa); //com.sist.spring4.Sawon@7f3b84b8 주소 출력
		//setter 초기화
		sa.print();
		
		System.out.println(sa);
		Sawon sa1=(Sawon)app.getBean("sa");
		sa1.print();
		//System.out.println(sa1);
		Sawon sa2=(Sawon)app.getBean("sa");
		sa2.print();
		//System.out.println(sa2);
		Sawon sa3=(Sawon)app.getBean("sa");
		sa3.print();
		//System.out.println(sa3);
		Sawon sa4=(Sawon)app.getBean("sa");
		sa4.print();
		//System.out.println(sa4);
		//싱글턴 - 다른 객체를 불러도 하나의 메모리를 할당하기에 같은 값 반환
		
		//생성자 초기화
		Sawon sa5=(Sawon)app.getBean("sa2");
		sa5.print();
	}

}
