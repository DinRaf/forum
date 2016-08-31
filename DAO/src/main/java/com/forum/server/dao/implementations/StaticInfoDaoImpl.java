package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.models.staticInfo.Subsection;
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
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_SECTIONS = "SELECT * FROM section;";
    private static final String SQL_GET_SUBSECTIONS_BY_SECTION_ID = "SELECT * FROM subsection where section_id = ?;";
    private static final String SQL_GET_INFO_BY_IDENTIFIER = "SELECT * FROM info WHERE identifier = ?;";
    private static final String SQL_IS_EXISTS_INFO = "SELECT CASE WHEN EXISTS(SELECT identifier FROM info WHERE identifier = ?)THEN TRUE ELSE FALSE END;";
    private static final String SQL_IS_EXISTS_SECTION_ID = "SELECT CASE WHEN EXISTS(SELECT subsection_id FROM subsection WHERE section_id = ?)THEN TRUE ELSE FALSE END;";

    private RowMapper<Section> sectionRowMapper() {
        return (rs, i) -> new Section.Builder()
                .SectionId(rs.getLong("section_id"))
                .Name(rs.getString("name"))
                .ThemesCount(rs.getLong("themes_count"))
                .SubsectionsCount(rs.getLong("subsections_count"))
                .build();
    }

    private RowMapper<Subsection> subsectionRowMapper() {
        return (rs, i) -> new Subsection.Builder()
                .Subsection_id(rs.getLong("subsection_id"))
                .Name(rs.getString("name"))
                .ThemesCount(rs.getLong("themes_count"))
                .build();
    }

    private RowMapper<Info> infoRowMapper() {
        return (rs, i) -> new Info.Builder()
                .Title(rs.getString("title"))
                .Text(rs.getString("text"))
                .build();
    }

    public List<Section> getSections() {
        return jdbcTemplate.query(SQL_GET_SECTIONS, sectionRowMapper());
    }

    @Override
    public List<Subsection> getSubsections(long sectionId) {
        return jdbcTemplate.query(SQL_GET_SUBSECTIONS_BY_SECTION_ID, subsectionRowMapper(), sectionId);
    }

    @Override
    public boolean isExistsInfo(String identifier) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_INFO, boolean.class, identifier);
    }

    @Override
    public Info getInfo(String identifier) {
        return jdbcTemplate.queryForObject(SQL_GET_INFO_BY_IDENTIFIER, infoRowMapper(), identifier);
    }

    public boolean isExistsSectionId(long sectionId) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_SECTION_ID, boolean.class, sectionId);
    }
}
