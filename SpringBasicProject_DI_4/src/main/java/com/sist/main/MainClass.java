package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.model.*;
/*
 *  Spring - Web ���̺귯��
 *           Ŭ���� ���� => �޸� ���� ���� (�������� �Ҹ����)
 *                       ���ռ��� ���� ���α׷� => �ٸ� Ŭ������ ������ ��ġ�� �ʴ´�
 *                       ==> ��������, ����� ����, ��� ������ Ŭ������ �°� �ҽ� �ڵ�
 *                           ������������ ��� �޴� ���� ���� �幰�� POJO (2.5����) => �Ϲ��ڹ�
 *                       �����̳� - �淮 / Ŭ����ȭ
 *                                �� ���� => ������ / �����۾� / ���콺 => ����
 *                                �б�, ����, �����, ���� (��������) => ������
 *           1. ������Ʈ�� �´� Ŭ���� ����
 *           2. ���� => Ŭ������ Ŭ������ ���� ���� ���� - .xml, annotation
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
