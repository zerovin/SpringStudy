package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.dao.*;
import com.sist.mapper.EmpMapper;
import com.sist.vo.*;
@Repository
public class EmpDAO {
	@Autowired //�ڵ� �޸� �ּ� ����
	private EmpMapper mapper;
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
}
