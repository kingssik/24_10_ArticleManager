package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {

  private Scanner sc;
  List<Member> members;

  public MemberController(Scanner sc) {
    this.sc = sc;
    members = new ArrayList<>();
  }


  int lastMemberId = 0;

  public void doJoin() {
    int id = lastMemberId + 1;
    lastMemberId = id;

    String regDate = Util.getNowDateTimeStr();
    String loginId = null;
    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = sc.nextLine();
      if (isJoinableLoginId(loginId) == false) {
        System.out.println("이미 사용중인 ID야");
        continue;
      }
      break;
    }
    String password = null;
    while (true) {
      System.out.printf("비밀번호 : ");
      password = sc.nextLine();
      System.out.printf("비밀번호 확인: ");
      String passwordConfirm = sc.nextLine();

      if (password.equals(passwordConfirm) == false) {
        System.out.println("비밀번호를 확인해주세요");
        continue;
      }
      break;
    }
    System.out.printf("이름 : ");
    String name = sc.nextLine();

    Member member = new Member(id, regDate, loginId, password, name);
    members.add(member);

    System.out.printf("%d번 회원이 가입되었습니다\n", id);
  }

  private boolean isJoinableLoginId(String loginId) {
    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return false;
      }
    }
    return true;
  }
}
