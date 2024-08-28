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
		System.out.print("페이지 입력 >> ");
		int page=scan.nextInt();
		
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		int totalpage=dao.goodsTotalPage();
		List<GoodsVO> list=dao.goodsListData(start, end);
		
		for(GoodsVO vo:list) {
			System.out.println("상품 번호 : "+vo.getNo());
			System.out.println("상품명 : "+vo.getGoods_name());
			System.out.println("=========================");
		}
		System.out.println("======= "+page+" page / "+totalpage+" pages ======");
		System.out.print("상품 번호 입력 >> ");
		int no=scan.nextInt();
		GoodsVO vo=dao.goodsDetailData(no);
		System.out.println("=========== 상품 정보 ===========");
		System.out.println("상품 번호 : "+vo.getNo());
		System.out.println("상품명 : "+vo.getGoods_name());
		System.out.println("설명 : "+vo.getGoods_sub());
		System.out.println("할인율 : "+vo.getGoods_discount()+"%");
		System.out.println("가격 : "+vo.getGoods_price());
		System.out.println("배송 : "+vo.getGoods_delivery());
		
	}

}
