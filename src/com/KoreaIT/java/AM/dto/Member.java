package com.KoreaIT.java.AM.dto;

public class Member extends Dto {

  public String loginId;
  public String password;
  public String name;

  public Member(int id, String regDate, String loginId, String password, String name) {
    this.id = id;
    this.regDate = regDate;
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }
}
