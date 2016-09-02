package com.forum.server.converters.staticInfo;

import com.forum.server.dto.staticInfo.SubsectionDto;
import com.forum.server.models.staticInfo.Subsection;
import org.springframework.core.convert.converter.Converter;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SubsectionToSubsectionDtoConverter implements Converter<Subsection, SubsectionDto> {
    @Override
    public SubsectionDto convert(Subsection subsection) {
        return new SubsectionDto.Builder()
                .Id(subsection.getSubsection_id())
                .ThemesCount(subsection.getThemesCount())
                .Name(subsection.getName())
                .Url(subsection.getUrl())
                .build();
    }
}
