package com.forum.server.converters.theme;

import com.forum.server.dto.theme.ThemesSearchDto;
import com.forum.server.models.theme.ThemeSearch;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ListThemeSearchToThemesSearchDtoConverter implements Converter<List<ThemeSearch>, ThemesSearchDto> {
    @Override
    public ThemesSearchDto convert(List<ThemeSearch> themeSearches) {
        ThemeSearchToThemeSearchDtoConverter converter = new ThemeSearchToThemeSearchDtoConverter();
        return new ThemesSearchDto(themeSearches
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
