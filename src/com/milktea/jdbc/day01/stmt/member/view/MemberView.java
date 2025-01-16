package com.milktea.jdbc.day01.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.milktea.jdbc.day01.stmt.member.controller.MemberController;
import com.milktea.jdbc.day01.stmt.member.model.vo.Member;

public class MemberView {
	// 선언만 한 상태
	private MemberController manage;
	
	public MemberView() {
		// 초기화를 통해 객체를 만들어줌
		manage = new MemberController();
	}
	
	private static final String BYE_MSG = "프로그램이 종료되었습니다.";
	private static final String SUCCESS_MSG = "[서비스 성공]";
	private static final String FAIL_MSG = "[서비스 실패]";
	private static final String NO_DATA_FOUND = "데이터가 없습니다.";

	public void startProgram() {
		finish:
		while(true) {
			int menu = this.printMenu();
			switch(menu) {
			case 1: 
				// 회원 가입
				Member member = this.inputMember();
				int result = manage.registerMember(member);
				if(result > 0) { // 성공여부 체크
					// 성공
					this.printMessage(SUCCESS_MSG);
				} else {
					// 실패
					this.printMessage(FAIL_MSG);
				}
				break;
			case 2: 
				// 회원 전체 조회
				List<Member> mList = manage.showMemberList();
				this.printAllMembers(mList);
				break;
			case 3: 
				// 회원 검색(아이디)
				// select * from member_tbl where member_id = 'user01';
				String memberId = this.inputMemberId();
				member = manage.findOneById(memberId);
				if(member != null) {
					this.printOne(member);
				} else {
					this.printMessage(NO_DATA_FOUND);
				}
				break;
			case 0: 
				this.printMessage(BYE_MSG);
				break finish;
			}
		}
	}

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	private void printOne(Member member) {
		System.out.println("=============== 검색한 회원 정보 ===============");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()
			+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
	}

	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		return member;
	}

	private void printAllMembers(List<Member> mList) {
		System.out.println("====== 회원 전체 정보 ======");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
		for(Member member: mList) {
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()
			+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
		}
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디)");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

}
