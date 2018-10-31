package com.forum.server.converters.theme;

import com.forum.server.converters.tags.ListTagToTagsDtoConverter;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.models.theme.ThemeSearch;
import org.springframework.core.convert.converter.Converter;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeSearchToThemeSearchDtoConverter implements Converter<ThemeSearch, ThemeSearchDto>{
    @Override
    public ThemeSearchDto convert(ThemeSearch themeSearch) {
        ListTagToTagsDtoConverter converter = new ListTagToTagsDtoConverter();
        return ThemeSearchDto.builder()
                .tags(converter.convert(themeSearch.getTags()))
                .date(themeSearch.getDate())
                .nickname(themeSearch.getNickname())
                .authorId(themeSearch.getAuthorId())
                .id(themeSearch.getId())
                .messagesCount(themeSearch.getMessagesCount())
                .status(themeSearch.isStatus())
                .title(themeSearch.getTitle())
                .sectionUrl(themeSearch.getSectionUrl())
                .build();
    }
}
