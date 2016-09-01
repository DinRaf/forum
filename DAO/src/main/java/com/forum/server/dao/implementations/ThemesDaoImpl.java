package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    private static final String SQL_ADD_THEME = "INSERT INTO theme (user_id, section_id, subsection_id, title, date, messages_count, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_NUMBER_OF_MESSAGES_IN_THEME = "SELECT messages_count FROM theme WHERE theme_id = ?;" ;
    private static final String SQL_GET_THEME_BY_THEME_ID = "SELECT * FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE theme.theme_id = ?;";
    private static final String SQL_GET_THEME_ID_BY_MESSAGE_ID = "SELECT theme_id FROM message WHERE message_id = ?;;";

    private RowMapper<Theme> themeRowMapper() {
        return (rs, rowNum) -> {
            ShortUser user = new ShortUser.Builder()
                    .UserId(rs.getInt("user_id"))
                    .NickName(rs.getString("nick_name"))
                    .Rating(rs.getLong("rating"))
                    .Avatar(rs.getString("avatar"))
                    .IsOnline(rs.getBoolean("is_online"))
                    .build();

            return new Theme.Builder()
                    .Date(rs.getLong("date"))
                    .Status(rs.getBoolean("status"))
                    .User(user)
                    .ThemeId(rs.getLong("theme_id"))
                    .SectionId(rs.getLong("section_id"))
                    .SubsectionId(rs.getLong("subsection_id"))
                    .Title(rs.getString("title"))
                    .MessagesCount(rs.getLong("messages_count"))
                    .build();
        };
    }

    public void save(Theme theme) {
        jdbcTemplate.update(SQL_ADD_THEME,
                new Object[]{theme.getUser().getUserId(),
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


    public long findTheNumberOfMessagesInTheme(long themeId) {
        return jdbcTemplate.queryForObject(SQL_NUMBER_OF_MESSAGES_IN_THEME, long.class, themeId);
    }

    public Theme getThemeByThemeId(long themeId) {
        return jdbcTemplate.queryForObject(SQL_GET_THEME_BY_THEME_ID, themeRowMapper(), themeId);
    }

    public long getThemeIdByMessageId(long messageId) {
        return jdbcTemplate.queryForObject(SQL_GET_THEME_ID_BY_MESSAGE_ID, long.class, messageId);
    }
}
