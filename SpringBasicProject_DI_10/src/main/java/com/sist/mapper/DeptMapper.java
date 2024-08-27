package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.DeptVO;
public interface DeptMapper {
	@Select("SELECT deptno, dname, loc "
			+ "FROM dept ORDER BY deptno ASC")
	public List<DeptVO> deptListData();
	//interface => �ڵ����� ����
	/*
	 * 	������ : resultType
	 *  �Ű����� : parameterType
	 *  �޼ҵ�� : id
	 *  <select id="deptListData" resultType="DeptVO" parameterType="">
	 *  	SELECT deptno, dname, loc FROM dept ORDER BY deptno ASC
	 *  </select> 
	 */
}
