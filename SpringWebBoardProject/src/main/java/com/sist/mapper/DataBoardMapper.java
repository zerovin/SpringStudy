package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.sist.mapper.*;
import com.sist.vo.*;
public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_databoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT COUNT(*) FROM spring_databoard")
	public int databoardRowCount();
	
	@Insert("INSERT INTO spring_databoard(no, name, subject, content, pwd, filename, filesize, filecount) "
			+ "VALUES(sd_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, #{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	//상세보기
	@Update("UPDATE spring_databoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday, hit, filename, filesize, filecount "
			+ "FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	//삭제하기 => 암호화/실제데이터 비교 => match
	@Select("SELECT filename, filecount FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	
	@Select("SELECT pwd FROM spring_databoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	@Delete("DELETE FROM spring_databoard "
			+ "WHERE no=#{no}")
	public void databoardDelete(int no);
}
