package com.sist.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT fno, name, poster, address, rownum "
			+ "FROM (SELECT fno, name, poster, address "
			+ "FROM project_food_house ORDER BY hit DESC) "
			+ "WHERE rownum<=5")
	public List<FoodVO> foodHitTop5();
	// 목록
	@Select("SELECT fno, name, poster, score, type, hit, num "
			+ "FROM (SELECT fno, name, poster, score, type, hit, rownum as num "
			+ "FROM (SELECT fno, name, poster, score, type, hit "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house")
	public int foodTotalPage();
	
	// 상세보기
	@Update("UPDATE project_food_house SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void hitIncrement(int fno);
	@Select("SELECT * FROM project_food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	//검색
	@Select("SELECT fno, name, poster, score, type, hit, num "
			+ "FROM (SELECT fno, name, poster, score, type, hit, rownum as num "
			+ "FROM (SELECT fno, name, poster, score, type, hit "
			+ "FROM project_food_house "
			//+ "<if test='fd!=null'>"
			+ "WHERE address LIKE '%'||#{fd}||'%' "
			//+ "</if>"
			+ "ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindListData(Map map);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house "
			+ "WHERE address LIKE '%'||#{fd}||'%'")
	public int foodFindTotalPage(Map map);
	
	// 예약
	// 추천 => 네이버 카페
}
