package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;

@Service("eds")
public class EmpDeptServiceImpl implements EmpDeptService{
	@Autowired
	private EmpDAO edao;
	@Autowired
	private DeptDAO ddao;
	@Override
	public List<EmpVO> empListData() {
		// TODO Auto-generated method stub
		return edao.empListData();
	}

	@Override
	public List<DeptVO> deptListData() {
		// TODO Auto-generated method stub
		return ddao.deptListData();
	}
	
}
