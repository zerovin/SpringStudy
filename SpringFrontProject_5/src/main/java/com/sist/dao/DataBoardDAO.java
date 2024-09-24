package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	/*
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM vue_databoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end);
	*/
	public List<DataBoardVO> databoardListData(int start, int end){
		return mapper.databoardListData(start, end);
	}
	
	/*
	@Select("SELECT COUNT(*) FROM vue_databoard")
	public int databoardCount(); 
	 */
	public int databoardCount() {
		return mapper.databoardCount();
	}
	
	/*
	@SelectKey(keyProperty="no", resultType=int.class, before=true, statement="SELECT NVL(MAX(no)+1,1) as no FROM vue_databoard"
	@Insert("INSERT INTO vue_databoard(no, name, subject, content, pwd, filename, filesize, filecount) "
			+ "VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, #{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo); 
	 */
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	/*
	@Update("UPDATE vue_databoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, filename, filesize, filecount, hit "
			+ "FROM veue_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no); 
	 */
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	/*
	 //비밀번호 처리
	@Select("SELECT pwd FROM vue_databoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	//파일정보읽기
	@Select("SELECT filename, filecount FROM vue_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	//삭제
	@Delete("DELETE FROM vue_databoard "
			+ "WHERE no=#{no}")
	public void databoardDelete(int no); 
	 */
	public DataBoardVO databoardFileInfoData(int no) {
		return mapper.databoardFileInfoData(no);
	}
	
	public String databoardDelete(int no, String pwd) {
		String result="no";
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			mapper.databoardDelete(no);
			result="yes";
		}
		return result;
	}
	
	//수정
	//이전에 입력한 데이터 읽어서 출력
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	/*
	 @Update("UPDATE vue_databoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content}, filename=#{filename}, filesize=#{filesize}, filecount=#{filecount} "
			+ "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo); 
	 */
	public String databoardUpdate(DataBoardVO vo) {
		String result="no";
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			mapper.databoardUpdate(vo);
			result="yes";
		}
		return result; 
	}
}
