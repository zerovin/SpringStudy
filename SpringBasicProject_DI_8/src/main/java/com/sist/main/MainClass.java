package com.sist.main;
/*
 *  ������ : �ε����� ����
 *         ���¼ҽ� ���̺귯�� => Ȯ���� - �����ؼ� ��밡��
 *         �� �������� �����ӿ�ũ(�����)***
 *           �ִ������ӿ�ũ(����)
 *           �Ϲ� ������(������)
 *  ���������� �����ϴ� ���̺귯��
 *  1. core : ��ü ���� ~ ��ü �Ҹ�, ��ü ����
 *            ��ü �����ֱ� => �����̳� (�淮 �����̳�) => Ŭ���� ������
 *            --------- ��ü �����ֱ� ��� = DI
 *  2. AOP : ���� ����� ��Ƽ� �ڵ����� ó��
 *           OOP(��ü�������α׷�)�� ������ ���α׷�
 *  3. Data Access : JDBC
 *                   ORM - MyBatis, Hibernate, JPA
 *                   OXM - XML �Ľ�
 *                   JMS - Message service
 *  4. WEB : MVC
 *  5. ��Ÿ : Spring Data - ������ �м�
 *        ***Spring Security
 *           Spring Cloud (MSA)
 *           Spring AI
 *           ������ƽ ��ġ - �˻����� => ��� => ����
 *           
 *  DI - ��� �������� �⺻, �ʼ� ����
 *       ������ : Ŭ���� ������ (����~�Ҹ�) => DI
 *              ���α׷��� �°� Ŭ���� ����
 *              �ٸ� �����ӿ�ũ�� ȣȯ���� ����
 *              �ٷ� �Ⱦ �� �ִ�
 *              XML ��� (Spring 4) / Annotation ��� (Spring 5 - ���Ȱ���, �����ڹ�)
 *     - Ŭ������ �����ؼ� �����ϴ� �����̳� Ŭ������ �˾ƾ�
 *       - ���������� �����ϴ� �����̳� (Ŭ���� �ϳ� = ������Ʈ / ��)
 *                       BeanFactory : core(DI)
 *                            |
 *                    ApplicationContext : core(DI), AOP
 *                            |
 *                -------------------------
 *                |                       |
 *  GenericXmlApplicationcontext  AnnotationConfigApplicationContext
 *  => GC => close()              => �ڹٷ� ȯ�漳��
 *                -------------------------
 *                            |
 *                  WebApplicationContext : core(DI), AOP, MVC
 *               
 *        �����̳�(������) - Ŭ������ ���� ��� / ���� ���谡 ���� ��쿡 �ַ� ���
 *                       ex) ��, ����
 *                     - XML ��� - ������ (�����ͺ��̽� ����)
 *                     - ������̼� ��� - ����� ���� (������ ����)
 *        ������ �����ӿ�ũ�� Ư¡
 *        - �淮 �����̳� => DI + DL
 *          1) ��ü ���� => DI
 *             <bean id="" class="">
 *          2) ��ü ã�� => DL
 *             getBean("id")
 *          3) ��ü �Ҹ�
 *        - POJO��� ���, Spring 2.5���� POJO
 *          - ���, �������̽� �������� �����ϰ� �ڹٷθ� ����ϴ� �Ϲ� Ŭ����
 *            �����Ӱ� ����, �ٸ� Ŭ������ ������ ����
 *            �������� ���ÿ� ���߽� ���θ� ����
 *            Ư�� �����ӿ�ũ ����� �������� �ʴ´�
 *            ���ռ��� ���� ���α׷� ����
 *        - �������� ��
 *          - Ŭ������ ���������� ����Ǿ� ��
 *        - Ȯ�强
 *        - �ʿ��� ��� ���̺귯���� ����
 *        
 *  ������ �Թ�
 *  - DI / AOP / DataBase / MVC
 *  
 *  DI
 *  1. setter DI : setXxx() => ���� �ʱ�ȭ
 *  2. ������ DI : ������ �̿� �ʱ�ȭ
 *  3. �޼ҵ� DI
 *     - ��ü ������
 *     - ��ü �Ҹ��
 *  - Ŭ������ Ŭ������ �������� ���� => �޴��� ���� => ���� XML (application.xml)
 *    1. XML ���
 *    2. Annotation
 *    ============== ��������
 *    3. �ڹٱ�� : Spring Framework -> Spring-Boot
 *    
 *  ���������� Ŭ���� ��� - ��� Ŭ���� ���, VO�� ���� (VO�� ����� ���� ��������)
 *  1) XML
 *     - <bean id="" class=""> : �Ѱ��� Ŭ������ ���
 *     - <context:component-scan basepackage="">
 *       => ������
 *       => ��ϵ� Ŭ������ �ݵ�� ������̼� ���
 *          - @Component : �Ϲ� Ŭ����
 *          - @Repository : DAO (�����)
 *          - @Service : BI - �������
 *          - @Controller : Model - forward/redirect
 *          - @RestController : Model - void, ajax
 *                              JSON - �ڹٽ�ũ��Ʈ ����, Vue, React
 *                                     simple-json (X) -> jackson 
 *          - @ControllerAdvice : ���� ����ó��
 *    - @Bean : xml���� ó��
 *  2) DI
 *     XML������ ��� ���� : ��������� �ʱ�ȭ
 *                       setter DI / ������ DI
 *     ex) class A{
 *            private int a,b;
 *            public void setA(int a){
 *               this.a=a;
 *            }
 *            public void setB(int b){
 *               this.b=b;
 *            {
 *            public A(int a, int b){
 *               this.a=a;
 *               this.b=b;
 *            }
 *         }
 *         <bean id="aa" class="A"
 *            <!-- setter -->
 *            p:a="10" ==> setA(10)
 *            p:b="20" ==> setB(20)
 *            
 *            <!-- ������ -->
 *            c:a="100" c:b="200" ==> A(100, 200)
 *         />
 *         
 *         p:name="aaa" ==> �Ϲ� ����
 *         p:name-ref="id��" ==> Ŭ���� ��ü, id�� ���
 *         
 *  1.XML�̿��ϴ� ���
 *  2.������̼�
 *  3.XML + Annotation (���� ���� ���)
 *  => ���̺귯�� Ŭ���� ��� : MyBatis, MVC, Security
 *     ���̺귯���� ������̼��� ���� => XML���
 *  => ����� ���Ǵ� ������̼� �ַ� ���
 *  
 *         
 *                  
 *             ***���� : DI / AOP / Transaction - SI
 *                �ڵ��׽�Ʈ : �ڹٽ�ũ��Ʈ - �ַ��
 *                �׽�Ʈ�� : AWS, ������ ��ɾ� 
 *        
 */
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�����̳� ���
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
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
