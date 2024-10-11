package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface FreeBoardMapper {
	//목록
	@Select("SELECT no, subject, id, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, id, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, id, name, regdate, hit "
			+ "FROM spring_freeboard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FreeBoardVO> freeboardListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT COUNT(*) FROM spring_freeboard")
	public int freeboardRowCount();
	
	//수정
	@Select("SELECT no, subject, content FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public FreeBoardVO freeboardUpdateData(int no);
	
	@Update("UPDATE spring_freeboard SET "
			+ "subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void freeboardUpdate(FreeBoardVO vo);
	
	//삭제
	@Delete("DELETE FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public void freeboardDelete(int no);
	
	//추가
	@Insert("INSERT INTO spring_freeboard VALUES(sf_no_seq.nextval, #{id}, #{name}, #{subject}, #{content}, SYSDATE, 0)")
	public void freeboardInsert(FreeBoardVO vo);
	
	//상세
	@Update("UPDATE spring_freeboard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, id, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
			+ "FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public FreeBoardVO freeboardDetailData(int no);
}
