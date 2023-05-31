package de.dhbw.karlsruhe.domain.models.core;

public class User {

  private String userName;
  private String password;
  private Setting setting;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    this.setting = new Setting();
  }

  public User(String userName, String password, Setting setting) {
    this.userName = userName;
    this.password = password;
    this.setting = setting;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public Setting getSetting() {
    return setting;
  }

  public void setSetting(Setting setting) {
    this.setting = setting;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
