package com.milktea.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	public static void main(String[]args) {
		//DML JDBC
		String query = "insert into student_tbl values('user02', 'pass02', '이올라', 'F', 23, "
				+ "'user02@ola.com', '01012341234', '경기도 남양주시', '수영.클라이밍', default)";
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			// 4. SQL전송 및 5. 결과받기
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("데이터가 저장되었습니다.");
			} else {
				System.out.println("데이터 저장이 완료되지 않았습니다.");
			}
			// 6. 자원해제
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void dqlJDBC(String[] args) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결
		 * 3. Statement 생성
		 * 4. SQL 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. DBMS 연결
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE"
					, "student", "student"); // 아이디, 패스워드(대소문자 구분)
			// 3. Statement 생성 
			Statement stmt = conn.createStatement();
			// 4. SQL(쿼리문) 전송 및 5. 결과받기
			String query = "select * from student_tbl";
			ResultSet rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				System.out.println("ID : "+rset.getString("STUDENT_ID"));
				System.out.println("NAME : "+rset.getString("STUDENT_NAME"));
				System.out.println("AGE : "+rset.getInt("AGE"));
				System.out.println("DATE : "+rset.getDate("ENROLL_DATE"));
			}			
			// 6. 자원해제 (순서는 중요하지 않음)
			rset.close();
			stmt.close();
			conn.close();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	
	}	
}
