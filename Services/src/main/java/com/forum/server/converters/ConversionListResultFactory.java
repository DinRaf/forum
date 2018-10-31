package com.forum.server.converters;

import com.forum.server.converters.message.ListMessageToMessagesDtoConverter;
import com.forum.server.converters.staticInfo.ListSectionToSectionsDtoConverter;
import com.forum.server.converters.tags.ListTagToTagsDtoConverter;
import com.forum.server.converters.theme.ListThemeSearchToThemesSearchDtoConverter;
import com.forum.server.converters.user.ListShortUsersToShortUsersDtoConverter;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.tag.TagsDto;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.dto.theme.ThemesSearchDto;
import com.forum.server.dto.user.ShortUsersDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.models.tag.Tag;
import com.forum.server.models.theme.ThemeSearch;
import com.forum.server.models.user.ShortUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 31.08.16.
 */

@Component
public class ConversionListResultFactory {

    public MessagesDto convertMessages(List<Message> messages) {
        ListMessageToMessagesDtoConverter converter = new ListMessageToMessagesDtoConverter();
        return converter.convert(messages);
    }

    public SectionsDto convertSections(List<Section> sections) {
        ListSectionToSectionsDtoConverter converter = new ListSectionToSectionsDtoConverter();
        return converter.convert(sections);
    }

    public ShortUsersDto convertShortUsers(List<ShortUser> users) {
        ListShortUsersToShortUsersDtoConverter converter = new ListShortUsersToShortUsersDtoConverter();
        return converter.convert(users);
    }

    public TagsDto convertTags(List<Tag> tags) {
        ListTagToTagsDtoConverter converter = new ListTagToTagsDtoConverter();
        return converter.convert(tags);
    }

    public ThemesSearchDto convertThemes(List<ThemeSearch> themeSearches) {
        ListThemeSearchToThemesSearchDtoConverter converter = new ListThemeSearchToThemesSearchDtoConverter();
        return converter.convert(themeSearches);
    }
}
