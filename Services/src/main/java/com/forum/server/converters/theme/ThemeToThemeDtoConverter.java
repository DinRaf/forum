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
        return ThemeDto.builder()
                .date(theme.getDate())
                .messagesCount(theme.getMessagesCount())
                .title(theme.getTitle())
                .authorId(theme.getUser().getUserId())
                .status(theme.isStatus())
                .themeId(theme.getThemeId())
                .build();
    }

}
