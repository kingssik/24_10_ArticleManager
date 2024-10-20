package com.KoreaIT.java.AM;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;

import java.util.Scanner;

public class App {
  public void run() {

    Scanner sc = new Scanner(System.in);

    MemberController memberController = new MemberController(sc);
    ArticleController articleController = new ArticleController(sc);

    articleController.makeTestData();
    memberController.makeTestData();

    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = sc.nextLine().trim();
      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요");
        continue;
      }

      if (cmd.equals("system exit")) {
        System.out.println("==프로그램 종료==");
        break;
      }
      if (cmd.equals("member login")) {
        memberController.doLogin();
      } else if (cmd.equals("member logout")) {
        memberController.doLogout();
      } else if (cmd.equals("member join")) {
        memberController.doJoin();
      } else if (cmd.equals("article list")) {
        articleController.showList();
      } else if (cmd.equals("article write")) {
        articleController.doWrite();
      } else if (cmd.startsWith("article detail ")) {
        articleController.showDetail(cmd);
      } else if (cmd.startsWith("article delete ")) {
        articleController.doDelete(cmd);
      } else if (cmd.startsWith("article modify ")) {
        articleController.doModify(cmd);
      } else {
        System.out.println("존재하지 않는 명령어입니다");
      }
    }

  }
}
