package com.forum.server.dao.interfaces;

import com.forum.server.dto.tag.TagsDto;
import com.forum.server.models.tag.Tag;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface TagsDao {
    List<Tag> getTagsByThemeId(long themeId);

    void deleteTagsFromThemeByThemeId(long themeId);

    void addTags(long themeId, TagsDto tags);
}
