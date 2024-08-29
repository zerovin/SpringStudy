package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;
@Component("mc")
// MainClass는 스프링에서 관리 => 필요한 경우 반드시 스프링을 통해서 객체를 얻어온다
// 스프링 : MainClass mc=(MainClass)app.getBean("mc")
// new MainClass() => 스프링과 관련이 없다 => @Autowired가 실행하지 않는다
public class MainClass {
	@Autowired // 메모리할당 (@Conponent) 후 @Autowired를 해야 작동, 메모리할당 안하고쓰면 null
	private GoodsDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		MainClass mc=(MainClass)app.getBean("mc"); //new로 가지고 오면 spring에 저장된거 말고 새로운 메모리 할당이라 null
		String [] tabs= {"", "goods_all", "goods_best", "goods_new", "goods_special"};
		Scanner scan=new Scanner(System.in);
		System.out.print("1.전체상품 2.베스트상품 3.신상품 4.특가상품 >> ");
		int cno=scan.nextInt();
		System.out.print("페이지 입력 >> ");
		int page=scan.nextInt();
		
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("table_name", tabs[cno]);
		map.put("start", start);
		map.put("end", end);
		List<GoodsVO> list=mc.dao.goodsListData(map);
		
		int totalpage=mc.dao.goodsTotalPage(map);
		System.out.println(page+" page / "+totalpage+" pages");
		for(GoodsVO vo:list) {
			System.out.println(vo.getNo()+". "+vo.getGoods_name());
		}
		
		System.out.println("=====================================");
		System.out.print("상세 볼 상품 선택 >> ");
		int no=scan.nextInt();
		map.put("no", no);
		
		GoodsVO vo=mc.dao.goodsDetailData(map);
		System.out.println("상품번호 : "+vo.getNo());
		System.out.println("상품명 : "+vo.getGoods_name());
		System.out.println("상품가격 : "+vo.getGoods_price());
		System.out.println("상품소개 : "+vo.getGoods_sub());
		System.out.println("상품이미지 : "+vo.getGoods_poster());
	}

}
