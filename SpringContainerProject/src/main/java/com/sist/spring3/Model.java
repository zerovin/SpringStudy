package com.sist.spring3;
//���� ���
public interface Model {
	public String execute();
	default public void write() {}; //������ �޼ҵ� jdk1.8 => �߻�Ŭ���� ����� ���� �����
	// �������̵��ϴ� �޼ҵ尡 �����ص� ���ص� ��
	// �ڹٽ�ũ��Ʈ���� �������̽� ��
}
