package com.sist.aop;
import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
@Component
public class EmpAOP {
	@Autowired
	private EmpDAO dao;
	
	//try ù��°��
	//���۰� ���ÿ� ó�� => ���������� ���
	/*
	 *  public void insert(){
	 *     SqlSession session=null;
	 *     try{
	 *         session=ssf.openSession(); => before
	 *         session.insert.("id")
	 *     }catch(Exception ex{
	 *         ex.printStacktrace(); => afterThrowing
	 *     }finally{
	 *        if(session!=null) => after
	 *           session.close();
	 *     }
	 *  } 
	 *  
	 *  public void aaa(){
	 *     @Before => getConnection()
	 *     try{
	 *        @Around => setAutoCommit(false)
	 *        �ҽ� �ڵ�
	 *        @Around => commit()   
	 *     }catch(Exception ex){
	 *        @AfterThrowing
	 *     }finally{
	 *        @After
	 *     }
	 *     return; => @AfterReturning
	 *  }
	 */
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void getConnection() {
		dao.getConnection();
	}
	
	//finally�� �߰�
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void disConnection() {
		dao.disConnection();
	}
	
	//log ����, Ʈ�����, ����
	@Around(value = "execution(* com.sist.dao.EmpDAO.emp*(..))")
	public Object log(ProceedingJoinPoint jp) {
		Object obj=null;
		try {
			long start=System.currentTimeMillis();
			obj=jp.proceed();
			System.out.println("ȣ��� �޼ҵ� : "+jp.getSignature().getName());
			long end=System.currentTimeMillis();
			System.out.println("�ҿ�ð� : "+(end-start));
		}catch(Throwable ex) {}
		return obj;
	}
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp*(..))", returning="obj")
	public void AafterReturning(Object obj) {
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getSal());
		}
	}
	
	//catch ����
	@AfterThrowing(value ="execution(* com.sist.dao.EmpDAO.emp*(..)", throwing="ex")
	public void afterThrowing(Throwable ex) throws Throwable{
		System.out.println("======== �����߻� =======");
		ex.printStackTrace();
	}
}
