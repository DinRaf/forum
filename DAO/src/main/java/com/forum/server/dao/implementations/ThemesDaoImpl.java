package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.models.theme.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class ThemesDaoImpl implements ThemesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String SQL_GET_ID_BY_DATE_AND_USER_ID = "SELECT theme_id FROM theme WHERE user_id = :userId AND date = :date;";
    private static final String SQL_ADD_THEME = "INSERT INTO theme (user_id, section_id, subsection_id, title, date, message_count, status) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public void save(Theme theme) {
        jdbcTemplate.update(SQL_ADD_THEME,
                new Object[]{theme.getUserId(),
                        theme.getSectionId(),
                        theme.getSubsectionId(),
                        theme.getTitle(),
                        theme.getDate(),
                        theme.getMessagesCount(),
                        theme.isStatus()});
    }

    public long getIdByDateAndUserId(long userId, long date) {
        Map<String, Number> params = new HashMap<>();
        params.put("userId", userId);
        params.put("date", date);
        return namedJdbcTemplate.queryForObject(SQL_GET_ID_BY_DATE_AND_USER_ID, params, long.class);
    }
}
