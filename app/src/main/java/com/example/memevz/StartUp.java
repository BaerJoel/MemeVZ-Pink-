package com.example.memevz;

public class StartUp {
    private String logInToken;

    public StartUp() {

    }

    public String getLogInToken() {
        return logInToken;
    }

    private void setLogInToken(String logInToken) {
        this.logInToken = logInToken;
    }

    private boolean readToken() {
        return true;
    }

    private boolean isConnected() {
        return true;
    }

    private boolean isLoggedIn(String logInToken) {
        return true;
    }

}
