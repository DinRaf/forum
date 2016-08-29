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

    private static final String SQL_IS_EXISTS_NICKNAME = "SELECT CASE WHEN EXISTS(SELECT user_id FROM short_user WHERE nick_name = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_IS_EXISTS_MAIL = "SELECT CASE WHEN EXISTS(SELECT user_id FROM user_info WHERE mail = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_GET_HASH_BY_NICKNAME = "SELECT pass_hash FROM user_info WHERE user_id = (SELECT user_id FROM short_user WHERE nick_name = ?);";
    private static final String SQL_GET_HASH_BY_MAIL = "SELECT pass_hash FROM user_info WHERE mail = ?;";
    private static final String SQL_FIND_BY_TOKEN = "SELECT * FROM user_info INNER JOIN short_user ON user_info.user_id = short_user.user_id WHERE short_user.user_id = (SELECT user_id FROM auth WHERE token = ?);";
    private static final String SQL_GET_ID_BY_MAIL = "SELECT user_id FROM user_info WHERE mail = ?;";
    private static final String SQL_GET_ID_BY_NICKNAME = "SELECT user_id FROM short_user WHERE nick_name = ?;";


    private RowMapper<User> userRowMapper() {
        return (rs, i) -> new User.Builder()
                .UserId(rs.getInt("user_id"))
                .NickName(rs.getString("nick_name"))
                .Rating(rs.getInt("rating"))
                .Avatar(rs.getString("avatar"))
                .IsOnline(rs.getBoolean("is_online"))
                .Mail(rs.getString("mail"))
                .DateOfBirth(rs.getInt("birth_date"))
                .Info(rs.getString("info"))
                .Rights(rs.getString("rights"))
                .RegistrationTime(rs.getInt("registration_time"))
                .LastSession(rs.getInt("last_session"))
                .MessagesCount(rs.getInt("messages_count"))
                .ThemesCount(rs.getInt("themes_count"))
                .HashPassword(rs.getString("pass_hash"))
                .build();
    }

    public User findByToken(String token) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_TOKEN, userRowMapper(), token);
    }

    public boolean isExistsMail(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_MAIL, boolean.class, identifier);
    }

    public String getHashByMail(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_HASH_BY_MAIL, String.class, identifier);
    }

    public Integer getIdByMail(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_MAIL, Integer.class, identifier);
    }

    public boolean isExistsNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_NICKNAME, boolean.class, identifier);
    }

    public String getHashByNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_HASH_BY_NICKNAME, String.class, identifier);
    }

    public Integer getIdByNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_NICKNAME, Integer.class, identifier);
    }

    public void save(User user) {

    }
}
