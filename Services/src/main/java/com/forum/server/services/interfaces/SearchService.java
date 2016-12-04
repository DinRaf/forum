package com.forum.server.services.interfaces;

import com.forum.server.dto.tag.TagsDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.user.SearchUsersDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface SearchService {

    ThemeSearchResultDto searchThemes(String keyword, Integer offset, int count, String sectionUrl);

    SearchUsersDto searchUsers(String keyword, Integer offset, int count, String sorting);

    TagsDto searchTags(String keyword, Integer offset, int count);
}
