package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface EmpMapper {
	/*
	<select id="empListData" resultMap="empMap">
		SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, dname, loc, grade
		FROM emp, dept, salgrade
		WHERE emp.deptno=dept.deptno <!-- INNER JOIN -->
		AND sal BETWEEN losal AND hisal
	</select> 
	 */
	public List<EmpVO> empListData();
	//@ResultMap("empMap") => XML에 <resultMap> 구현해두면 id만 가져오면 됨 아니면 @Results 사용
	//SQL문장 사용시마다 문장 위에 붙여야해서 @ResultMap("id")가 편리
	/*
	@Results({
		@Result(property="dvo.dname",column="dname"),
		@Result(property="dvo.log",column="loc"),
		@Result(property="svo.grade",column="grade")
	})
	*/
	@ResultMap("empMap") //XML에 등록된 <resultMap> => 매개변수=id명
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, dname, loc, grade "
			+ "FROM emp, dept, salgrade "
			+ "WHERE emp.deptno=dept.deptno "
			+ "AND sal BETWEEN losal AND hisal "
			+ "AND empno=#{empno}")
	public EmpVO empDetailData(int empno);
}
