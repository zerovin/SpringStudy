package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	/*
	@Select("SELECT no, goods_price, goods_poster, goods_name, hit, num "
			+ "FROM (SELECT no, goods_price, goods_poster, goods_name, hit, rownum as num "
			+ "FROM (SELECT no, goods_price, goods_poster, goods_name, hit "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	*/
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalpage(); 
	 */
	public int goodsTotalpage() {
		return mapper.goodsTotalpage();
	}
	
	/*
	@Update("UPDATE goods_all SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no); 
	 */
	public void hitIncrement(int no) {
		mapper.hitIncrement(no);
	}
	
	/*
	@Select("SELECT * FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	*/
	public GoodsVO goodsDetailData(int no) {
		return mapper.goodsDetailData(no);
	}
	
	/*
	@Select("SELECT userId, userName, sex, post, addr1, addr2, phone, email "
			+ "FROM spring_member "
			+ "WHERE userId=#{userId}")
	public MemberVO memberInfoData(String userId); 
	 */
	public MemberVO memberInfoData(String userId) {
		return mapper.memberInfoData(userId);
	}
	
	/*
	@Insert("INSERT INTO spring_cart(cno, gno, id, account) "
			+ "VALUES(sc2_cno_seq.nextval, #{gno}, #{id}, #{account})")
	public void goodsCartInsert(CartVO vo); 
	 */
	public void goodsCartInsert(CartVO vo) {
		mapper.goodsCartInsert(vo);
	}
	
	/*
	@Update("UPDATE spring_cart SET "
			+ "account=account+#{account} "
			+ "WHERE cno=#{cno}")
	public void goodsCartAccountUpdate(CartVO vo); 
	 */
	public void goodsCartAccountUpdate(CartVO vo) {
		mapper.goodsCartAccountUpdate(vo);
	}
	
	/*
	@Select("SELECT COUNT(*) FROM spring_cart "
			+ "WHERE gno=#{gno}")
	public int goodsCartGnoCount(int gno);
	*/
	public int goodsCartGnoCount(int gno) {
		return mapper.goodsCartGnoCount(gno);
	}
	
	/*
	@Results({
		@Result(property="gvo.goods_name", column="goods_name"),
		@Result(property="gvo.goods_poster", column="goods_poster"),
		@Result(property="gvo.goods_price", column="goods_price")
	})
	@Select("SELECT cno, gno, account, isBuy, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, goods_name, goods_poster, goods_price "
			+ "FROM spring_cart sc, goods_all ga "
			+ "WHERE sc.gno=ga.no "
			+ "AND id=#{id} "
			+ "AND isBuy=0 "
			+ "ORDER VY cno DESC")
	public List<CartVO> goodsCartListData(String id); 
	 */
	public List<CartVO> goodsCartListData(String id){
		return mapper.goodsCartListData(id);
	}
	
	/*
	@Delete("DELETE FROM spring_cart "
			+ "WHERE cno=#{cno}")
	public void goodsCartCancel(int cno); 
	 */
	public void goodsCartCancel(int cno) {
		mapper.goodsCartCancel(cno);
	}
	
	/*
	@Update("UPDATE spring_cart SET "
			+ "isBuy=1 "
			+ "WHERE cno#{cno}")
	public void goodsBuy(int cno);
	*/
	public void goodsBuy(int cno) {
		mapper.goodsBuy(cno);
	}
	
	/*
	@Results({
		@Result(property="gvo.goods_name", column="goods_name"),
		@Result(property="gvo.goods_poster", column="goods_poster"),
		@Result(property="gvo.goods_price", column="goods_price")
	})
	@Select("SELECT cno, gno, account, isBuy, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, goods_name, goods_poster, goods_price "
			+ "FROM spring_cart sc, goods_all ga "
			+ "WHERE sc.gno=ga.no "
			+ "AND id=#{id} "
			+ "AND isBuy=1 "
			+ "ORDER BY cno DESC")
	public List<CartVO> goodsBuyListData(String id); 
	 */
	public List<CartVO> goodsBuyListData(String id){
		return mapper.goodsBuyListData(id);
	}
}
