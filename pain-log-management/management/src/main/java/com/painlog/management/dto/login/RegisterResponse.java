package com.painlog.management.dto.login;

public class RegisterResponse {
    private String message;
    
    // 引数付きコンストラクタ
    public RegisterResponse(String message) {
        this.message = message;
    }
    
    // 必要に応じてgetter/setter
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
