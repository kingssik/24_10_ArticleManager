package com.KoreaIT.java.AM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class test {



  /** 현재 날짜와 시간을 반환하는 함수 (str)**/
  public static String getNowDateTimeStr() {
    // 현재 날짜/시간
    LocalDateTime now = LocalDateTime.now();
    // 포맷팅
     String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // 포맷팅 현재 날짜/시간 출력
    return formatedNow;
  }
}
