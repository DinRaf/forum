package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.models.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 30.08.16.
 */
@Repository
public class MessagesDaoImpl implements MessagesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_ADD_MESSAGE = "INSERT INTO message (user_id, theme_id, date, body, update, rating, updater_id, updater_nick_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ;";
    private static final String SQL_GET_MESSAGE_ID_BY_USER_ID_AND_DATE = "SELECT message_id FROM message WHERE user_id = ? AND message.date = ?;";

    public void save(Message message) {
        jdbcTemplate.update(SQL_ADD_MESSAGE,
                new Object[] {
                        message.getUserId(),
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
        return jdbcTemplate.queryForObject(SQL_GET_MESSAGE_ID_BY_USER_ID_AND_DATE, long.class, new Object[]{userId, date});
    }
}
