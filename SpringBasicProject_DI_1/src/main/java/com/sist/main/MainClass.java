package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 컨테이너에 클래스 등록
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		// classpath : 자동 인식하는 위치 => src/main/java
		Sawon sa1=(Sawon)app.getBean("sa1");
		//System.out.println("sa1 = "+sa1);
		sa1.print();
		System.out.println("================");
		Sawon sa2=(Sawon)app.getBean("sa2");
		//System.out.println("sa2 = "+sa2);
		sa2.print();
		System.out.println("================");
		Sawon sa3=(Sawon)app.getBean("sa3");
		//System.out.println("sa3 = "+sa3);
		sa3.print();
		System.out.println("================");
		/*
		 *   ======================
		 *     id        객체
		 *   ======================
		 *     sa1    new Sawon()
		 *   ======================
		 *     sa2    new Sawon()
		 *   ======================
		 *     sa3    new Sawon()
		 *   ======================
		 */
	}

}
