package com.sist.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/*
 *  1. 객체 생성 => 관리
 *  2. 멤버변수의 초기화 후에 객체 생성 => 관리
 *     - setXxx() => setter DI
 *     - 생성자 = constructor DI
 *  <bean id="mem" class="com.sist.main.Member"> => 오류 => default, 매개변수없이 호출
 *    => @AllArgsConstructor로 모든 매개변수가 들어가는 생성자를 만들었기 떄문에 매개변수 넣어서 호출해야 오류X
 *  class A{
 *     private int a,b;
 *     public A(int a, int b){
 *       this.a=a;
 *       this.b=b;
 *     }
 *  }
 *  A a=new A(); => 오류, 매개변수X => new A(10,20)
 *   
 */
/*
@Getter
@Setter
@AllArgsConstructor //모든 매개변수가 들어간 생성자
*/
public class Member {
	private int mno;
	private String name;
	private String sex;
	private String address;
	private String phone;
	
	//모든 매개변수가 들어간 생성자 = @AllArgsConstructor
	public Member(int mno, String name, String sex, String address, String phone) {
		super();
		this.mno = mno;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
	}


	public void print() {
		System.out.println("회원번호 : "+mno);
		System.out.println("이름 : "+name);
		System.out.println("성별 : "+sex);
		System.out.println("주소 : "+address);
		System.out.println("전화 : "+phone);
	}
}
