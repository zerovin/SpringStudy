package com.sist.main;
/*
 *  maven => ant
 *  - ���̺귯�� ���� : pom.xml
 *  - ������Ʈ ���� => ���� ����
 *  - ���� ���� => �ڵ����� war(��)
 *    war => ���� AWS�ü���� ���� => tomcat
 *    AWS �ü��(������)�� ������ ���, ���� IP ���� ����
 *    
 *  ������ - DI*** / AOP(�����ľ�) / MVC-���ͼ�Ʈ/ MyBatis(ORM)-���ͼ�Ʈ,Transaction => security / websocket
 *  
 *  DI
 *  - Ŭ���� ���
 *    - <bean id="" class=""> �Ѱ��� Ŭ���� ���
 *    **<context:component-scan base-package=""> ������ Ŭ���� ���ÿ� �޸� �Ҵ�
 *      �ݵ�� �޸� �Ҵ��� Ŭ������ ����
 *      class ���� ������̼��� �̿��ؼ� ����
 *      => Ŭ���� �������� ����
 *         �Ϲ� Ŭ���� : @Component - MainClass, ~Manager
 *         �����ͺ��̽� ���� : @Repository - ~DAO
 *         ���õ� DAO ���� : @Service
 *         �Ϲ� JSP ���� : @Controller forward/redirect - Model
 *         �ڹٽ�ũ��Ʈ, ��Ʋ�� ���� : @RestController JSON, RestFul - Model
 *                               RestFul - �ٸ� ���� ����, CRUD, 
 *                                          GET : SELECT
 *                                          POST : INSERT
 *                                          PUT : UPDATE
 *                                          DELETE : DELETE
 *         ���� ����ó�� : @ControllerAdvice
 *    - XML���� ���
 *      @Bean => �ڹٷθ� �ڵ��ϴ� ���
 *      
 *  **�������� �����̳� - Ŭ������ ��Ƽ� ����(��ü ���� ~ ��ü �Ҹ�, ��ü�� �����ֱ� ���)
 *                    ��ü ���� - ������� �ʱ�ȭ => DI
 *                    ������� �ʱ�ȭ - 1.setter : setXxx()
 *                                    ex) p:name => setName()
 *                                  2.������ �̿�
 *                                    ex) e:name => ������(String name)
 *                                - ��������� Ŭ���������� ���� ����
 *                                  �����ͺ��̽� ������ ����Ŭ ������ ���۰� ���ÿ� ����
 *    MVC�� �������� �Ϻ� ���̺귯��
 *    
 *  �����ڰ� ���� ����
 *  1) ������� - �Ѱ��� ���� ���
 *              => XML + Java => Java
 *  2) ���������� ��ƴ� - XML�� �ڵ尡 �����
 *                     => Java - ������.class
 *  ===> ������ 5�������� ����, ���������� ȣȯ����
 *       ������ 6�� ȣȯX javax => jakarata
 *       
 *  MyBatis : @Select("") �Ű����� �ݵ�� 1�� => �ֱ� ������ ��밡�� 
 *            ������ SQL����(��������) => XML������ ������̼����� 
 *            PROCEDURE / TRIGGER => ��� SQL
 *  
 */
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO2 dao=(EmpDAO2)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getSal());
		}
	}

}
