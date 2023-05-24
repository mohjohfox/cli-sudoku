package de.dhbw.karlsruhe.domain.models;

public class User {

    private String userName;
    private String password;
    private Setting setting;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.setting = new Setting();
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
}
