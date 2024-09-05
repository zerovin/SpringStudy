package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO dao;

	@Override
	public List<FoodVO> foodListData(Map map) {
		return dao.foodListData(map);
	}

	@Override
	public int foodRowCount() {
		return dao.foodRowCount();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		return dao.foodDetailData(fno);
	}

	@Override
	public FoodVO foodCookieData(int fno) {
		return dao.foodCookieData(fno);
	}
	
	
}
