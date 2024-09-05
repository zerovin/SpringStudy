package com.sist.service;
import java.util.*;
import com.sist.vo.*;
public interface FoodService {
	public List<FoodVO> foodListData(Map map);
	public int foodRowCount();
	public FoodVO foodDetailData(int fno);
	public FoodVO foodCookieData(int fno);
}
