package com.KoreaIT.java.AM.dto;

public class Article extends Dto {

  public String updateDate;
  public String title;
  public String body;

  public int memberId;

  public Article(int id, String regDate, String updateDate, int memberId, String title, String body) {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.memberId = memberId;
    this.title = title;
    this.body = body;
  }
}
