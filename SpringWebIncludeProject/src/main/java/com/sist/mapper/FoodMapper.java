package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;
import org.apache.ibatis.annotations.*;
/*
 	private int fno, score;
	private String name, type, phone, address, theme, poster, time, parking, content; 
 */
public interface FoodMapper {
	@Select("SELECT fno, name, poster, address, num "
			+ "FROM (SELECT fno, name, poster, address, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(project_food_house pf_fno_pk)*/fno, name, poster, address "
			+ "FROM project_food_house)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT COUNT(*) FROM project_food_house")
	public int foodRowCount();
	
	@Update("UPDATE project_food_house SET hit=hit+1 WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	@Select("SELECT * FROM project_food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno, name, poster, address FROM project_food_house WHERE fno=#{fno}")
	public FoodVO foodCookieData(int fno);
}
