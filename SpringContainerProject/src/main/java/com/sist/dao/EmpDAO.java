package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
//@Repository
public class EmpDAO {
	//@Autowired //�ڵ����� => ������ ��ü�� �ּҸ� ����
	private EmpMapper mapper;

	public void setMapper(EmpMapper mapper) { //@Autowired ��� setter
		this.mapper = mapper;
	}

	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
