package com.sist.spring5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	//Spring 4 - xml + java
	//Spring 5 - annotation, �����ڹ�
	//Spring 6 - domain
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[] xml={"sawon.xml", "member.xml"}; //xml �����Ľ�
		ApplicationContext app=new ClassPathXmlApplicationContext("my*.xml"); //my�� �����ϴ� ��� xml �Ľ�
		Sawon sa=(Sawon)app.getBean("sa");
		sa.print();
		Member mem=(Member)app.getBean("mem");
		mem.print();
	}

}
