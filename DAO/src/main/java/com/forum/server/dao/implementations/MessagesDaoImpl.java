package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.models.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public List<Message> getMessagesWithOffset(long themeId, long offset, long messagesCount) {
        return null;
    }


}
