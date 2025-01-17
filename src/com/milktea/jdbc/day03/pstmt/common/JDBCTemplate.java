package com.milktea.jdbc.day03.pstmt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "kh";
	private static final String PASSWORD = "kh";
	
	// Connection에 싱글톤을 적용하지 않은 이유
	// Connection 싱글톤을 적용하려는 이유는 Connection Pool(연결을 생성해서 pool에 넣고
	// 재사용할 수 있는 기술)을 사용하려고 하는 것이지만 싱글톤을 적용하고
	// Connection Pool이 동작하는 코드는 없기 때문에 적용하지 않음
	// Connection 을 만드는 메소드를 가지고 있는 JDBCTemplate에 싱글톤을 적용하여 사용함.
	private static JDBCTemplate instance;
	private static Connection conn;
	
	private JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
}
