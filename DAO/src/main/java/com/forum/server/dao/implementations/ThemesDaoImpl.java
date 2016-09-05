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
    private static final String SQL_ADD_THEME = "INSERT INTO theme (user_id, section_id, subsection_id, title, date, messages_count, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
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
    private static final String SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name " +
            "FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE title ILIKE :keyword AND " +
                "(section_id = (SELECT section_id FROM section WHERE LOWER(url) = :section_url) AND " +
                "subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :subsection_url)) " +
                "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE title ILIKE :keyword " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE title ILIKE :keyword AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = :url) " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE title ILIKE :keyword AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :url) " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title FROM theme, nick_name INNER JOIN short_user ON short_user.user_id = theme.user_id " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_SECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE section_id = (SELECT section_id FROM section WHERE LOWER(url) = :url1) " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :url2) " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name " +
            "FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE " +
            "(section_id = (SELECT section_id FROM section WHERE LOWER(url) = :url1) AND " +
            "subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :url2)) " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";

    private static final String SQL_GET_COUNT = "SELECT SUM(themes_count) FROM section;";
    private static final String SQL_GET_COUNT_BY_KEYWORD = "SELECT count(*) FROM theme WHERE title ILIKE ?;";
    private static final String SQL_GET_COUNT_BY_SECTION_URL = "SELECT themes_count FROM section WHERE LOWER(url) = ?;";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL = "SELECT count(*) FROM theme WHERE title ILIKE ? AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = ?);";
    private static final String SQL_GET_COUNT_BY_SUBSECTION_URL = "SELECT themes_count FROM subsection WHERE LOWER(url) = ?;";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SUBSECTION_URL = "SELECT count(*) FROM theme WHERE title ILIKE ? AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = ?);";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL_AND_SUBSECTION_URL = "SELECT count(*) FROM theme WHERE title ILIKE ? AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = ?) AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = ?);";

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
                    .SectionId(rs.getLong("section_id"))
                    .SubsectionId(rs.getLong("subsection_id"))
                    .Title(rs.getString("title"))
                    .MessagesCount(rs.getLong("messages_count"))
                    .build();
        };
    }

    private RowMapper<ThemeSearchDto> themeSearchDtoRowMapper() {
        return (rs, rowNum) -> new ThemeSearchDto.Builder()
                .Id(rs.getLong("theme_id"))
                .AuthorId(rs.getLong("user_id"))
                .Nickname(rs.getString("nick_name"))
                .Date(rs.getLong("date"))
                .MessagesCount(rs.getLong("messages_count"))
                .Status(rs.getBoolean("status"))
                .Title(rs.getString("title"))
                .build();
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

    public List<ThemeSearchDto> getThemesByKeywordSectionUrlSubsectionUrlWithLimitOffset(String keyword, String sectionUrl, String subsectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyword + "%");
        params.put("section_url", sectionUrl.toLowerCase());
        params.put("subsection_url", subsectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyword + "%");
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordSectionUrlWithLimitOffset(String keyword, String sectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyword + "%");
        params.put("url", sectionUrl.toLowerCase());
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordSubsectionUrlWithLimitOffset(String keyword, String subsectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyword + "%");
        params.put("url", subsectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SUBSECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesWithLimitOffset(Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesBySectionUrlWithLimitOffset(String sectionUrl, Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("url1", sectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_SECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesBySubsectionUrlWithLimitOffset(String subsectionUrl, Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("url2", subsectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_SUBSECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesBySectionUrlSubsectionUrlWithLimitOffset(String sectionUrl, String subsectionUrl, Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("url1", sectionUrl.toLowerCase());
        params.put("url2", subsectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public int getCount() {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT, int.class);
    }

    public int getCountByKeyword(String keyword) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD, int.class, "%" + keyword + "%");
    }

    public int getCountBySectionUrl(String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_SECTION_URL, int.class, sectionUrl.toLowerCase());
    }

    public int getCountByKeywordAndSectionUrl(String keyword, String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL, int.class, new Object[]{"%" + keyword + "%", sectionUrl.toLowerCase()});
    }

    public int getCountBySubsectionUrl(String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_SUBSECTION_URL, int.class, subsectionUrl.toLowerCase());
    }

    public int getCountByKeywordAndSubsectionUrl(String keyword, String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SUBSECTION_URL, int.class, new Object[]{"%" + keyword + "%", subsectionUrl.toLowerCase()});
    }

    public int getCountByKeywordAndSectionUrlAndSubsectionUrl(String keyword, String sectionUrl, String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL_AND_SUBSECTION_URL, int.class, new Object[]{"%" + keyword + "%", sectionUrl.toLowerCase(), subsectionUrl.toLowerCase()});
    }

    public long getThemeIdByMessageId(long messageId) {
        return jdbcTemplate.queryForObject(SQL_GET_THEME_ID_BY_MESSAGE_ID, long.class, messageId);
    }
}
