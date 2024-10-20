package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {

  private Scanner sc;
  List<Article> articles;

  public ArticleController(Scanner sc) {
    this.sc = sc;
    articles = new ArrayList<>();
  }

  int lastArticleId = 3;

  public void showList() {
    if (articles.size() == 0) {
      System.out.println("게시글이 없습니다");
      return;
    }
    System.out.println("번호  |  제목");
    for (int i = articles.size() - 1; i >= 0; i--) {
      Article article = articles.get(i);
      System.out.printf("%d  |  %s\n", article.id, article.title);
    }
  }

  public void doWrite() {
    int id = lastArticleId + 1;
    lastArticleId = id;

    String regDate = Util.getNowDateTimeStr();
    String updateDate = Util.getNowDateTimeStr();

    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String body = sc.nextLine();

    Article article = new Article(id, regDate, updateDate, title, body);
    articles.add(article);

    System.out.printf("%d번 글이 생성되었습니다\n", id);
  }

  public void showDetail(String cmd) {

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
      return;
    }
    System.out.println("번호 : " + foundArticle.id);
    System.out.println("작성 날짜 : " + foundArticle.regDate);
    System.out.println("수정 날짜 : " + foundArticle.updateDate);
    System.out.println("제목 : " + foundArticle.title);
    System.out.println("내용 : " + foundArticle.body);
  }

  public void doDelete(String cmd) {
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
      return;
    }
    articles.remove(foundArticle);
    System.out.println(id + "번 게시글이 삭제되었습니다.");
  }

  public void doModify(String cmd) {
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
      return;
    }
    System.out.println("기존 제목 : " + foundArticle.title);
    System.out.println("기존 내용 : " + foundArticle.body);
    System.out.print("새 제목 : ");
    String newTitle = sc.nextLine();
    System.out.print("새 내용 : ");
    String newBody = sc.nextLine();

    foundArticle.title = newTitle;
    foundArticle.body = newBody;
    foundArticle.updateDate = Util.getNowDateTimeStr();

    System.out.println(id + "번 게시글이 수정되었습니다.");
  }

  public void makeTestData() {
    System.out.println("테스트를 위한 데이터를 생성합니다.");
    articles.add(new Article(1, "2024-12-12 12:12:12", Util.getNowDateTimeStr(), "제목 1", "내용 1"));
    articles.add(new Article(2, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "제목 2", "내용 2"));
    articles.add(new Article(3, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "제목 3", "내용 3"));
  }
}
