package com.KoreaIT.java.AM;

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

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, title, body);
        articles.add(article);

        System.out.printf("%d번 글이 생성되었습니다\n", id);
      } else if (cmd.startsWith("article detail ")) {

        String[] cmdDiv = cmd.split(" ");
        System.out.println(cmdDiv[0]);
        System.out.println(cmdDiv[1]);
        System.out.println(cmdDiv[2]);

        // article detail 1 => "1" -> 1

        int id = Integer.parseInt(cmdDiv[2]);

        boolean found = false;

        for(int i = 0; i < articles.size(); i++){
          Article article = articles.get(i);
          if(article.id == id){
            found = true;
            break;
          }
        }
        if(found == false){
          System.out.printf("%d번 게시글은 없습니다.\n",id);
        }else {
          System.out.println("있던데???");
        }

      } else {
        System.out.println("존재하지 않는 명령어입니다");
      }
    }
  }
}

class Article {
  int id;
  String title;
  String body;

  public Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }
}