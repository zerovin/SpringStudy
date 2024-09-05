package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface RecipeMapper {
	//레시피 목록
	@Select("SELECT no, poster, title, chef, num "
			+ "FROM (SELECT no, poster, title, chef, rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, poster, title, chef "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)))"
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT COUNT(*) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
	public int recipeRowCount();
	
	//레시피 상세
	@Update("UPDATE recipe SET hit=hit+1 WHERE no=#{no}")
	public void recipeHitIncrement(int no);
	
	@Select("SELECT * FROM recipedetail "
			+ "WHERE no=#{no}")
	public RecipeDetailVO recipeDetailData(int no);
	
	//쿠키정보 데이터
	@Select("SELECT no, title, poster "
			+ "FROM recipe "
			+ "WHERE no=#{no}")
	public RecipeVO recipeCookieInfoData(int no);
	
	//레시피 찾기
	
	//셰프 목록
	@Select("SELECT chef, poster, mem_cont1, mem_cont3, mem_cont7, mem_cont2, num "
			+ "FROM (SELECT chef, poster, mem_cont1, mem_cont3, mem_cont7, mem_cont2, rownum as num "
			+ "FROM (SELECT chef, poster, mem_cont1, mem_cont3, mem_cont7, mem_cont2 "
			+ "FROM chef)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/50.0) FROM chef")
	public int chefTotalPage();
	
	//셰프 상세
	@Select("SELECT no, title, poster, chef, num "
			+ "FROM (SELECT no, title, poster, chef, rownum as num "
			+ "FROM (SELECT no, title, poster, chef "
			+ "FROM recipe WHERE chef=#{chef} ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefMakeRecipeData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe WHERE chef=#{chef}")
	public int chefMakeRecipeTotalPage(String chef);
	
}
