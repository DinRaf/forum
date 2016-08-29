package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.TokensDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;

/**
 * 24.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class TokensDaoImpl implements TokensDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_IS_EXISTS_TOKEN = "SELECT CASE WHEN EXISTS(SELECT user_id FROM auth WHERE token = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_ADD_TOKEN = "INSERT INTO auth (user_id, token) VALUES (?, ?);";

    public boolean isExistsToken(String token) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_TOKEN, boolean.class, token);
    }

    public void addToken(Integer userId, String token) {
        jdbcTemplate.update(SQL_ADD_TOKEN, new Object[]{userId, token});
    }
}
