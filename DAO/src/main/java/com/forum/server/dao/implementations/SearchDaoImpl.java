package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.SearchDao;
import com.forum.server.dto.theme.ThemeSearchDto;
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
 * 12.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_SHORT_USER_SORTED_LIMIT_OFFSET = "SELECT * FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id ORDER BY :sorting LIMIT :count OFFSET :offset ;";
    private static final String SQL_GET_SHORT_USER_BY_KEYWORD_SORTED_LIMIT_OFFSET = "SELECT * FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id, to_tsquery('russian', :keyword) q WHERE s_user_fts @@ q OR user_fts @@ q ORDER BY ts_rank_cd(s_user_fts, q, 2) DESC, ts_rank_cd(user_fts, q, 2) DESC , :sorting LIMIT :count OFFSET :offset ;";
    private static final String SQL_GET_USERS_COUNT = "SELECT count(*) FROM short_user;";
    private static final String SQL_GET_USERS_COUNT_BY_KEYWORD = "SELECT count(*) FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id, to_tsquery('russian', ?) q WHERE s_user_fts @@ q OR user_fts @@ q;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name " +
            "FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND " +
            "(section_id = (SELECT section_id FROM section WHERE LOWER(url) = :section_url) AND " +
            "subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :subsection_url)) " +
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC, ts_rank_cd(message_fts, q, 2) LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id  LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q " +
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC, ts_rank_cd(message_fts, q, 2) LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id  LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = :urlSection) " +
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC, ts_rank_cd(message_fts, q, 2) LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SUBSECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme_id, theme.user_id, date, messages_count, status, title, nick_name FROM theme INNER JOIN short_user ON short_user.user_id = theme.user_id  LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = :urlSubsection) " +
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC, ts_rank_cd(message_fts, q, 2) LIMIT :count OFFSET :offset;";
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
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC, ts_rank_cd(message_fts, q, 2) LIMIT :count OFFSET :offset;";

    private static final String SQL_GET_COUNT = "SELECT SUM(themes_count) FROM section;";
    private static final String SQL_GET_COUNT_BY_KEYWORD = "SELECT count(*) FROM theme LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q;";
    private static final String SQL_GET_COUNT_BY_SECTION_URL = "SELECT themes_count FROM section WHERE LOWER(url) = ?;";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL = "SELECT count(*) FROM theme LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = ?);";
    private static final String SQL_GET_COUNT_BY_SUBSECTION_URL = "SELECT themes_count FROM subsection WHERE LOWER(url) = ?;";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SUBSECTION_URL = "SELECT count(*) FROM theme  LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = ?);";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL_AND_SUBSECTION_URL = "SELECT count(*) FROM theme  LEFT JOIN message ON theme.theme_id = message.theme_id, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR message_fts @@ q AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = ?) AND subsection_id = (SELECT subsection_id FROM subsection WHERE LOWER(url) = ?);";

    private RowMapper<ShortUser> shortUserRowMapper() {
        return (rs, i) -> new ShortUser.Builder()
                .UserId(rs.getLong("user_id"))
                .Nickname(rs.getString("nick_name"))
                .Rating(rs.getLong("rating"))
                .Avatar(rs.getString("avatar"))
                .Rights(rs.getString("rights"))
                .build();
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

    public int getUsersCount() {
        return jdbcTemplate.queryForObject(SQL_GET_USERS_COUNT, int.class);
    }

    public int getUsersCountByKeyword(String keyword) {
        return jdbcTemplate.queryForObject(SQL_GET_USERS_COUNT_BY_KEYWORD, int.class, keyword.trim().replaceAll("\\s+", ":*|") + ":*");
    }

    public List<ShortUser> getShortUsersSortedLimitOffset(Integer offset, int count, String sorting) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("count", count);
        params.put("sorting", "short_user." + sorting);
        return namedJdbcTemplate.query(SQL_GET_SHORT_USER_SORTED_LIMIT_OFFSET, params, shortUserRowMapper());

    }

    public List<ShortUser> getShortUsersByKeywordSortedLimitOffset(String keyword, Integer offset, int count, String sorting) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("offset", offset);
        params.put("count", count);
        params.put("sorting", "short_user." + sorting);
        return namedJdbcTemplate.query(SQL_GET_SHORT_USER_BY_KEYWORD_SORTED_LIMIT_OFFSET, params, shortUserRowMapper());
    }


    public List<ThemeSearchDto> getThemesByKeywordSectionUrlSubsectionUrlWithLimitOffset(String keyword, String sectionUrl, String subsectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("section_url", sectionUrl.toLowerCase());
        params.put("subsection_url", subsectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_SUBSECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordSectionUrlWithLimitOffset(String keyword, String sectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("urlSection", sectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearchDto> getThemesByKeywordSubsectionUrlWithLimitOffset(String keyword, String subsectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("urlSubsection", subsectionUrl.toLowerCase());
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

    public int getThemesCount() {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT, int.class);
    }

    public int getCountByKeyword(String keyword) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD, int.class, keyword.trim().replaceAll("\\s+", ":*|") + ":*");
    }

    public int getCountBySectionUrl(String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_SECTION_URL, int.class, sectionUrl.toLowerCase());
    }

    public int getCountByKeywordAndSectionUrl(String keyword, String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL, int.class, new Object[]{keyword.trim().replaceAll("\\s+", ":*|") + ":*", sectionUrl.toLowerCase()});
    }

    public int getCountBySubsectionUrl(String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_SUBSECTION_URL, int.class, subsectionUrl.toLowerCase());
    }

    public int getCountByKeywordAndSubsectionUrl(String keyword, String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SUBSECTION_URL, int.class, new Object[]{keyword.trim().replaceAll("\\s+", ":*|") + ":*", subsectionUrl.toLowerCase()});
    }

    public int getCountByKeywordAndSectionUrlAndSubsectionUrl(String keyword, String sectionUrl, String subsectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL_AND_SUBSECTION_URL, int.class, new Object[]{keyword.trim().replaceAll("\\s+", ":*|") + ":*", sectionUrl.toLowerCase(), subsectionUrl.toLowerCase()});
    }
}
