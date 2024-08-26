package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app3.xml");
		Board b=(Board)app.getBean("board");
		b.print();
		//호출 순서 : 생성자 -> setXxx() => 최종값은 setter
	}

}
