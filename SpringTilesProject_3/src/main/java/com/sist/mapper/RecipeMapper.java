package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface RecipeMapper {
	@Select("SELECT no, title, chef, poster, hit, num "
			+ "FROM (SELECT no, title, chef, poster, hit, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, chef, poster, hit "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(@Param("start") int start, @Param("end") int end);
	// rownum => Top-N : 중간의 데이터를 읽을 수 없다
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)")
	public int recipeTotalPage();
	/*
	 *  INTERSECT : 교집합
	 *  UNION : 합집합 중복제거
	 *  MINUS : 차집합
	 *  UNION ALL : 합집합 중복포함  
	 */
	
	//상세 make => like, jjim
	@Update("UPDATE recipe SET hit=hit+1 WHERE no=#{no}")
	public void recipeHitIncrement(int no);
	
	@Select("SELECT * FROM recipeDetail "
			+ "WHERE no=#{no}")
	public RecipeVO recipeDetailData(int no);
}
