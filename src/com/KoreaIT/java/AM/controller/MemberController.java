package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {

  private Scanner sc;
  private List<Member> members;
  private boolean isLogined = false;
  private Member loginedMember = null;

  public MemberController(Scanner sc) {
    this.sc = sc;
    members = new ArrayList<>();
  }


  int lastMemberId = 3;

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

  public void doLogin() {
    if (isLogined) {
      System.out.println("이미 로그인 상태임");
      return;
    }
    System.out.print("아이디 : ");
    String loginId = sc.nextLine();
    System.out.print("비밀번호 : ");
    String password = sc.nextLine();

    // 얘 있나? -> 현재 로그인을 시도하는 사용자가 입력한 아이디랑 일치하는 회원이 나한테 있는가?
    // 뒤져봐야겠다 -> members 를

    Member member = getMemberByLoginId(loginId);

    if (member == null) {
      System.out.println("일치하는 회원이 없어");
      return;
    }

    // 회원 찾았다!
    if (member.password.equals(password) == false) {
      System.out.println("비밀번호 틀렸어");
      return;
    }

    isLogined = true;
    loginedMember = member;

    System.out.printf("로그인 성공! (%s)\n", member.name);

  }

  public void doLogout() {
    if (!isLogined) {
      System.out.println("이미 로그아웃 상태임");
      return;
    }

    isLogined = false;
    loginedMember = null;

    System.out.println("로그아웃 성공!");
  }

  private Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return member;
      }
    }
    return null;
  }


  public void makeTestData() {
    System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
    members.add(new Member(1, Util.getNowDateTimeStr(), "test1", "test1", "회원1"));
    members.add(new Member(2, Util.getNowDateTimeStr(), "test2", "test2", "회원2"));
    members.add(new Member(3, Util.getNowDateTimeStr(), "test3", "test3", "회원3"));
  }


}
