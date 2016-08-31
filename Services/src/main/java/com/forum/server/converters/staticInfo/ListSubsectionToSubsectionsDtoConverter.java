package com.forum.server.converters.staticInfo;

import com.forum.server.dto.staticInfo.SubsectionsDto;
import com.forum.server.models.staticInfo.Subsection;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ListSubsectionToSubsectionsDtoConverter implements Converter<List<Subsection>, SubsectionsDto>{
    @Override
    public SubsectionsDto convert(List<Subsection> subsections) {
        SubsectionToSubsectionDtoConverter converter = new SubsectionToSubsectionDtoConverter();
        return new SubsectionsDto(subsections
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
