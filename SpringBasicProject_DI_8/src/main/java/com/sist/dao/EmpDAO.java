package com.sist.dao;
import java.util.*;
public class EmpDAO {
	private EmpMapper mapper;
	
	public void setMapper(EmpMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
		//ssf.openSession(true)
		//session.close()
	}
}
