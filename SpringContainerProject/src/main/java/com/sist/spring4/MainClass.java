package com.sist.spring4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  1. XML ���� - ���
 *  2. ���� - ������̼�
 *  =================== ����
 *  3. ���
 *     - DI
 *     - AOP
 *     - MVC
 *     - Transaction
 *     - Security
 *     - WebScoket
 *     - Betch : �����ٷ� 
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application.xml");
		Sawon sa=(Sawon)app.getBean("sa");
		sa.setSabun(5);
		sa.setName("������");
		sa.setDept("�ѹ���");
		//System.out.println(sa); //com.sist.spring4.Sawon@7f3b84b8 �ּ� ���
		//setter �ʱ�ȭ
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
		//�̱��� - �ٸ� ��ü�� �ҷ��� �ϳ��� �޸𸮸� �Ҵ��ϱ⿡ ���� �� ��ȯ
		
		//������ �ʱ�ȭ
		Sawon sa5=(Sawon)app.getBean("sa2");
		sa5.print();
	}

}
