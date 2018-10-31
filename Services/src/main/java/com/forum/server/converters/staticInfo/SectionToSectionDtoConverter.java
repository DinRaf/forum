package com.forum.server.converters.staticInfo;

import com.forum.server.dto.staticInfo.SectionDto;
import com.forum.server.models.staticInfo.Section;
import org.springframework.core.convert.converter.Converter;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SectionToSectionDtoConverter implements Converter<Section, SectionDto> {
    @Override
    public SectionDto convert(Section section) {
        return new SectionDto.builder()
                .Id(section.getSectionId())
                .Name(section.getName())
                .ThemesCount(section.getThemesCount())
                .Url(section.getUrl())
                .build();
    }
}
