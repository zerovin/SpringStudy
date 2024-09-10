package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	
	public RecipeDetailVO recipeDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.recipeDetailData(no);
	}
	
	public List<RecipeVO> recipeFindData(Map map) {
		return mapper.recipeFindData(map);
	}
	
	public int recipeFindTotalPage(Map map) {
		return mapper.recipeFindTotalPage(map);
	}
	
	public List<ChefVO> chefListData(Map map){
		return mapper.chefListData(map);
	}
	
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	
	public List<RecipeVO> chefMakeData(Map map){
		return mapper.chefMakeData(map);
	}
	
	public int chefMakeTotalData(Map map) {
		return mapper.chefMakeTotalData(map);
	}
}
