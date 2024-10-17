package com.sist.dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
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
	
	public List<SeoulVO> seoulShopListData(Map map){
		return mapper.seoulShopListData(map);
	}
	 */
	
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_shop")
	public int seoulShopTotalPage(); 
	
	public int seoulShopTotalPage() {
		return mapper.seoulShopTotalPage();
	}
	 */
	
	/*
	@Select("SELECT no, title, poster, msg, address "
			+ "FROM project_seoul_location "
			+ "WHERE no=#{no}")
	public SeoulVO seoulLocationDetailData(int no);
	*/
	public SeoulVO seoulLocationDetailData(int no) {
		return mapper.seoulLocationDetailData(no);
	}
	
	/*
	@Select("SELECT no, title, poster, msg, address "
			+ "FROM project_seoul_nature "
			+ "WHERE no=#{no}")
	public SeoulVO seoulNatureDetailData(int no);
	*/
	public SeoulVO seoulNatureDetailData(int no) {
		return mapper.seoulNatureDetailData(no);
	}
	
	/*
	@Select("SELECT no, title, poster, msg, address "
			+ "FROM project_seoul_shop "
			+ "WHERE no=#{no}")
	public SeoulVO seoulShopDetailData(int no); 
	
	public SeoulVO seoulShopDetailData(int no) {
		return mapper.seoulShopDetailData(no);
	}
	 */
	
	/*
	@Select(value="{CALL seoulShopListData("
			+ "#{pStart, mode=IN, javaType=java.lang.Integer}, #{pEnd, mode=IN, javaType=java.lang.Integer}, #{pResult, mode=OUT, jdbcType=CURSOR})}")
	@Options(statementType=StatementType.CALLABLE)
	// Statement, PreparedStatement(default), CallableStatement(procedure호출)
	public List<SeoulVO> seoulShopListData(Map map); 
	 */
	public List<SeoulVO> seoulShopListData(Map map){
		mapper.seoulShopListData(map);
		return (List<SeoulVO>)map.get("pResult");
	}
	
	/*
	@Select(value="{CALL seoulShopTotalPage(#{pTotal, mode=OUT, javaType=java.lang.Integer})}")
	@Options(statementType=StatementType.CALLABLE)
	public int seoulShopTotalPage(Map map); 
	 */
	public int seoulShopTotalPage() {
		return mapper.seoulShopTotalPage();
	}
	
	/*
	@Select(value="{CALL seoulShopDetailData(#{pNo, javaType=java.lang.Integer, mode=IN}, #{pResult, mode=OUT, jdbyType=CURSOR})}")
	@Options(statementType=StatementType.CALLABLE)
	public SeoulVO seoulShopDetailData(Map map); 
	 */
	public SeoulVO seoulShopDetailData(Map map) {
		mapper.seoulShopDetailData(map);
		List<SeoulVO> list=(List<SeoulVO>)map.get("pResult");
		return list.get(0);
	}
}
