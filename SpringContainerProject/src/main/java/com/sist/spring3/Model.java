package com.sist.spring3;
//공통 기반
public interface Model {
	public String execute();
	default public void write() {}; //구현된 메소드 jdk1.8 => 추상클래스 사용이 거의 사라짐
	// 오버라이딩하는 메소드가 구현해도 안해도 됨
	// 자바스크립트에도 인터페이스 있
}
