package com.duru.schoolManagement.controller;

public class AppConfig {
  private String mailApiKey;
    private String testToken;
    private String baseUrl;




  public String getMailApiKey(){
      return mailApiKey;
  }



  public String getBaseUrl(){
      return baseUrl;
  }



  public  String getTestToken(){
      return this.testToken;
  }
}
