package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class BoardDAO {
	// 스프링으로부터 구현된 Mapper의 주소를 대입 => 요청
	@Autowired // 주소값 자동 주입 => 객체에만 적용 가능
	// 스프링 => 클래스의 생명주기 관리 => 주로 객체 주소 주입
	// @Autowired 1)멤버변수, 2)생성자, 3)setXxx()
	// 어노테이션은 아래/옆에 있는 변수/메소드/생성자에 적용/제어
	private BoardMapper mapper;
	
	/*
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM vue_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	*/
	public List<BoardVO> boardListData(int start, int end){
		return mapper.boardListData(start, end);
	}
	
	/*
	@Select("SELECT COUNT(*) FROM vue_board")
	public int boardRowCount(); 
	 */
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	
	/*
	@Insert("INSERT INTO vue_board(no, name, subject, content, pwd) "
			+ "VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	public void boardInsert(BoardVO vo); 
	 */
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	/*
	@Update("UPDATE vue_board SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no); 
	 */
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	/*
	 @Select("SELECT pwd FROM vue_board WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Delete("DELETE FROM vue_board WHERE no=#{no}")
	public void boardDelete(int no); 
	 */
	public String boardDelete(int no, String pwd) {
		String result="no";
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			mapper.boardDelete(no);
		}
		return result;
	}
	
	/*
	@Select("SELECT name, subject, content "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public BoardVO boardUpdateData(int no); 
	 */
	public BoardVO boardUpdateData(int no) {
		return mapper.boardUpdateData(no);
	}
	
	/*
	@Update("UPDATE vue_baord SET name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo); 
	 */
	public String boardUpdate(BoardVO vo) {
		String result="no";
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result="yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}
}
