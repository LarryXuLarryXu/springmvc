package main.java.com.jetsly.webapp.domain;

import java.io.Serializable;

import org.springframework.util.Assert;


public class User implements Serializable {

  private String userName;
  private String passWord;

  public String getUserName() {
    return this.userName;
  }

  public String getPassWord() {
    return this.passWord;
  }

  public void setUserName(String userName) {
    this.userName=userName;
  }

  public void setPassWord(String passWord) {
    this.passWord=passWord;
  }

  @Override
	public String toString() {
		return getUserName() + "," + getPassWord() ;
	}
}
