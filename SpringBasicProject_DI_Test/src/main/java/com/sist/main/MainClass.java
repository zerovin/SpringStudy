package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		GoodsDAO dao=(GoodsDAO)app.getBean("dao");
		
		Scanner scan=new Scanner(System.in);
		System.out.print("������ �Է� >> ");
		int page=scan.nextInt();
		
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		int totalpage=dao.goodsTotalPage();
		List<GoodsVO> list=dao.goodsListData(start, end);
		
		for(GoodsVO vo:list) {
			System.out.println("��ǰ ��ȣ : "+vo.getNo());
			System.out.println("��ǰ�� : "+vo.getGoods_name());
			System.out.println("=========================");
		}
		System.out.println("======= "+page+" page / "+totalpage+" pages ======");
		System.out.print("��ǰ ��ȣ �Է� >> ");
		int no=scan.nextInt();
		GoodsVO vo=dao.goodsDetailData(no);
		System.out.println("=========== ��ǰ ���� ===========");
		System.out.println("��ǰ ��ȣ : "+vo.getNo());
		System.out.println("��ǰ�� : "+vo.getGoods_name());
		System.out.println("���� : "+vo.getGoods_sub());
		System.out.println("������ : "+vo.getGoods_discount()+"%");
		System.out.println("���� : "+vo.getGoods_price());
		System.out.println("��� : "+vo.getGoods_delivery());
		
	}

}
