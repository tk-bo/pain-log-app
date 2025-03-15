package com.painlog.management.dto.login;


public class User {
    private String username;
    private String password; // BCryptでハッシュ化されたパスワード

    // デフォルトコンストラクタ
    public User() {
    }

    // コンストラクタ（必要に応じて利用）
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter / setter
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

