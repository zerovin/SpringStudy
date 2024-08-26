package com.sist.mapper;
import java.util.*;
import com.sist.vo.*;
import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
	@Select("SELECT empno, ename, job, hiredate, sal "
			+ "FROM emp ORDER BY empno ASC")
	public List<EmpVO> empListData(); //�ڵ�����, �ڵ���ȯ(session.close())
	//     resultType       id     parameterType
	// <select id="empListData" resultType="EmpVO" parameterType="">
}
