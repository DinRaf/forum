package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.ConfirmationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 04.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class ConfirmationDaoImpl implements ConfirmationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SAVE_CONFIRM_HASH = "INSERT INTO confirm (user_id, confirm_hash) VALUES (?, ?);";
    private static final String SQL_CONFIRM_USER = "UPDATE short_user SET rights = 'user' WHERE user_id = (SELECT user_id FROM confirm WHERE confirm_hash = ?);";
    private static final String SQL_IS_EXISTS_HASH = "SELECT CASE WHEN EXISTS(SELECT user_id FROM confirm WHERE confirm_hash = ?) THEN TRUE ELSE FALSE END ;";
    private static final String SQL_GET_ID_BY_HASH = "SELECT user_id FROM confirm WHERE confirm_hash = ?;";
    private static final String SQL_UPDATE_HASH = "UPDATE user_info SET pass_hash = ? WHERE user_id = ?;";


    public void confirmUser(String confirmHash) {
        jdbcTemplate.update(SQL_CONFIRM_USER, confirmHash);
    }

    public boolean isExistsHash(String confirmHash) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_HASH, boolean.class, confirmHash);
    }

    public void saveConfirmHash(long userId, String confirmHash) {
        jdbcTemplate.update(SQL_SAVE_CONFIRM_HASH, new Object[]{userId, confirmHash});
    }

    public long getIdByHash(String confirmHash) {
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_HASH, long.class, confirmHash);
    }

    public void updatePassHash(long userId, String passHash) {
        jdbcTemplate.update(SQL_UPDATE_HASH, new Object[]{passHash, userId});
    }
}
