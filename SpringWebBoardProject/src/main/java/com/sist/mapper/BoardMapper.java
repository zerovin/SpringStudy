package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.sist.vo.*;
public interface BoardMapper {
	//목록 - 페이징
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	/*                 메소드명               리턴형                  매개변수
	 *  <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
	 *  	SELECT ~ 
	 *  </select>
	 */
	// boardList(1,10) => 구분자 전송 => @Param 매개변수 여러개 사용가능
	// Map => 1개 이용시 Map / VO로 묶어서 전송, VO에 없는 변수는 Map으로
	@Select("SELECT COUNT(*) FROM spring_board")
	public int boardRowCount();
	
	//추가
	@Insert("INSERT INTO spring_board(no, name, subject, content, pwd) "
			+ "VALUES(sb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	public void boardInsert(BoardVO vo);
	
	//상세
	@Update("UPDATE spring_board SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
			+ "FROM spring_board "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//수정
	@Select("SELECT pwd FROM spring_board "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE spring_board SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	//삭제
	@Delete("DELETE FROM spring_board "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	//검색
}
