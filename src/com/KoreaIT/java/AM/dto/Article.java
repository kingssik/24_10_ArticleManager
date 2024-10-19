package com.KoreaIT.java.AM.dto;

public class Article extends Dto {

  public String updateDate;
  public String title;
  public String body;

  public Article(int id, String regDate, String updateDate, String title, String body) {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.title = title;
    this.body = body;
  }
}
