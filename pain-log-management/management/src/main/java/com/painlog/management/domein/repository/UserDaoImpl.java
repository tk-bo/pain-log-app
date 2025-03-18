package com.painlog.management.domein.repository;

import java.util.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.painlog.management.dto.login.User;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbc;

    public UserDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<User> findByUsername(String username) throws DataAccessException {
        String sql = "SELECT username, password FROM users WHERE username = ?";
        try {
            List<User> users = jdbc.query(sql, new Object[]{username}, userRowMapper());
            return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
        } catch (DataAccessException e) {
            log.error("データアクセス例外が発生しました。username: {} エラー内容: {}", username, e.getMessage(), e);
            return Optional.empty();
        }
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        };
    }

    @Override
    public User save(User user) throws DataAccessException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbc.update(sql, user.getUsername(), user.getPassword());
        return user;
    }

}
