package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static List<Article> articles = new ArrayList<>();
  static List<Member> members = new ArrayList<>();

  public static void main(String[] args) {

    makeTestData();

    Scanner sc = new Scanner(System.in);

    int lastArticleId = 3;
    int lastMemberId = 0;

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


      if (cmd.equals("member join")) {
        int id = lastMemberId + 1;
        lastMemberId = id;

        String regDate = test.getNowDateTimeStr();
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
        System.out.printf("비밀번호 : ");
        String password = sc.nextLine();
        System.out.printf("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, password, name);
        members.add(member);

        System.out.printf("%d번 회원이 가입되었습니다\n", id);
      } else if (cmd.equals("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다");
          continue;
        }
        System.out.println("번호  |  제목");
        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d  |  %s\n", article.id, article.title);
        }

      } else if (cmd.equals("article write")) {
        int id = lastArticleId + 1;
        lastArticleId = id;

        String regDate = test.getNowDateTimeStr();
        String updateDate = test.getNowDateTimeStr();

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, regDate, updateDate, title, body);
        articles.add(article);

        System.out.printf("%d번 글이 생성되었습니다\n", id);
      } else if (cmd.startsWith("article detail ")) {

        String[] cmdDiv = cmd.split(" ");

        int id = Integer.parseInt(cmdDiv[2]);

        Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 없습니다.\n", id);
          continue;
        }
        System.out.println("번호 : " + foundArticle.id);
        System.out.println("작성 날짜 : " + foundArticle.regDate);
        System.out.println("수정 날짜 : " + foundArticle.updateDate);
        System.out.println("제목 : " + foundArticle.title);
        System.out.println("내용 : " + foundArticle.body);

      } else if (cmd.startsWith("article delete ")) {

        String[] cmdDiv = cmd.split(" ");

        int id = Integer.parseInt(cmdDiv[2]);

        Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 없습니다.\n", id);
          continue;
        }
        articles.remove(foundArticle);
        System.out.println(id + "번 게시글이 삭제되었습니다.");


      } else if (cmd.startsWith("article modify ")) {

        String[] cmdDiv = cmd.split(" ");

        int id = Integer.parseInt(cmdDiv[2]);

        Article foundArticle = null;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 없습니다.\n", id);
          continue;
        }
        System.out.println("기존 제목 : " + foundArticle.title);
        System.out.println("기존 내용 : " + foundArticle.body);
        System.out.print("새 제목 : ");
        String newTitle = sc.nextLine();
        System.out.print("새 내용 : ");
        String newBody = sc.nextLine();

        foundArticle.title = newTitle;
        foundArticle.body = newBody;
        foundArticle.updateDate = test.getNowDateTimeStr();

        System.out.println(id + "번 게시글이 수정되었습니다.");


      } else {
        System.out.println("존재하지 않는 명령어입니다");
      }
    }
  }

  private static boolean isJoinableLoginId(String loginId) {
    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      if (member.loginId.equals(loginId)) {
        return false;
      }
    }
    return true;
  }

  private static void makeTestData() {
    System.out.println("테스트를 위한 데이터를 생성합니다.");
    articles.add(new Article(1, "2024-12-12 12:12:12", test.getNowDateTimeStr(), "제목 1", "내용 1"));
    articles.add(new Article(2, test.getNowDateTimeStr(), test.getNowDateTimeStr(), "제목 2", "내용 2"));
    articles.add(new Article(3, test.getNowDateTimeStr(), test.getNowDateTimeStr(), "제목 3", "내용 3"));
  }
}


class Article {
  int id;
  String regDate;
  String updateDate;
  String title;
  String body;

  public Article(int id, String regDate, String updateDate, String title, String body) {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.title = title;
    this.body = body;
  }
}

class Member {
  int id;
  String regDate;

  String loginId;
  String password;
  String name;

  public Member(int id, String regDate, String loginId, String password, String name) {
    this.id = id;
    this.regDate = regDate;
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }
}