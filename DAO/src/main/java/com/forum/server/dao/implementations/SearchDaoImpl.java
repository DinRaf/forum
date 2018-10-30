package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.SearchDao;
import com.forum.server.dto.tag.TagDto;
import com.forum.server.dto.tag.TagsDto;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.models.tag.Tag;
import com.forum.server.models.theme.ThemeSearch;
import com.forum.server.models.user.ShortUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;

/**
 * 12.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private  final JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_SHORT_USER_PART_1 = "SELECT * FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id ORDER BY ";
    private static final String SQL_GET_SHORT_USER_PART_2 = " DESC LIMIT :count OFFSET :offset ;";
    private static final String SQL_GET_SHORT_USER_BY_KEYWORD_PART_1 = "SELECT * FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id, to_tsquery('russian', :keyword) q WHERE s_user_fts @@ q OR user_fts @@ q ORDER BY ts_rank_cd(s_user_fts, q, 2) DESC, ts_rank_cd(user_fts, q, 2) DESC , ;";
    private static final String SQL_GET_SHORT_USER_BY_KEYWORD_PART_2 = " LIMIT :count OFFSET :offset ;";
    private static final String SQL_GET_USERS_COUNT = "SELECT count(*) FROM short_user;";
    private static final String SQL_GET_USERS_COUNT_BY_KEYWORD = "SELECT count(*) FROM short_user LEFT JOIN user_info ON short_user.user_id = user_info.user_id, to_tsquery('russian', ?) q WHERE s_user_fts @@ q OR user_fts @@ q;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET = "SELECT theme.theme_id, theme.user_id, theme.date, messages_count, status, title, nick_name, section.url FROM theme INNER JOIN section ON theme.section_id = section.section_id INNER JOIN short_user ON short_user.user_id = theme.user_id , to_tsquery('russian', :keyword) q WHERE (theme_fts @@ q OR theme.theme_id = ANY(SELECT theme_id FROM message, to_tsquery('russian', :keyword) q WHERE message.message_fts @@ q))  ORDER BY ts_rank_cd(theme_fts, q, 2) DESC LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme.theme_id, theme.user_id, theme.date, messages_count, status, title, nick_name, section.url FROM theme INNER JOIN section ON theme.section_id = section.section_id INNER JOIN short_user ON short_user.user_id = theme.user_id, to_tsquery('russian', :keyword) q WHERE (theme_fts @@ q OR theme.theme_id = ANY(SELECT theme_id FROM message, to_tsquery('russian', :keyword) q WHERE message.message_fts @@ q)) @@ q AND section.url = :url " +
            "ORDER BY ts_rank_cd(theme_fts, q, 2) DESC LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_WITH_LIMIT_OFFSET = "SELECT theme.theme_id, theme.user_id, date, messages_count, status, title, nick_name, section.url FROM theme INNER JOIN section ON theme.section_id = section.section_id INNER JOIN short_user ON short_user.user_id = theme.user_id " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_THEMES_SECTION_URL_WITH_LIMIT_OFFSET = "SELECT theme.theme_id, theme.user_id, date, messages_count, status, title, nick_name, section.url FROM theme INNER JOIN section ON theme.section_id = section.section_id INNER JOIN short_user ON short_user.user_id = theme.user_id WHERE section.url = :url " +
            "ORDER BY theme_id LIMIT :count OFFSET :offset;";
    private static final String SQL_GET_COUNT = "SELECT SUM(themes_count) FROM section;";
    private static final String SQL_GET_COUNT_BY_KEYWORD = "SELECT count(*) FROM theme, to_tsquery('russian', ?) q WHERE theme_fts @@ q OR theme.theme_id = ANY(SELECT theme_id FROM message, to_tsquery('russian', ?) q WHERE message.message_fts @@ q);";
    private static final String SQL_GET_COUNT_BY_SECTION_URL = "SELECT themes_count FROM section WHERE LOWER(url) = ?;";
    private static final String SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL = "SELECT count(*) FROM theme, to_tsquery('russian', ?) q WHERE (theme_fts @@ q OR theme.theme_id = ANY(SELECT theme_id FROM message, to_tsquery('russian', ?) q WHERE message.message_fts @@ q)) AND section_id = (SELECT section_id FROM section WHERE LOWER(url) = ?);";

    private RowMapper<ShortUser> shortUserRowMapper() {
        return (rs, i) -> new ShortUser.Builder()
                .UserId(rs.getLong("user_id"))
                .Nickname(rs.getString("nick_name"))
                .Rating(rs.getLong("rating"))
                .Avatar(rs.getString("avatar"))
                .Rights(rs.getString("rights"))
                .build();
    }

    private RowMapper<ThemeSearch> themeSearchDtoRowMapper() {
        return (rs, rowNum) -> new ThemeSearch.Builder()
                .Id(rs.getLong("theme_id"))
                .AuthorId(rs.getLong("user_id"))
                .Nickname(rs.getString("nick_name"))
                .Date(rs.getLong("date"))
                .SectionUrl(rs.getString("url"))
                .MessagesCount(rs.getLong("messages_count"))
                .Status(rs.getBoolean("status"))
                .Title(rs.getString("title"))
                .build();
    }

    public int getUsersCount() {
        return jdbcTemplate.queryForObject(SQL_GET_USERS_COUNT, int.class);
    }

    public int getUsersCountByKeyword(String keyword) {
        return jdbcTemplate.queryForObject(SQL_GET_USERS_COUNT_BY_KEYWORD, int.class, keyword.trim().replaceAll("\\s+", ":*") + ":*");
    }

    public List<ShortUser> getShortUsersSortedLimitOffset(Integer offset, int count, String sorting) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("count", count);
        return namedJdbcTemplate.query(SQL_GET_SHORT_USER_PART_1 + sorting + SQL_GET_SHORT_USER_PART_2, params, shortUserRowMapper());
    }

    public List<ShortUser> getShortUsersByKeywordSortedLimitOffset(String keyword, Integer offset, int count, String sorting) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("offset", offset);
        params.put("count", count);
        return namedJdbcTemplate.query(SQL_GET_SHORT_USER_BY_KEYWORD_PART_1 + sorting + SQL_GET_SHORT_USER_BY_KEYWORD_PART_2, params, shortUserRowMapper());
    }

    public List<ThemeSearch> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearch> getThemesByKeywordSectionUrlWithLimitOffset(String keyword, String sectionUrl, int offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword.trim().replaceAll("\\s+", ":*|") + ":*");
        params.put("urlSection", sectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_BY_KEYWORD_SECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearch> getThemesWithLimitOffset(Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public List<ThemeSearch> getThemesBySectionUrlWithLimitOffset(String sectionUrl, Integer offset, int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("url", sectionUrl.toLowerCase());
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_THEMES_SECTION_URL_WITH_LIMIT_OFFSET, params, themeSearchDtoRowMapper());
    }

    public int getThemesCount() {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT, int.class);
    }

    public int getCountByKeyword(String keyword) {
        keyword = keyword.trim().replaceAll("\\s+", ":*|") + ":*";
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD, int.class, new Object[]{keyword, keyword});
    }

    public int getCountBySectionUrl(String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_SECTION_URL, int.class, sectionUrl.toLowerCase());
    }

    public int getCountByKeywordAndSectionUrl(String keyword, String sectionUrl) {
        keyword = keyword.trim().replaceAll("\\s+", ":*|") + ":*";
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_BY_KEYWORD_AND_SECTION_URL, int.class, new Object[]{keyword, keyword, sectionUrl.toLowerCase()});
    }
}
