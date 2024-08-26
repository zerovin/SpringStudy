package com.sist.spring2;

public class HelloImpl implements Hello{

	@Override
	public void sayHello(String name) { //인터페이스사용 => 메소드명이 바껴도 본인 클래스만 에러
		// TODO Auto-generated method stub
		System.out.println(name+"님 로그인 되었습니다");
	}

}
