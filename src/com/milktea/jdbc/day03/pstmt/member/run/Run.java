package com.milktea.jdbc.day03.pstmt.member.run;

import com.milktea.jdbc.day03.pstmt.common.Singleton;
import com.milktea.jdbc.day03.pstmt.member.view.MemberView;

public class Run {

	public static void main(String[] args) {
		MemberView view = new MemberView();
		view.startProgram();
		Singleton singleton = Singleton.getInstance(); //new Singleton();
	}
}
