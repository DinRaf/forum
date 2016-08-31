package com.forum.server.converters.theme;

import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.theme.Theme;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 31.08.16.
 */
public class ThemeToThemeDtoConverter implements Converter<Theme, ThemeDto> {

    @Override
    public ThemeDto convert(Theme theme) {
        return new ThemeDto.Builder()
                .Date(theme.getDate())
                .MessagesCount(theme.getMessagesCount())
                .Title(theme.getTitle())
                .AuthorId(theme.getUser().getUserId())
                .Status(theme.isStatus())
                .ThemeId(theme.getThemeId())
                .build();
    }

}
