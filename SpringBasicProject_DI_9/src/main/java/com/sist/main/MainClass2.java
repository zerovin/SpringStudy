package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

/*
 *  @Component => @Target(value={TYPE}) class������
 *  @Repository => @Target(value={TYPE})
 *  @Autowired => @Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
 *  @Qualifier => @Target(value={FIELD, METHOD, PARAMETER, TYPE, ANNOTATION_TYPE})
 *  
 *  class A{
 *  	@Autowired
 *  	private B b; => FIELD (�������)
 *  
 *  	@Autowired 
 *  	public A(B b){ => CONSTRUCTOR (������)
 *  		this.b=b;
 * 		}
 * 
 * 		@Autowired => ANNOTATION_TYPE (������̼� ��)
 * 		@Qualifier("")
 * 		public void setB(@Autowried B b){ => METHOD
 * 			this.b=b;    ============= PARAMETER
 * 		}
 *  }
 *  
 *  ������̼�
 *  1. ������ - Ŭ���� : TYPE
 *            �Ű����� : PARAMETER
 *            ������ : CONSTRUCTOR
 *            �޼ҵ� : METHOD
 *  Ŭ���� ���
 *  - XML�� �̿� : ���̺귯�� Ŭ���� => ��� ���α׷��� ������
 *  - ANNOTATION�� �̿� : ����� ���� Ŭ���� ���
 *                      @Component : MainClass, ~Manager
 *                      @Repository : DAO
 *                      @Service : DAO ������ => BI(Business Integration)
 *                                 ex) �Խ��� + ��� / Emp + Dept
 *                      @Controller
 *                      @RestController
 *                      @ControllerAdvice
 *  - XML + ANNOTATION (���� ���� ���Ǵ� ����)
 */
@Component("mc")
public class MainClass2 {
	@Autowired //�ڵ����� ���������� ���� ��ü�� ã�Ƽ� �ּҰ� ����
	@Qualifier("mysql") //���� => ��ü����, �ΰ��̻��� ��� �Ѱ� ����
	//@Resouce(name="mysql") => @Autowired + @Qualifier
	private MyDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		MainClass2 mc=(MainClass2)app.getBean("mc");
		mc.dao.connection();
	}
}
