package com.milktea.jdbc.day03.pstmt.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	public Member() {}
	
	public Member(String memberId, String memberPwd, String memberName, String gender, int age) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
	}

	public Member(String memberId, String memberPwd, String email, String phone, String address, String hobby) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	public Member(String memberId, String memberPwd, String memberName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	// 객체의 불변성을 위해 setter는 권장하지 않는다.
	public String getMemberId() {
		return memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}

	public String getHobby() {
		return hobby;
	}

	public Date getEnfollDate() {
		return enrollDate;
	}

	@Override // 데이터 확인용
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName + ", gender="
				+ gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
}
