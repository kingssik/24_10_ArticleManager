package com.KoreaIT.java.AM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);


    int lastArticleId = 0;
    List<Article> articles = new ArrayList<>();

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

      if (cmd.equals("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다");
          continue;
        } else {
          System.out.println("번호  |  제목");
          for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            System.out.printf("%d  |  %s\n", article.id, article.title);
          }
        }
      } else if (cmd.equals("article write")) {
        int id = lastArticleId + 1;
        lastArticleId = id;

        String regDate = test.getNowDateTimeStr();

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, regDate, title, body);
        articles.add(article);

        System.out.printf("%d번 글이 생성되었습니다\n", id);
      } else if (cmd.startsWith("article detail ")) {

        String[] cmdDiv = cmd.split(" ");

        // article detail 1 => "1" -> 1

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
        } else {
          System.out.println("번호 : " + foundArticle.id);
          System.out.println("날짜 : " + foundArticle.regDate);
          System.out.println("제목 : " + foundArticle.title);
          System.out.println("내용 : " + foundArticle.body);
        }

      } else if (cmd.startsWith("article delete ")) {

        String[] cmdDiv = cmd.split(" ");

        // article detail 1 => "1" -> 1

        int id = Integer.parseInt(cmdDiv[2]);

//        Article foundArticle = null;
        int foundIndex = -1;

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
//            foundArticle = article;
            foundIndex = i;
            break;
          }
        }
//        if (foundArticle == null) {
        if (foundIndex == -1) {
          System.out.printf("%d번 게시글은 없습니다.\n", id);
        } else {
          articles.remove(id - 1);
          articles.remove(foundIndex);
          System.out.println(id + "번 게시글이 삭제되었습니다.");
        }

      } else {
        System.out.println("존재하지 않는 명령어입니다");
      }
    }
  }
}

class Article {
  int id;
  String regDate;
  String title;
  String body;

  public Article(int id, String regDate, String title, String body) {
    this.id = id;
    this.regDate = regDate;
    this.title = title;
    this.body = body;
  }
}