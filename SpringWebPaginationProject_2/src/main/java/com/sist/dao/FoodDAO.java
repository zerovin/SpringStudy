package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	/*
	 @Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(project_food_house pf_fno_pk)/fno, name, poster "
			+ "FROM project_food_house)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	*/
	public List<FoodVO> foodListData(int start, int end){
		return mapper.foodListData(start, end);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house")
	public int foodTotalPage(); 
	 */
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
