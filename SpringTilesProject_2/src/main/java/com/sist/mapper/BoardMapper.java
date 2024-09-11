package com.sist.mapper;
//SQL문장을 이용해서 CRUD 수행 => MyBatis에 의해 자동 구현
/*
 *  JSP => @Controller => @Repository => @Controller => JSP
 *  
 *  JSP에서 .do 요청 => 자바(Model-Controller)수행 => JSP수행
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
}
