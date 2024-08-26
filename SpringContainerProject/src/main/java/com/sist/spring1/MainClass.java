package com.sist.spring1;
/*
 *  C/S
 *  client / server
 *     |        |
 *   Front    Back
 *              |
 *           서버변경 => 클라이언트에서 에러발생
 *   
 */
public class MainClass {
	public static void main(String[] args) {
		Hello hello=new Hello(); //관련된 기능을 모아서 관리
		hello.sayHello("홍길동"); //한개의 기능을 가짐
		//결합성이 강한 프로그램 - 수정시 다른 클래스에 영향
		//가급적이면 new를 사용하지 않는다 (new => 결합성이 높은 프로그램)
	}
}
