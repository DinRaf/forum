package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.models.user.ShortUser;
import com.forum.server.models.user.User;
import com.forum.server.models.user.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String SQL_IS_EXISTS_NICKNAME = "SELECT CASE WHEN EXISTS(SELECT user_id FROM short_user WHERE LOWER(nick_name) = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_IS_EXISTS_MAIL = "SELECT CASE WHEN EXISTS(SELECT user_id FROM user_info WHERE mail = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_IS_EXISTS_ID = "SELECT CASE WHEN EXISTS(SELECT user_id FROM user_info WHERE user_id = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_GET_HASH_BY_NICKNAME = "SELECT pass_hash FROM user_info WHERE user_id = (SELECT user_id FROM short_user WHERE LOWER(nick_name) = ?);";
    private static final String SQL_GET_HASH_BY_MAIL = "SELECT pass_hash FROM user_info WHERE mail = ?;";
    private static final String SQL_FIND_BY_TOKEN = "SELECT * FROM user_info INNER JOIN short_user ON user_info.user_id = short_user.user_id WHERE short_user.user_id = (SELECT user_id FROM auth WHERE token = ?);";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM user_info INNER JOIN short_user ON user_info.user_id = short_user.user_id WHERE short_user.user_id = ?;";
    private static final String SQL_FIND_SHORT_USER_BY_TOKEN = "SELECT * FROM short_user WHERE user_id = (SELECT user_id FROM auth WHERE token = ?);";
    private static final String SQL_GET_ID_BY_MAIL = "SELECT user_id FROM user_info WHERE mail = ?;";
    private static final String SQL_GET_ID_BY_NICKNAME = "SELECT user_id FROM short_user WHERE LOWER(nick_name) = ?;";
    private static final String SQL_GET_ID_BY_TOKEN = "SELECT user_id FROM auth WHERE token = ?;";
    private static final String SQL_ADD_SHORT_USER = "INSERT INTO short_user (nick_name, rating, avatar, rights) VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_SHORT_USER = "UPDATE short_user SET avatar = ? WHERE user_id = ?;;";
    private static final String SQL_ADD_USER_INFO = "INSERT INTO user_info (user_id, mail, birth_date, info, registration_time, last_session, messages_count, themes_count, pass_hash, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_USER_INFO_WITH_PASS_HASH = "UPDATE user_info SET mail = ?, pass_hash = ?, birth_date = ?, info = ?, name = ? WHERE user_id = ?;";
    private static final String SQL_GET_USER_BY_THEME_ID = "SELECT * FROM short_user WHERE user_id = (SELECT user_id FROM theme WHERE theme_id = ?) ;";
    private static final String SQL_GET_RIGHTS_BY_TOKEN = "SELECT rights FROM short_user WHERE user_id = (SELECT user_id FROM auth WHERE token = ?);";
    private static final String SQL_GET_RIGHTS_BY_EMAIL = "SELECT rights FROM short_user WHERE user_id = (SELECT user_id FROM user_info WHERE mail = ?);";
    private static final String SQL_GET_RIGHTS_BY_USER_ID = "SELECT rights FROM short_user WHERE user_id = ?;";
    private static final String SQL_GET_NICKNAME_BY_MAIL = "SELECT nick_name FROM short_user WHERE user_id = (SELECT user_id FROM user_info WHERE mail = ?);";
    private static final String SQL_GET_NICKNAME_BY_TOKEN = "SELECT nick_name FROM short_user WHERE user_id = (SELECT user_id FROM auth WHERE token = ?);";

    private RowMapper<User> userRowMapper() {
        return (rs, i) -> new User.Builder()
                .UserId(rs.getLong("user_id"))
                .Name(rs.getString("name"))
                .Nickname(rs.getString("nick_name"))
                .Rating(rs.getLong("rating"))
                .Avatar(rs.getString("avatar"))
                .Mail(rs.getString("mail"))
                .DateOfBirth(rs.getLong("birth_date"))
                .Info(rs.getString("info"))
                .Rights(rs.getString("rights"))
                .RegistrationTime(rs.getLong("registration_time"))
                .MessagesCount(rs.getLong("messages_count"))
                .ThemesCount(rs.getLong("themes_count"))
                .HashPassword(rs.getString("pass_hash"))
                .build();
    }

    private RowMapper<ShortUser> shortUserRowMapper() {
        return (rs, i) -> new ShortUser.Builder()
                .UserId(rs.getLong("user_id"))
                .Nickname(rs.getString("nick_name"))
                .Rating(rs.getLong("rating"))
                .Avatar(rs.getString("avatar"))
                .Rights(rs.getString("rights"))
                .build();
    }

    public ShortUser findShortUserByToken(String token) {
        return jdbcTemplate.queryForObject(SQL_FIND_SHORT_USER_BY_TOKEN, shortUserRowMapper(), token);
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
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_MAIL, Integer.class,  identifier);
    }

    public boolean isExistsNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_NICKNAME, boolean.class, identifier.toLowerCase());
    }

    public String getHashByNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_HASH_BY_NICKNAME, String.class, identifier.toLowerCase());
    }

    public Integer getIdByNickName(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_NICKNAME, Integer.class, identifier.toLowerCase());
    }

    public ShortUser getUserByThemeId(long themeId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_THEME_ID, shortUserRowMapper(), themeId);
    }

    public void save(User user) {
        jdbcTemplate.update(SQL_ADD_SHORT_USER,
                new Object[]{user.getNickname(),
                        user.getRating(),
                        user.getAvatar(),
                        user.getRights()});
        jdbcTemplate.update(SQL_ADD_USER_INFO,
                new Object[]{getIdByNickName(user.getNickname()),
                        user.getMail(),
                        user.getDateOfBirth(),
                        user.getInfo(),
                        user.getRegistrationTime(),
                        user.getMessagesCount(),
                        user.getThemesCount(),
                        user.getHashPassword(),
                        user.getName()});
    }

    @Override
    public User getUserById(long userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper(), userId);
    }

    public boolean isExistsId(long userId) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_ID, boolean.class, userId);
    }

    public long findIdByToken(String token) {
        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_TOKEN, long.class, token);
    }

    public void update(UserUpdate user, long userId) {

        jdbcTemplate.update(SQL_UPDATE_SHORT_USER,
                new Object[]{user.getAvatar(),
                        userId});
        String query = "UPDATE user_info SET";
        List<Object> objects = new LinkedList<>();
        Map<String, Object> params = new HashMap<>();
        params.put(" name = ?,", user.getName());
        params.put(" birth_date = ?,", user.getDateOfBirth());
        params.put(" pass_hash = ?,", user.getHashPassword());
        params.put(" info = ?,", user.getInfo());
        params.put(" mail = ?,", user.getMail());
        for (Map.Entry entry:
             params.entrySet()) {
            if (entry.getValue() != null) {
                query += entry.getKey();
                objects.add(entry.getValue());
            }
        }
        objects.add(userId);
        jdbcTemplate.update(query.substring(0, query.length() - 1) + " WHERE user_id = ?;", objects.toArray());
    }

    public void updateWithHash(UserUpdate user, long userId) {
        jdbcTemplate.update(SQL_UPDATE_SHORT_USER,
                new Object[]{user.getAvatar(),
                        userId});
        Object[] objects = new Object[]{user.getMail(),
                user.getHashPassword(),
                user.getDateOfBirth(),
                user.getInfo(),
                user.getName(),
                userId};

        jdbcTemplate.update(SQL_UPDATE_USER_INFO_WITH_PASS_HASH,objects);

    }

    public String getNicknameByToken(String token) {
        return jdbcTemplate.queryForObject(SQL_GET_NICKNAME_BY_TOKEN, String.class, token);
    }

    public String getRightsByToken(String token) {
        return jdbcTemplate.queryForObject(SQL_GET_RIGHTS_BY_TOKEN, String.class, token);
    }

    public String getRightsByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_GET_RIGHTS_BY_EMAIL, String.class, email);
    }

    public String getRightsByUserId(long userId) {
        return jdbcTemplate.queryForObject(SQL_GET_RIGHTS_BY_USER_ID, String.class, userId);
    }

    public String getNicknameByMail(String mail) {
        return jdbcTemplate.queryForObject(SQL_GET_NICKNAME_BY_MAIL, String.class, mail);
    }
}
