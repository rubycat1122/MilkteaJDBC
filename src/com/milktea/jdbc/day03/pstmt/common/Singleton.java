package com.milktea.jdbc.day03.pstmt.common;

public class Singleton {
	// 싱글톤 -클래스의 인스턴스가 하나만 생성되도록 보장하는 디자인 패턴
	// 데이터베이스 연결 작업은 부하가 큰 작업이고
	// 반복될 수록 데이터 베이스에 무리가 될 수 있음
	// -> 데이터베이스 연결은 비용이 많이 드는 작업, 많은 메모리와 시스템 리소스가 필요함.
	// 그런데 이런 작업을 싱글톤으로 관리하면 한 번만 연결 객체를 생성하여 재사용 할 수 있음
	// -> 성능 향상, 새로운 연결을 생성할 때마다 발생하는 비용 절감 -> DB부하 감소
	// static이면서 Singleton 타입(클래식 명)인 멤버변수 필요
	private static Singleton instance;
	// private 접근제한자인 생성자 생성
	private Singleton() {}
	
	// static이면서 public이고 리턴타입이 Singleton인 메소드 필요
	public static Singleton getInstance() {
		// if문으로 멤버변수 널 체크 후 Null이면 객체 생성
		// null이 아니면 그대로 리턴
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
