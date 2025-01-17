package com.milktea.jdbc.day03.pstmt.member.model.service;

import java.sql.*;
import java.util.List;

import com.milktea.jdbc.day03.pstmt.common.JDBCTemplate;
import com.milktea.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.milktea.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberService {
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		jdbcTemplate = new JDBCTemplate();
		mDao = new MemberDAO();
	}
	// 회원가입
	public int insertMember (Member member) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	// 회원 정보 수정
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	// 회원 탈퇴(삭제)
	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
		// 연결-전달
		try {
			conn = jdbcTemplate.getConnection(); 
			result = mDao.deleteMember(conn, memberId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	// 회원 전체 정보 조회
	public List<Member> selectList() {
		Connection conn = null;
		List<Member> mList = null;
		try {
			conn = jdbcTemplate.getConnection();
			mList = mDao.selectList(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}

	public Member selectOneById(String memberId) {
		Connection conn = null;
		Member member = null;
		try {
			conn = jdbcTemplate.getConnection();
			member = mDao.selectOneById(conn, memberId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}
}
