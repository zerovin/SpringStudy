package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.BoardDAO;
import com.sist.manager.BoardManager;
import com.sist.model.BoardModel;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		BoardDAO dao=(BoardDAO)app.getBean("dao"); //boardDAO
		dao.list();
		
		BoardManager bm=(BoardManager)app.getBean("bm"); //boardManaer
		bm.manager();
		
		//���̵� ������ �ȵ� ���� => �ڵ����� => Ŭ������, ù���� �ҹ���
		BoardModel model=(BoardModel)app.getBean("boardModel");
		model.model();
	}

}
