package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface RecipeMapper {
	//오라클에서 실행여부 확인
	//메인
	@Select("SELECT no, title, poster, chef, hit, (SELECT content FROM recipedetail WHERE no=recipe.no) as content "
			+ "FROM recipe "
			+ "WHERE hit=(SELECT MAX(hit) FROM recipe)")
	public RecipeVO recipeMaxHitData();
	
   @Select("SELECT no, title, poster, chef, hit, content "
	         + "FROM (SELECT r.no, r.title, r.poster, r.chef, r.hit, rd.content "
	         + "FROM recipe r "
	         + "JOIN recipedetail rd ON r.no = rd.no "
	         + "ORDER BY r.hit DESC)"
	         + "WHERE ROWNUM<=8 AND hit!=(SELECT MAX(hit) FROM recipe)")
	public List<RecipeVO> recipeHitTop8();
   
   //목록 출력
   @Select("SELECT no, poster, title, chef, hit, num "
   		+ "FROM (SELECT no, poster, title, chef, hit, rownum as num "
   		+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, poster, title, chef, hit "
   		+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
   		+ "WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map map);
   
   //총페이지
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
   		+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)")
   public int recipeTotalPage();
   
   //상세보기
   @Update("UPDATE recipe SET hit=hit+1 "
   		+ "WHERE no=#{no}")
   public void hitIncrement(int no);
   
   @Select("SELECT * FROM recipedetail "
   		+ "WHERE no=#{no}")
   public RecipeDetailVO recipeDetailData(int no);
}