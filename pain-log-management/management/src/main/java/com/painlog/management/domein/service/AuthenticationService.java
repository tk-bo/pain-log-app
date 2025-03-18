package com.painlog.management.domein.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.painlog.management.domein.repository.UserDao;
import com.painlog.management.dto.login.User;


@Service
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    // JWTの署名に使うシークレットキー（実際はプロパティファイルなどで管理）
    private final String secretKey = "mySecretKey";

    /**
     * ユーザー名とパスワードを検証し、正しければJWTトークンを生成して返却
     * @param username ユーザー名
     * @param password パスワード（平文）
     * @return JWTトークン、認証に失敗した場合は null を返す
     */
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userDao.findByUsername(username);
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

    public void register(String username, String password) {
        // 既に同じユーザー名が存在するかチェック
        Optional<User> existingUser = userDao.findByUsername(username);
        if (existingUser.isPresent()) {
            // ユーザー名が重複している場合は例外をスロー（または適切なエラーハンドリング）
            throw new RuntimeException("Username already exists");
        }

        // パスワードをハッシュ化
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // 新規ユーザーの生成
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashedPassword);

        // ユーザーをデータベースまたはメモリ上のリポジトリに保存する
        userDao.save(newUser);
    }
}
