package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 *  ~VO : 데이터형 => class 관련된 데이터를 모아서 관리
 *                  데이터형 클래스
 *                  액션 클래스 (기능 => 메소드) 
 *                  - ~DAO => @Repository
 *                  - ~Manager, AOP => @Component
 *                  - ~Service => @Service DAO 여러개를 통합해서 사용 BI
 *                  - ~Controller => @Controller
 *                  - ~RestController => @RestController
 *  인터페이스는 메모리할당에서 제외
 *  ~Mapper
 *  ~Service 
 */
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	/*
	 //목록 출력
   @Select("SELECT no, poster, title, chef, hit, num "
   		+ "FROM (SELECT no, poster, title, chef, hit, rownum as num "
   		+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)/no, poster, title, chef, hit "
   		+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
   		+ "WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map map);
   */
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
   
	/*
   //총페이지
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
   		+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)")
   public int recipeTotalPage();
   */
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
   
	/*
   //상세보기
   @Update("UPDATE recipe SET hit=hit+1 "
   		+ "WHERE no=#{no}")
   public void hitIncrement(int no);
   
   @Select("SELECT * FROM recipedetail "
   		+ "WHERE no=#{no}")
   public RecipeDetailVO recipeDetailData(int no); 
	 */
	public RecipeDetailVO recipeDetailData(int no) {
		mapper.hitIncrement(no);
		return recipeDetailData(no);
	}
}
