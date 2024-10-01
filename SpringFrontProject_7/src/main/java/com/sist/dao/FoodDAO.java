package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	/*
	 // 목록
	@Select("SELECT fno, name, poster, score, type, hit, num "
			+ "FROM (SELECT fno, name, poster, score, type, hit, rownum as num "
			+ "FROM (SELECT fno, name, poster, score, type, hit "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	*/
	public List<FoodVO> foodListData(int start, int end){
		return mapper.foodListData(start, end);
	}
	
	/*
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house")
	public int foodTotalPage();
	*/
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	/*
	// 상세보기
	@Update("UPDATE project_food_house SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void hitIncrement(int fno);
	@Select("SELECT * FROM project_food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	 */
	public FoodVO foodDetailData(int fno) {
		mapper.hitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
	
	/*
	@Select("SELECT COUNT(*) FROM project_member "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);
	@Select("SELECT id, name, sex, pwd FROM project_member "
			+ "WHERE id=#{id}")
	public MemberVO memberGetInfoDate(String id); 
	 */
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		int count=mapper.memberIdCheck(id);
		if(count==0) { //id가 없는 상태
			vo.setMsg("NOID");
		}else { // id가 존재하는 상태
			MemberVO dbvo=mapper.memberGetInfoDate(id);
			if(pwd.equals(dbvo.getPwd())) { // 로그인
				vo.setMsg("OK");
				//세션 저장용 => 사용자의 일부 정보를 서버에 저장 => 필요시마다 사용
				vo.setId(dbvo.getId());
				vo.setName(dbvo.getName());
				vo.setSex(dbvo.getSex());
			}else { //비밀번호가 틀린 상태
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
