package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;
public interface RecipeMapper {
	//오라클에서 실행여부 확인
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
}
