package com.milktea.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	// PreparedStatement
	public void preDmlJdbc(String[] args) {
		// DML JDBC
		String query = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3. Statement 생성, PreparedStatement 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "user03");
			pstmt.setString(2, "pass03");
			pstmt.setString(3, "삼올라");
			pstmt.setString(4, "F");
			pstmt.setInt(5, 33);
			pstmt.setString(6, "user03@ola.com");
			pstmt.setString(7, "01099887766");
			pstmt.setString(8, "경기도 광주시");
			pstmt.setString(9, "코딩,수영");
			// 4. SQL전송 및 5. 결과받기
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("데이터가 저장되었습니다.");
			}else {
				System.out.println("데이터 저장이 완료되지 않았습니다.");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원해제
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public void preDqlJDBC() {
		String query = "SELECT * FROM STUDENT_TBL";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE"
						, "STUDENT"
						, "STUDENT");
			// 3. Statement 생성, PreparedStatement 생성
			pstmt = conn.prepareStatement(query);
			// pstmt.setString(1, memberId); // 위치홀더 있으면 생략 불가능
			
			// 4. SQL 전송 및 5. 결과받기
			//rset = stmt.executeQuery(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				System.out.println("ID : " + rset.getString("STUDENT_ID"));
				System.out.println("NAME : " + rset.getString("STUDENT_NAME"));
				System.out.println("AGE : " + rset.getInt("AGE"));
				System.out.println("DATE : " + rset.getDate("ENROLL_DATE"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원해제
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
