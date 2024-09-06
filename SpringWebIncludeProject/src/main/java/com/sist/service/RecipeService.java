package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.*;

public interface RecipeService {
	public List<RecipeVO> recipeListData(Map map);
	public int recipeRowCount();
	public RecipeDetailVO recipeDetailData(int no);
	public List<ChefVO> chefListData(Map map);
	public int chefTotalPage();
	public List<RecipeVO> chefMakeRecipeData(Map map);
	public int chefMakeRecipeTotalPage(String chef);
	public RecipeVO recipeCookieInfoData(int no);
	public List<RecipeVO> recipeFindData(Map map);
	public int recipeFindTotalPage(Map map);
	public List<FoodVO> foodTop5Data();
	public List<RecipeVO> recipeTop5Data();
}
