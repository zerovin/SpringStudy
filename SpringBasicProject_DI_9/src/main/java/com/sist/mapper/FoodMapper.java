package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
/*
 	private int fno;
	private String name, type, address, time, parking, theme;
 */
public interface FoodMapper {
	@Select("SELECT fno, name, type, address, time, parking, theme "
			+ "FROM project_food_house "
			+ "WHERE ${column} LIKE '%'||#{fd}||'%'")
	public List<FoodVO> foodFindData(Map map);
}
