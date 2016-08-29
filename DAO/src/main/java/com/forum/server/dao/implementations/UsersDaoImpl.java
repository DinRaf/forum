package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 24.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //TODO Реализовать методы
    private static final String SQL_IS_EXISTS_NICKNAME = "SELECT CASE WHEN EXISTS(SELECT user_id FROM short_user WHERE nick_name = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_IS_EXISTS_MAIL = "SELECT CASE WHEN EXISTS(SELECT user_id FROM user_info WHERE nick_name = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_GET_HASH_BY_NICKNAME = "SELECT CASE WHEN EXISTS(SELECT user_id FROM user_info WHERE nick_name = ?)THEN TRUE ELSE FALSE END ;";


    public User findByToken(String token) {
        return null;
    }

    public boolean isExixstsMail(String identifier) {
        return false;
    }

    public String getHashByMail(String identifier) {
        return null;
    }

    public Integer findIdByMail(String identifier) {
        return null;
    }

    public boolean isExistsNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_NICKNAME, boolean.class, identifier);
    }

    public String getHashByNickName(String identifier) {
        return null;
    }

    public Integer findIdByNickName(String identifier) {
        return null;
    }

    public void save(User user) {

    }
}
