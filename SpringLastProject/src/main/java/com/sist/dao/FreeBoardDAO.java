package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// 형식 동일 => 마이페이지, 관리자페이지에서 여러개 동시 삭제
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FreeBoardDAO {
	@Autowired
	private FreeBoardMapper mapper;
	
	/*
	//목록
	@Select("SELECT no, subject, id, name, TO_CHAR(regdate, 'YYYY-MM-DD) as dbday, num "
			+ "FROM (SELECT no, subject, id, name, regdate, rownum as num "
			+ "FROM (SELECT no, subject, id, name, regdate "
			+ "FROM spring_freeboard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FreeBoardVO> freeboardListData(@Param("start") int start, @Param("end") int end);
	*/
	public List<FreeBoardVO> freeboardListData(int start, int end){
		return mapper.freeboardListData(start, end);
	}
	
	/*
	@Select("SELECT COUNT(*) FROM spring_freeboard")
	public int freeboardRowCount();
	*/
	public int freeboardRowCount() {
		return mapper.freeboardRowCount();
	}
	
	/*	
	//추가
	@Insert("INSERT INTO spring_freeboard VALUES(sf_no_seq.nextval, #{id}, #{name}, #{subject}, #{content}, SYSDATE, 0")
	public void freeboardInsert(FreeBoardVO vo); 
	 */
	public void freeboardInsert(FreeBoardVO vo) {
		mapper.freeboardInsert(vo);
	}
	
	/*
	//상세
	@Update("UPDATE spring_freeboard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, id, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
			+ "FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public FreeBoardVO freeboardDetailData(int no); 
	 */
	public FreeBoardVO freeboardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.freeboardDetailData(no);
	}
	
	/*
	//삭제
	@Delete("DELETE FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public void freeboardDelete(int no); 
	 */
	public void freeboardDelete(int no) {
		mapper.freeboardDelete(no);
	}
	
	/*
	//수정
	@Select("SELECT no, subject, content FROM spring_freeboard "
			+ "WHERE no=#{no}")
	public FreeBoardVO freeboardUpdateData(int no);
	*/
	public FreeBoardVO freeboardUpdateData(int no) {
		return mapper.freeboardUpdateData(no);
	}
	
	/*
	@Update("UPDATE spring_freeboard SET "
			+ "subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void freeboardUpdate(FreeBoardVO vo); 
	 */
	public void freeboardUpdate(FreeBoardVO vo) {
		mapper.freeboardUpdate(vo);
	}
}
