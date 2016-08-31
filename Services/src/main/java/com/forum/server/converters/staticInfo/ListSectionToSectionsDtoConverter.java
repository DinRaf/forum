package com.forum.server.converters.staticInfo;

import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.models.staticInfo.Section;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ListSectionToSectionsDtoConverter implements Converter<List<Section>, SectionsDto> {

    @Override
    public SectionsDto convert(List<Section> sections) {
        SectionToSectionDtoConverter converter = new SectionToSectionDtoConverter();
        return new SectionsDto(sections
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
