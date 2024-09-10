package com.sist.vo;
import java.util.*;

import lombok.Data;
// JOIN => 포함클래스
@Data
public class EmpVO {
	private int empno, sal;
	private String ename, job, dbday;
	private Date hiredate;
	// 포함 has-a
	private DeptVO dvo=new DeptVO();
	private SalgradeVO svo=new SalgradeVO();
}
