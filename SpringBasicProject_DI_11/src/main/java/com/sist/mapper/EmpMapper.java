package com.sist.mapper;
import java.util.*;

import com.sist.vo.EmpVO;
public interface EmpMapper {
	/*
	 <select id="empListData" resultType="EmpVO">
		SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal
		FROM emp ORDER BY empno ASC
	</select> 
	 */
	public List<EmpVO> empListData(); //mapper�� ���̵�� = �������̽� �޼ҵ�� ��ġ
}
