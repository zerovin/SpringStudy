package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.MyDAO;

/*
 *  1. DI => Ŭ������ Ŭ���� ���� ���� : ���� �޴��� => Ŭ���� ������ ���� ����
 *     1) XML ��� : Spring 4 => �������������ӿ�ũ(�ǹ�)
 *     2) Annotation ��� => ���̺귯�� Ŭ���� ��Ͻÿ� ����� �޾Ƽ� ó��
 *     3) XML + Annotation : XML - ���̺귯�� ��Ͻ� (MyBatis, ����)
 *                           ����� ���Ǵ� ������̼����� ����
 *                           ������Ʈ���� ���v���� ���Ǵ� �κ� => XML
 *                           �����ڸ��� �����ϴ� �κ� => Annotation
 *     4) ���� �ڹٸ� �̿� : XML - �������� Ŭ���� ������(����~�Ҹ�)
 *                             �Ѱ��� Ŭ���� ���
 *                             - <bean id="" class="" scope="">
 *                               id - ������, �ߺ��� �� ����, Ŭ���� ��ü�� ã�� ��쿡 ���
 *                               class - �޸� �Ҵ��� Ŭ���� ����, �ݵ�� ��Ű������
 *                               scope - �̱���(�Ѱ��� ��ü�� �̿��ϴ� ���)
 *                                       prototype
 *                               �ڹٷ� ����
 *                               - @Bean("id")
 *                             ��Ű�� ���� ��� 
 *                             - <context:component-scan base-package="��Ű����">
 *                               �������� �޸� �Ҵ�
 *                               �ڹٷ� ����
 *                               - @ComponentScan(basePackage={""})
 *                             - <mybatis-spring base-package="">
 *                               �ڹٷ� ����
 *                               - @MapperScan(basePackage={""})
 *                             - Spring-Boot:Framework�� ����
 *                               - application.properties
 *                                 server.port=80
 *                               - application.yml
 *                                 server:
 *                                    port:80
 *                             - Ŭ���� ���� @Component : �Ϲ� Ŭ����
 *                                        @Repository : DAO
 *                                        @Service : DAO ������ ����, BI
 *                                        ������������� �������� ��밡�� �������������
 *                                        @Controller : Model, Ŭ���� ������ �����Ǹ� ��ó���� �ȵȴ�
 *                                                      forward, redirect, ȭ�麯������
 *                                        @RestController : Model, ȭ�麯��X JSON����, �ڹٽ�ũ��Ʈ ����
 *                                                          GET : SELECT
 *                                                          POST : UPDATE
 *                                                          PUT : INSERT
 *                                                          DELETE : DELETE
 *                                        @ControllerAdvice : ���������� ����ó��
 *                             - �������� ��ϵ� ��ü ã�� => getBean("id��")
 *                               ���������� ��ϵ� Ŭ���� ���� => �����̳�
 *                                                         BeanFactory
 *                                                              |
 *                                                      ApplicationContext
 *                                                              |
 *                                              ------------------------------------
 *                                              |                                  |
 *                             AnnotationConfigApplicationContext      WebApplicationContext(MVC)
 *  2. AOP => �������� ���Ǵ� Ŭ������ ����� ��Ƽ� ó�� => �ڵ� ȣ��
 *            Aspect : �������� ���Ǵ� ����� ��Ƽ� ����, ������
 *            1) PointCut : � �޼ҵ忡 ����
 *            2) JoinPoint : � ��ġ���� ����
 *                           public void display(){
 *                              ó���κ� => Before : driver���
 *                              try{
 *                              	ó���κ� setAutoCommit(false)
 *                                  ó���κ� commit() : Ʈ�����ó��, �α�����
 *                                  ===> Around
 *                              }catch(Exception e){
 *                                  ó���κ� => after-throwing : catch ����
 *                              }finally{
 *                                  ó���κ� => after
 *                              }
 *                              ó���κ� => after-returning
 *                           }
 *            3) Advice : PointCut + JoinPoint
 *            4) Weaving : �ҽ��� �����ϴ� ����
 *  3. MVC => ��
 *  4. ORM => Mybatis
 *  *** DI, AOP, MVC ����
 *  ==================== Basic
 *  5. ���ͼ�Ʈ : �ڵ��α���, ����
 *  6. ���뿹��ó��
 *  7. ����
 *  8. ��ä�� 
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MyDAO dao=(MyDAO)app.getBean("dao");
		//MyDAO dao=new MyDAO();
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
		String s=dao.find("Hello");
		//System.out.println(s);
	}

}
