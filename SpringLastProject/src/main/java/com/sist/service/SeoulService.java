package com.sist.service;

import java.util.*;

import com.sist.vo.SeoulVO;

public interface SeoulService {
	public List<SeoulVO> seoulLocationListData(Map map);
	public List<SeoulVO> seoulNatureListData(Map map);
	//public List<SeoulVO> seoulShopListData(Map map);
	public int seoulLocationTotalPage();
	public int seoulNatureTotalPage();
	//public int seoulShopTotalPage();
	public SeoulVO seoulLocationDetailData(int no);
	public SeoulVO seoulNatureDetailData(int no);
	//public SeoulVO seoulShopDetailData(int no);
	
	public List<SeoulVO> seoulShopListData(Map map);
	public int seoulShopTotalPage();
	public SeoulVO seoulShopDetailData(Map map);
}
