package com.sist.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;
import java.util.*;
import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT fno, name, poster, address, num "
			+ "FROM (SELECT fno, name, poster, address, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(project_food_house pf_fno_pk)*/fno, name, poster, address "
			+ "FROM project_food_house)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house")
	public int foodTotalPage();
}
