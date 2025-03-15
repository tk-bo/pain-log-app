package com.painlog.management.domein.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.painlog.management.domein.repository.UserRepository; // ユーザー情報取得用のリポジトリ（仮定）
import com.painlog.management.dto.login.User;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    // JWTの署名に使うシークレットキー（実際はプロパティファイルなどで管理）
    private final String secretKey = "mySecretKey";

    /**
     * ユーザー名とパスワードを検証し、正しければJWTトークンを生成して返却
     * @param username ユーザー名
     * @param password パスワード（平文）
     * @return JWTトークン、認証に失敗した場合は null を返す
     */
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // パスワードはハッシュ化されたものと比較（BCryptを例示）
            if (BCrypt.checkpw(password, user.getPassword())) {
                // JWTトークンを生成（例として1日間有効）
                String token = Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24時間後
                        .signWith(SignatureAlgorithm.HS512, secretKey)
                        .compact();
                return token;
            }
        }
        return null;
    }
}

