package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	/*
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SLEECT no, poster, title "
			+ "FROM seoul_loaction ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulLocationListData(Map map);
	*/
	public List<SeoulVO> seoulLocationListData(Map map){
		return mapper.seoulLocationListData(map);
	}
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_location")
	public int seoulLocationTotalPage(); 
	 */
	public int seoulLocationTotalPage() {
		return mapper.seoulLocationTotalPage();
	}
	
	/*
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SLEECT no, poster, title "
			+ "FROM seoul_nature ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulNatureListData(Map map);
	*/
	public List<SeoulVO> seoulNatureListData(Map map){
		return mapper.seoulNatureListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_nature")
	public int seoulNatureTotalPage(); 
	 */
	public int seoulNatureTotalPage() {
		return mapper.seoulNatureTotalPage();
	}
	
	/*
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SLEECT no, poster, title "
			+ "FROM seoul_shop ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulVO> seoulShopListData(Map map); 
	 */
	public List<SeoulVO> seoulShopListData(Map map){
		return mapper.seoulShopListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_shop")
	public int seoulShopTotalPage(); 
	 */
	public int seoulShopTotalPage() {
		return mapper.seoulShopTotalPage();
	}
	
}
