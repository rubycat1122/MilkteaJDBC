package com.milktea.jdbc.day02.stmt.member.model.dao;

import java.sql.*; // sql 유틸들을 한번에 임포트 해줄 수 있음 *
import java.util.ArrayList;
import java.util.List;

import com.milktea.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDao {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "kh";
	private static final String PASSWORD = "kh";

	/*
	 * 여기서 JDBC 코딩 할거임
	 * 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	// 회원가입
	public int insertMember(Member member) {
		String query = "insert into member_tbl(member_id, member_pwd, member_name, gender, age) "
				+ "values('"+member.getMemberId()+"', '"+member.getMemberPwd()+"', '"+member.getMemberName()+"', '"+member.getGender()+"', "+member.getAge()+")";
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query); // 여기서 예외가 발생하면 close() 코드는 실행되지 않음.
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			// 예외가 발생하든 안하든 실행문을 동작시켜줌.
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	// 회원정보 수정
	public int updateMember(Member member) {
		int result = 0;
		// query = "UPDATE MEMBER_TBL SET member_pwd ='', gender = '', email = '', phone = '"+member.getPhone()+"', address = '"+member.getAddress()+"', hobby = '"+member.getHobby()+"' WHERE MEMBER_ID = '"+member.getMemberId()+"' ";
		String query = "UPDATE MEMBER_TBL SET member_pwd ='"+member.getMemberPwd()+"', email = '"+member.getEmail()
				+"',phone = '"+member.getPhone()+"', address = '"+member.getAddress()+"', hobby = '"+member.getHobby()
				+"' WHERE MEMBER_ID = '"+member.getMemberId()+"' ";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			// 쿼리문 실행 코드 누락 주의!!
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// finally 에서 자원 해제!
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		// 대소문자 구분없이 아이디 삭제 (모두 소문자로 적용해 줌)
		String query = " DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		//		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			// Statement 생성
			stmt = conn.createStatement();
			// 쿼리문 실행
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 성공여부를 알 수 있도록 result를 리턴해 줌
		return result;
	}
	// 회원 전체 정보 조회
	public List<Member> selectList() {
		List<Member> mList = new ArrayList<Member>();
		String query = "select * from member_tbl"; // 정렬은 쿼리문에서 정리 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// rset에 테이블 형태로 데이터가 있으나 그대로 사용못함
			// rset을 member에 담아주는 코드를 작성해주어야 함.
			// 그럴 때 사용하는 rset의 메소드가 next(), getString(), ...이 있음
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				// while문이 동작하면서 모든 레코드에 대한 정보를
				// mLIst에 담을 수 있도록 add 해 줌
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 리턴을 null로 하면 NullPointerException 발생 
		// mList로 리턴해주어야 함
		return mList;
	}
	// 회원 아이디로 검색
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Member member = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				// rset은 member로 변환되어야 함(rsetToMember)
				member = this.rsetToMember(rset);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		String memberId 	= rset.getString("member_id");
		String memberPwd	= rset.getString("member_pwd");
		String memberName 	= rset.getString("member_name");
		String	gender		= rset.getString("gender");
		int 	age 		= rset.getInt("age");
		String email 		= rset.getString("email");
		String phone		= rset.getString("phone");
		String address 		= rset.getString("address");
		String hobby 		= rset.getString("hobby");
		Date enrollDate 	= rset.getDate("enroll_date");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
		return member;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

}
