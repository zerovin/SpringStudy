package com.sist.spring1;
/*
 *  C/S
 *  client / server
 *     |        |
 *   Front    Back
 *              |
 *           �������� => Ŭ���̾�Ʈ���� �����߻�
 *   
 */
public class MainClass {
	public static void main(String[] args) {
		Hello hello=new Hello(); //���õ� ����� ��Ƽ� ����
		hello.sayHello("ȫ�浿"); //�Ѱ��� ����� ����
		//���ռ��� ���� ���α׷� - ������ �ٸ� Ŭ������ ����
		//�������̸� new�� ������� �ʴ´� (new => ���ռ��� ���� ���α׷�)
	}
}
