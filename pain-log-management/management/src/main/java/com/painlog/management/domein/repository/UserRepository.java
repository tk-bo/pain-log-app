package com.painlog.management.domein.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.painlog.management.dto.login.User;

@Repository
public class UserRepository {

    private final Map<String, User> dummyUsers = new HashMap<>();

    public UserRepository() {
        // 仮のユーザーデータを作成（例: testUser）
        User user = new User();
        user.setUsername("testUser");
        // "password"のハッシュ値（BCryptで生成済み）
        user.setPassword("$2a$10$7EqJtq98hPqEX7fNZaFWoO9e3zO9I8Rb7l5JLZHI1p8N2sB36cK5e");
        dummyUsers.put(user.getUsername(), user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(dummyUsers.get(username));
    }
}

