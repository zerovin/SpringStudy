package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//������ ���̺귯�� ���� => XML, Annotation
//�����ӿ�ũ�� �⺻ Ʋ ���� => Ʋ�� �°� �ҽ� �ڵ�, �ڵ��� �ܼ�
/*
 * ���ó�� Ŭ���� : �����ͺ��̽� ����(DAO), Jsoup(Component), Model
 * @Component
 * @Repository
 * @Service
 * ==================
 * @Controller
 * @RestController
 * @ControllerAdvice
 * ================== ��
 * @configuration
 * ==> Ŭ���� ���� ������ ��� �޸� �Ҵ� (��ü ����)
 *     ������ �޸� �Ҵ�
 *     
 * �Ϲ� ������
 *   - DI : Ŭ���� ���� (���)
 *   - AOP : ���� ��� ���� => CommonsModel => �ڵ�ȣ�� (�ݹ�)
 *     AOP�� ���ͼ�Ʈ ������   
 *   - MVC => ORM(MyBatis)
 *   --------------------
 *   - Security
 *   - Batch
 *   - Cloud
 *   - AI
 */

//Ŭ���� ��� ����
//application-context.xml
@Configuration
@ComponentScan(basePackages = {"com.sist.*"})
//<context:component-scan base-package="com.sist.*"/>
public class MusicConfig {

}
