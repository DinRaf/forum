package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.theme.ThemeUpdate;
import com.forum.server.models.user.ShortUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
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
    private static final String SQL_ADD_THEME = "INSERT INTO theme (user_id, section_id, subsection_id, title, date, messages_count, status) VALUES (?, (SELECT section_id FROM section WHERE LOWER(url) = ?), (SELECT subsection_id FROM subsection WHERE LOWER(url) = ?), ?, ?, ?, ?);";
    private static final String SQL_NUMBER_OF_MESSAGES_IN_THEME = "SELECT messages_count FROM theme WHERE theme_id = ?;" ;
    private static final String SQL_GET_THEME_BY_THEME_ID = "SELECT * FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE theme.theme_id = ?;";
    private static final String SQL_GET_THEME_ID_BY_MESSAGE_ID = "SELECT theme_id FROM message WHERE message_id = ?;;";
    private static final String SQL_GET_USER_ID_BY_THEME_ID = "SELECT updater_id FROM theme WHERE theme_id = ?;";
    private static final String SQL_SAVE_UPDATE = "UPDATE theme SET title = :title WHERE theme_id = :theme_id RETURNING true;";
    private static final String SQL_IS_EXISTS_THEME = "SELECT CASE WHEN EXISTS(SELECT user_id FROM theme WHERE theme_id = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_DELETE_MARKS_FROM_MESSAGES_IN_THEME = "DELETE FROM message_mark WHERE message_id = :message_id;";
    private static final String SQL_DELETE_MESSAGES_IN_THEME = "DELETE FROM message WHERE theme_id = :theme_id;";
    private static final String SQL_DELETE_THEME = "DELETE FROM theme WHERE theme_id = :theme_id;";
    private static final String SQL_GET_ALL_MESSAGES_IN_THEME = "SELECT message_id FROM message WHERE theme_id = :theme_id;";


    private RowMapper<Theme> themeRowMapper() {
        return (rs, rowNum) -> {
            ShortUser user = new ShortUser.Builder()
                    .UserId(rs.getInt("user_id"))
                    .Nickname(rs.getString("nick_name"))
                    .Rating(rs.getLong("rating"))
                    .Avatar(rs.getString("avatar"))
                    .build();

            return new Theme.Builder()
                    .Date(rs.getLong("date"))
                    .Status(rs.getBoolean("status"))
                    .User(user)
                    .ThemeId(rs.getLong("theme_id"))
                    .Title(rs.getString("title"))
                    .MessagesCount(rs.getLong("messages_count"))
                    .build();
        };
    }

    public void save(Theme theme) {
        jdbcTemplate.update(SQL_ADD_THEME,
                new Object[]{theme.getUser().getUserId(),
                        theme.getSectionUrl().toLowerCase(),
                        theme.getSectionUrl().toLowerCase() + "/" + theme.getSubsectionUrl().toLowerCase(),
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

    public long getAuthorIdByThemeId(long themeId) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_ID_BY_THEME_ID, long.class, themeId);
    }

    public Theme getThemeByThemeId(long themeId) {
        return jdbcTemplate.queryForObject(SQL_GET_THEME_BY_THEME_ID, themeRowMapper(), themeId);
    }

    public void saveUpdate(ThemeUpdate themeUpdate, long themeId) {
        Map<String, Object> params = new  HashMap<>();
        params.put("title", themeUpdate.getTitle());
        params.put("theme_id", themeId);
        namedJdbcTemplate.queryForObject(SQL_SAVE_UPDATE, params, boolean.class);
    }

    public boolean themeIsExists(long themeId) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_THEME, boolean.class, themeId);
    }

    private List<Long> getAllMessagesIdInTheme(long themeId) {
        Map<String, Number> params = new HashMap<>();
        params.put("theme_id", themeId);
        return namedJdbcTemplate.queryForList(SQL_GET_ALL_MESSAGES_IN_THEME, params, long.class);
    }

    private void deleteMarksFromMessagesInTheme(List<Long> messagesId) {
        for (long messageId :
                messagesId) {
            Map<String, Number> params = new HashMap<>();
            params.put("message_id", messageId);
            namedJdbcTemplate.update(SQL_DELETE_MARKS_FROM_MESSAGES_IN_THEME, params);
        }
    }

    private void deleteMessagesInTheme(long themeId) {
        Map<String, Number> params = new HashMap<>();
        params.put("theme_id", themeId);
        namedJdbcTemplate.update(SQL_DELETE_MESSAGES_IN_THEME, params);
    }

    public void deleteTheme(long themeId) {
        List<Long> messagesId = getAllMessagesIdInTheme(themeId);
        deleteMarksFromMessagesInTheme(messagesId);
        deleteMessagesInTheme(themeId);
        Map<String, Number> params = new HashMap<>();
        params.put("theme_id", themeId);
        namedJdbcTemplate.update(SQL_DELETE_THEME, params);
    }

    public long getThemeIdByMessageId(long messageId) {
        return jdbcTemplate.queryForObject(SQL_GET_THEME_ID_BY_MESSAGE_ID, long.class, messageId);
    }
}
