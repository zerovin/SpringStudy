package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *  JSP => Controller => Service => Repository => Oracle
 *     <=            <=         <=            <=   
 *     
 *  1. mapper => SQL 문장 작성
 *  2. dao에서 구현
 *  3. service에 등록 ~Service
 *  4. service 구현 ~ServiceImpl
 *  => 유지보수 목적 => DAO를 수정하면 생기는 오류방지
 *  => 결합성이 낮은 프로그램 (다른 클래스에 영향없이 사용)
 */
@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO dao;

	@Override
	public List<RecipeVO> recipeListData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeListData(map);
	}

	@Override
	public int recipeRowCount() {
		// TODO Auto-generated method stub
		return dao.recipeRowCount();
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		// TODO Auto-generated method stub
		return dao.recipeDetailData(no);
	}

	@Override
	public List<ChefVO> chefListData(Map map) {
		// TODO Auto-generated method stub
		return dao.chefListData(map);
	}

	@Override
	public int chefTotalPage() {
		// TODO Auto-generated method stub
		return dao.chefTotalPage();
	}

	@Override
	public List<RecipeVO> chefMakeRecipeData(Map map) {
		// TODO Auto-generated method stub
		return dao.chefMakeRecipeData(map);
	}

	@Override
	public int chefMakeRecipeTotalPage(String chef) {
		// TODO Auto-generated method stub
		return dao.chefMakeRecipeTotalPage(chef);
	}

	@Override
	public RecipeVO recipeCookieInfoData(int no) {
		// TODO Auto-generated method stub
		return dao.recipeCookieInfoData(no);
	}

	@Override
	public List<RecipeVO> recipeFindData(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeFindData(map);
	}

	@Override
	public int recipeFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return dao.recipeFindTotalPage(map);
	}

	@Override
	public List<FoodVO> foodTop5Data() {
		// TODO Auto-generated method stub
		return dao.foodTop5Data();
	}

	@Override
	public List<RecipeVO> recipeTop5Data() {
		// TODO Auto-generated method stub
		return dao.recipeTop5Data();
	}
	
	
	
}
