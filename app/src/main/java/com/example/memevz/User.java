package com.example.memevz;

public class User {
    private String eMail, username, password;
    private int userId;

    public User(int userId) {
        this.userId = userId;
    }
    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public boolean changeCredentials(int userId, String username, String eMail, String password) {
        return true;
    }

    public boolean isPasswordCorrect (String password) {
        return true;
    }

    public boolean registerUser(String eMail, String username, String password) {
        return true;
    }

    public boolean logIn(String username, String eMail, String password)  {
        return true;
    }

    public boolean logOut() {
        return true;
    }
}
