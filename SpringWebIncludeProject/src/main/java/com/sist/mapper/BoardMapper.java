package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
	//목록
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, group_tab, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
			+ "FROM spring_replyboard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ReplyBoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	
	//총페이지
	@Select("SELECT COUNT(*) FROM spring_replyboard")
	public int boardRowCount();
	
	//추가
	@Insert("INSERT INTO spring_replyboard(no, name, subject, content, pwd, group_id) "
			+ "VALUES(srb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, "
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))")
	public void boardInsert(ReplyBoardVO vo);
	
	//상세
	@Update("UPDATE spring_replyboard SET hit=hit+1 WHERE no=#{no}")
	public void boardHitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM spring_replyboard "
			+ "WHERE no=#{no}")
	public ReplyBoardVO boardDetailData(int no);
	
	//수정
	//삭제
	//답변
	//검색 => 동적쿼리
}
