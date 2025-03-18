package com.painlog.management.domein.repository;

import java.util.Optional;
import org.springframework.dao.DataAccessException;
import com.painlog.management.dto.login.User;

public interface UserDao {
    public Optional<User> findByUsername(String username) throws DataAccessException;
    public User save(User user) throws DataAccessException;
}
