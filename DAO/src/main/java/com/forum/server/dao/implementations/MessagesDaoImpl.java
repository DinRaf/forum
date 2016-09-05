package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.models.message.Message;
import com.forum.server.models.message.MessageUpdate;
import com.forum.server.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 30.08.16.
 */
@Repository
public class MessagesDaoImpl implements MessagesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String SQL_ADD_MESSAGE = "INSERT INTO message (user_id, theme_id, date, body, update, rating, updater_id, updater_nick_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ;";
    private static final String SQL_GET_MESSAGE_ID_BY_USER_ID_AND_DATE = "SELECT message_id FROM message WHERE user_id = :userId AND message.date = :date;";
    private static final String SQL_GET_MESSAGES_WITH_OFFSET = "SELECT * FROM message INNER JOIN short_user ON message.user_id = short_user.user_id WHERE theme_id = :themeId ORDER BY message_id OFFSET :offset;";
    private static final String SQL_UPDATE_MESSAGE = "UPDATE message SET update = :update, updater_id = :updater_id, updater_nick_name = :updater_nick_name WHERE message_id = :message_id RETURNING true;";
    private static final String SQL_GET_MESSAGES_WITH_LIMIT_OFFSET = "SELECT * FROM message INNER JOIN short_user ON message.user_id = short_user.user_id WHERE theme_id = :themeId ORDER BY message_id LIMIT :count OFFSET :offset;";
    private static final String SQL_IS_EXISTS_MESSAGE = "SELECT CASE WHEN EXISTS(SELECT theme_id FROM message WHERE message_id = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_DELETE_MESSAGE = "DELETE FROM message WHERE message_id = :message_id;";
    private static final String SQL_DELETE_MESSAGE_MARK = "DELETE FROM message_mark WHERE message_id = :message_id;";
    private static final String SQL_GET_USER_ID_BY_MESSAGE_ID = "SELECT user_id FROM message WHERE message_id = ?;";
    private static final String SQL_GET_OFFSET_BY_ID = "SELECT count(*) FROM message WHERE theme_id = (SELECT theme_id FROM message WHERE message_id = :messageId) AND message_id < :messageId;";
    private static final String SQL_GET_MESSAGES_IDS_BY_THEME_ID = "SELECT message_id FROM message WHERE theme_id = ?;";

    private RowMapper<Message> messageRowMapper(){
        return (rs, rowNum) -> {
            User user = new User.Builder()
                    .UserId(rs.getInt("user_id"))
                    .Nickname(rs.getString("nick_name"))
                    .Rating(rs.getLong("rating"))
                    .Avatar(rs.getString("avatar"))
                    .build();
            return new Message.Builder()
                    .MessageId(rs.getLong("message_id"))
                    .User(user)
                    .Body(rs.getString("body"))
                    .ThemeId(rs.getLong("theme_id"))
                    .Date(rs.getLong("date"))
                    .Update(rs.getLong("update"))
                    .Rating(rs.getLong("rating"))
                    .UpdaterId(rs.getLong("updater_id"))
                    .UpdaterNickname(rs.getString("updater_nick_name"))
                    .build();
        };
    }

    public void save(Message message) {
        jdbcTemplate.update(SQL_ADD_MESSAGE,
                new Object[] {
                        message.getUser().getUserId(),
                        message.getThemeId(),
                        message.getDate(),
                        message.getBody(),
                        message.getUpdate(),
                        message.getRating(),
                        message.getUpdaterId(),
                        message.getUpdaterNickname()
                });
    }

    public long getIdByUserIdAndDate(long userId, long date) {
        Map<String, Number> params = new HashMap<>();
        params.put("userId", userId);
        params.put("date", date);
        return namedJdbcTemplate.queryForObject(SQL_GET_MESSAGE_ID_BY_USER_ID_AND_DATE, params, long.class);
    }

    public void saveUpdate(MessageUpdate messageUpdate, long messageId) {
        Map<String, Object> params = new HashMap<>();
        params.put("update", messageUpdate.getUpdate());
        params.put("updater_id", messageUpdate.getUpdaterId());
        params.put("updater_nick_name", messageUpdate.getUpdaterNickName());
        params.put("message_id", messageId);
        namedJdbcTemplate.queryForObject(SQL_UPDATE_MESSAGE, params, boolean.class);
    }

    public boolean messageIsExists(long messageId) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_MESSAGE, boolean.class, messageId);
    }

    public void deleteMessageById(long messageId) {
        Map<String, Number> params = new HashMap<>();
        params.put("message_id", messageId);
        namedJdbcTemplate.update(SQL_DELETE_MESSAGE, params);
    }

    public List<Message> getMessagesWithLimitOffset(long themeId, long count, long offset) {
        Map<String, Number> params = new HashMap<>();
        params.put("themeId", themeId);
        params.put("offset", offset);
        params.put("count", count);
        return namedJdbcTemplate.query(SQL_GET_MESSAGES_WITH_LIMIT_OFFSET, params, messageRowMapper());
    }

    public List<Message> getMessagesWithOffset(long themeId, long offset) {
        Map<String, Number> params = new HashMap<>();
        params.put("themeId", themeId);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_MESSAGES_WITH_OFFSET, params, messageRowMapper());
    }

    public void deleteMessageMarkByMessageId(long messageId) {
        Map<String, Number> params = new HashMap<>();
        params.put("message_id", messageId);
        namedJdbcTemplate.update(SQL_DELETE_MESSAGE_MARK, params);
    }

    public long getAuthorIdByMessageId(long messageId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_ID_BY_MESSAGE_ID, long.class, messageId);
    }

    public long getOffsetById(long messageId) {
        Map<String, Number> params = new HashMap<>();
        params.put("messageId", messageId);
        return namedJdbcTemplate.queryForObject(SQL_GET_OFFSET_BY_ID, params, long.class);
    }

    public List<Long> getMessagesIdsByThemeId(long themeId) {
        return jdbcTemplate.queryForList(SQL_GET_MESSAGES_IDS_BY_THEME_ID, long.class, themeId);
    }

}
