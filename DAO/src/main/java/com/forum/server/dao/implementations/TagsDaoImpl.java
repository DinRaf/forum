package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.TagsDao;
import com.forum.server.dto.tag.TagDto;
import com.forum.server.models.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class TagsDaoImpl implements TagsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_GET_TAGS_BY_THEME_ID = "SELECT name FROM tag LEFT JOIN theme_tag ON id = tag_id WHERE theme_id = ?;";
    private static final String SQL_DELETE_TAGS_FROM_THEME_BY_THEME_ID = "DELETE FROM theme_tag WHERE theme_id = ?;";
    private static final String SQL_IS_EXISTS_TAG = "SELECT COALESCE((SELECT id FROM tag WHERE LOWER(name) = ?), -1);";
    private static final String SQL_SET_TAG_TO_THEME_BY_THEME_ID = "INSERT INTO theme_tag (theme_id, tag_id) VALUES (?, ?);";
    private static final String SQL_CREATE_TAG = "INSERT INTO tag (name) VALUES (?) RETURNING id";

    private RowMapper<Tag> tagRowMapper() {
        return (rs, i) -> Tag.builder()
                .name(rs.getString("name"))
                .build();
    }

    public List<Tag> getTagsByThemeId(long themeId) {
        return jdbcTemplate.query(SQL_GET_TAGS_BY_THEME_ID, tagRowMapper(), themeId);
    }

    public void deleteTagsFromThemeByThemeId(long themeId) {
        jdbcTemplate.update(SQL_DELETE_TAGS_FROM_THEME_BY_THEME_ID, themeId);
    }

    public void addTags(long themeId, List<TagDto> tags) {
        tags.forEach(tagDto -> {
            String tagName = tagDto.getName();
            int id = getTagIdIfExisits(tagName);
            if (id == -1) {
                addTag(themeId, tagName);
            } else {
                setTag(themeId, id);
            }
        });
    }

    @Override
    public List<Tag> getTagsByCountAndOffset(int count, Integer offset) {
        //TODO реализуй, Саня
        return null;
    }

    @Override
    public List<Tag> getTagsByKeywordAndCountAndOffset(String keyword, int count, Integer offset) {
        //TODO реализуй, Саня
        return null;
    }

    private void addTag(long themeId, String name) {
        setTag(themeId, createTag(name));
    }
    
    private int createTag(String name) {
        return jdbcTemplate.queryForObject(SQL_CREATE_TAG, int.class, name);
    }

    private void setTag(long themeId, Integer id) {
        jdbcTemplate.update(SQL_SET_TAG_TO_THEME_BY_THEME_ID, new Object[]{themeId, id});
    }

    private Integer getTagIdIfExisits(String tagName) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_TAG, Integer.class, tagName.toLowerCase());
    }
}
