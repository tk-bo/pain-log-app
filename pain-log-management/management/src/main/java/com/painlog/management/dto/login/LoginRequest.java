package com.painlog.management.dto.login;

public class LoginRequest {
    private String username;
    private String password;

    // コンストラクタ、getter、setter
    public LoginRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
