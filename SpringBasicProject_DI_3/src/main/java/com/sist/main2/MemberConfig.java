package com.sist.main2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//ȯ�漳�� XML���
@Configuration
public class MemberConfig {
	/*
	 *	<bean id="mem" class="com.sist.main2.Member"
	 *		p:mno="1"
	 *		p:name="ȫ�浿
	 *		p:sex="����"
	 *	/> 
	 *
	 *	id="mem"
	 *	@Bean("mem")
	 *
	 *	class="com.sist.main2.Member" => Member m=new Member();
	 *
	 *	p:mno="1"            m.setMno(1);
	 *	p:name="ȫ�浿  ===>   m.setName("ȫ�浿");
	 *	p:sex="����"          m.setSex("����");
	 *
	 *	=> �Ѱ��� ���� ��� (�ڹٷ� �ڵ�)
	 *     XML => ������̼����� ����	
	 *     
	 *  <bean id="mem" class="com.sist.main2.Member"
	 *		p:mno="1"
	 *		p:name="ȫ�浿
	 *		p:sex="����"
	 *		scope="prototype"
	 *	/> 
	 */
	@Bean("mem")
	@Scope("prototype")
	public Member member() {
		Member m=new Member();
		m.setMno(1);
		m.setName("ȫ�浿");
		m.setSex("����");
		return m;
	}
}
