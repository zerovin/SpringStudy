package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	/*
	 @Select("SELECT fno, name, poster, address, num "
			+ "FROM (SELECT fno, name, poster, address, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(project_food_house pf_fno_pk)/fno, name, poster, address "
			+ "FROM project_food_house)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	*/
	public List<FoodVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	/*
	@Select("SELECT COUNT(*) FROM project_food_house")
	public int foodRowCount();
	 */
	public int foodRowCount() {
		return mapper.foodRowCount();
	}
	
	/*
	 @Update("UPDATE project_food_house SET hit=hit+1 WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	@Select("SELECT * FROM project_food_house WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	 */
	public FoodVO foodDetailData(int fno) {
		mapper.foodHitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
	
	/*
	 @Select("SELECT fno, name, poster FROM project_food_house WHERE fno=#{fno}")
	public FoodVO foodCookieData(int fno); 
	 */
	public FoodVO foodCookieData(int fno) {
		return mapper.foodCookieData(fno);
	}
}
