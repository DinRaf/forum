package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.dto.staticInfo.InfoCreateDto;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.models.staticInfo.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class StaticInfoDaoImpl implements StaticInfoDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_SECTIONS = "SELECT * FROM section ORDER BY section_id;";
    private static final String SQL_GET_INFO_BY_IDENTIFIER = "SELECT * FROM info WHERE identifier = ?;";
    private static final String SQL_IS_EXISTS_INFO = "SELECT CASE WHEN EXISTS(SELECT identifier FROM info WHERE identifier = ?)THEN TRUE ELSE FALSE END;";
    private static final String SQL_IS_EXISTS_SECTION_URL = "SELECT CASE WHEN EXISTS(SELECT url FROM section WHERE LOWER(url) = ?)THEN TRUE ELSE FALSE END;";
    private static final String SQL_CREATE_SECTION = "INSERT INTO section (name, themes_count, url) VALUES (?, 0, ?);";
    private static final String SQL_DELETE_SECTION_BY_URL = "DELETE FROM section WHERE url = ?;";
    private static final String SQL_DELETE_INFO_BY_IDENTIFIER = "DELETE FROM info WHERE identifier = ?;";
    private static final String SQL_ADD_INFO = "INSERT INTO info (identifier, title, text) VALUES (?, ?, ?);";
    private static final String SQL_GET_SECTION_NAME_BY_URL = "SELECT name FROM section WHERE url ILIKE ?;";

    private RowMapper<Section> sectionRowMapper() {
        return (rs, i) -> Section.builder()
                .sectionId(rs.getLong("section_id"))
                .name(rs.getString("name"))
                .themesCount(rs.getLong("themes_count"))
                .url(rs.getString("url"))
                .build();
    }

    private RowMapper<Info> infoRowMapper() {
        return (rs, i) -> Info.builder()
                .title(rs.getString("title"))
                .text(rs.getString("text"))
                .build();
    }

    public List<Section> getSections() {
        return jdbcTemplate.query(SQL_GET_SECTIONS, sectionRowMapper());
    }

    @Override
    public boolean isExistsInfo(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_INFO, boolean.class, identifier);
    }

    @Override
    public Info getInfo(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_INFO_BY_IDENTIFIER, infoRowMapper(), identifier);
    }

    public boolean isExistsSectionUrl(String url) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_SECTION_URL, boolean.class, url.toLowerCase());
    }

    public void createSection(String name, String url) {
        jdbcTemplate.update(SQL_CREATE_SECTION, new Object[]{name, url});
    }

    public void deleteSectionByUrl(String section_url) {
        jdbcTemplate.update(SQL_DELETE_SECTION_BY_URL, section_url);
    }

    public void deleteInfoByIdentifier(String identifier) {
        jdbcTemplate.update(SQL_DELETE_INFO_BY_IDENTIFIER, identifier);
    }

    public void saveInfo(InfoCreateDto infoCreateDto) {
        jdbcTemplate.update(SQL_ADD_INFO, new Object[] {
                infoCreateDto.getIdentifier(),
                infoCreateDto.getTitle(),
                infoCreateDto.getText(),
        });
    }

    public String getSectionNameByUrl(String sectionUrl) {
        return jdbcTemplate.queryForObject(SQL_GET_SECTION_NAME_BY_URL, String.class, sectionUrl);
    }
}
