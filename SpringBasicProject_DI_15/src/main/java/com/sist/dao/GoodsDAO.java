package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository("dao")
public class GoodsDAO {
	@Autowired //���������� ������ Ŭ���� �ּҸ� �޾� �´�
	private GoodsMapper mapper; 
	/*
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_price, goods_poster, "
			+ "FROM ${table_name} ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	*/
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map); //ssf.openSession(), session.close() ����
		//session.selectList()
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM ${table_name}")
	public int goodsTotalPage(Map map);
	*/
	public int goodsTotalPage(Map map) {
		return mapper.goodsTotalPage(map);
	}
	
	/*
	@Select("SELECT cno, goods_name, goods_price, goods_poster, goods_first_price, goods_delivery, goods_discount, goods_sub "
			+ "FROM ${table_name}")
	public GoodsVO goodsDetailData(Map map); 
	 */
	public GoodsVO goodsDetailData(Map map) {
		return mapper.goodsDetailData(map);
	}
}
