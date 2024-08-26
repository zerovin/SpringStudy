package com.sist.main;
/*
 *  ���̺귯�� VS �����ӿ�ũ
 *  
 *  ���̺귯�� - ���� ���Ǵ� ����� ��Ƽ� �̸� ������ �� Ŭ������ ����
 *            �����Ӱ� ��밡��
 *            �ڹ�, Jsoup
 *  �����ӿ�ũ - ���߿� �ʿ��� ����� �̸� �̸������� �� Ŭ������ ����
 *            �⺻Ʋ�� ������� �ִ�
 *            XML, Annotation
 *          - ��ǥ���� ������ ��ũ
 *            1. MyBatis
 *            2. Ajax, Vue, React => �̹� ������ ������� �ִ�
 *            3. Spring / Spring-Boot
 *          - ����
 *            - �⺻ Ʋ(����)�� ������� �ֱ� ������ ǥ��ȭ
 *              ���� �������� ���� : �ѹ� �ͼ������� ����� �� => ���������� �������� ���԰���
 *            - ���� �Ⱓ ����
 *            - ������� �ܼ�
 *          - ����
 *            - ����� ���� ������ ��ü ����� ��ƴ�
 *              Spring-Boot
 *              Spring Framework
 *              Spring Security
 *              Spring Batch
 *              Spring Data
 *              Spring Cloud
 *            - ���ſ�, ����ӵ��� ������
 *            - �н��ؾߵǴ� ���̺귯���� ���� ����
 *          - �ڹ�, JSP, DB ����
 *  Spring���� ���Ǵ� ��� �غ�
 *  1. Database
 *     - JDBC
 *     - ORM(Object Relation Mapper) : ������ �����ͺ��̽� - MyBatis / JPA / Hibernate
 *  2. Web - MVC => Controller�� �̹� ����
 *  3. Core
 *     - Container : Ŭ������ ��Ƽ� ���� => �������� Ŭ���� ������(����/�Ҹ�, ��ü�� �����ֱ� ����) => �����̳�
 *                   �����ڰ� Ŭ���� ���, ���� => VOŬ���� ����, VO�� ��������� ���������̱� ������ ������󿡼� ����
 *                   - �������� ���Ŀ� �°� ���(������ ����)
 *                      - Ŭ���� ���
 *                        - XML�� �̿� => Spring 4, Spring 5 �ڹ� �̿�
 *                            <bean id="aa" class="com.sist.main.AA">
 *                            map.put("aa", new AA())
 *                            AA a=map.get("aa")
 *                            =======================> �ǹ����� ���� ���
 *                            @Bean("aa")
 *                            public AA aa(){
 *                              return new AA()
 *                            }
 *                        - Annotation �̿�
 *                            @Component("a")
 *                            class A{
 *                            
 *                            }
 *                     - XML/Annotation�� �о Container�� ����
 *                                       | => Spring
 *                                ����� Ŭ�������� ����
 *                   1. Container�� ����
 *                      - Ŭ������ �޸� �Ҵ� (��ü ����)
 *                      - ��ü ã�� => getBean("id")
 *                      - ��ü �Ҹ�
 *     - DI : Setter / Constructor / Method
 *            �������� ���ؼ� ������� �ʱ�ȭ
 *            ��� Ŭ������ ����
 *            �ʱ�ȭ
 *            - setter DI
 *            - ������ DI
 *            - method DI => ��ü ������ init-method
 *                           ��ü �Ҹ�� destroy-method
 *     - AOP : ������ (���������� ����ϴ� ����� ��Ƽ� �ڵ� ȣ��)
 *             Transaction / Security
 *     - ORM : �����ͺ��̽� ���� - MyBatis
 *     - MVC : WEB
 *     
 *                                    BeanFactory : Core => DI (��ü ����/�Ҹ�/�ʱ�ȭ)
 *                                         |
 *                                 ApplicationContext : Core / AOP
 *                                         |=== WebApplicationContext : Core / AOP / MVC
 *                       -------------------------------------
 *                       |                                   |
 *      ApplicationConfigApplicationContext    GenericXmlApplicationContext
 *             Core / AOP / Annotation               Core / AOP / CLOSE
 *      
 *      1. �Ϲ� ������ => ApplicationContext
 *      2. �� => WebApplicationContext
 *      3. ������̼� => AnnotationConfigApplicationContext
 *      =====> Ŭ���� ��� => �ʿ�� ��ϵ� Ŭ������ ã�Ƽ� ��� => �ʿ���� ��� �Ҹ�(System.gc())
 *             
 *      class A{
 *      
 *      }
 *      class B{
 *      
 *      }
 *      class C{
 *      
 *      }
 *                                     �����̳�
 *                               ------------------
 *                                  id     class
 *                               -------------------
 *      <bean id="a" class="A">      a     new A()
 *      <bean id="b" class="B">      b     new B()
 *      <bean id="c" class="C">      c     new C()
 *                           ��������
 *                  A aa = �����̳�.getBean("a") a=���̵��
 *                  => System.gc() => �޸� ����
 *      �����ֱ� 
 *      1. class �б�
 *      2. Ŭ���� �޸� �Ҵ�
 *      3. setter�� �̿��ؼ� �ʱ�ȭ
 *      =====> Spring
 *      4. ������ ��� - ��� Ŭ���� ã��
 *      =====> ������
 *      5. ��� �� Ŭ���� �޸� ����
 *      =====> Spring
 *      
 *      class Board{
 *         public void insert(){}
 *         public void list(){}
 *         public void detail(){}
 *         public void update(){}
 *         public void delete(){}
 *      }
 *      
 *      
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//��ü����
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. �����̳ʿ� XML�Ľ� ��û
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		// 2. �ʿ��� ��ü�� ��û
		Board b=(Board)app.getBean("board");
		System.out.println("b = "+b);
		b.list();
		b.insert();
		Board b1=app.getBean("board", Board.class); //���׸� => �ڵ� ����ȯ
		System.out.println("b1 = "+b1);
		// 3. �ʿ信 ���� �޼ҵ� ȣ�� �� ���
		b1.list();
		b1.insert();
	}

}
